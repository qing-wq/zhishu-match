package ink.whi.project.modules.competition.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.whi.project.common.domain.base.BaseDO;
import ink.whi.project.common.domain.dto.BaseUserInfoDTO;
import ink.whi.project.common.domain.dto.CompetitionDTO;
import ink.whi.project.common.domain.dto.StatisticsDTO;
import ink.whi.project.common.domain.page.PageParam;
import ink.whi.project.common.domain.page.PageVo;
import ink.whi.project.common.domain.req.CompetitionUpdReq;
import ink.whi.project.common.enums.GroupStatusEnum;
import ink.whi.project.common.enums.YesOrNoEnum;
import ink.whi.project.common.exception.BusinessException;
import ink.whi.project.common.exception.StatusEnum;
import ink.whi.project.modules.competition.converter.CompetitionConverter;
import ink.whi.project.modules.competition.repo.dao.CompetitionDao;
import ink.whi.project.modules.competition.repo.dao.RegisterDao;
import ink.whi.project.modules.competition.repo.entity.CompetitionDO;
import ink.whi.project.modules.competition.repo.entity.RegisterDO;
import ink.whi.project.modules.competition.repo.mapper.CompetitionMapper;
import ink.whi.project.modules.competition.service.CompetitionService;
import ink.whi.project.modules.team.repo.dao.TeamDao;
import ink.whi.project.modules.team.repo.entity.TeamDO;
import ink.whi.project.modules.team.service.TeamService;
import ink.whi.project.modules.user.converter.UserConverter;
import ink.whi.project.modules.user.repo.dao.UserDao;
import ink.whi.project.modules.user.repo.entity.UserInfoDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/7
 */
@Service
public class CompetitionServiceImpl extends ServiceImpl<CompetitionMapper, CompetitionDO> implements CompetitionService {

    @Autowired
    private CompetitionDao competitionDao;

    @Autowired
    private RegisterDao registerDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TeamDao teamDao;

    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    @Override
    public PageVo<CompetitionDO> list(PageParam pageParam) {
        List<CompetitionDO> list = this.listAll(pageParam);
        return PageVo.build(list, pageParam.getPageSize(), pageParam.getPageNum(), this.countAll());
    }

    /**
     * 更新
     * @param req
     * @return
     */
    public boolean update(CompetitionUpdReq req){
        CompetitionDO competitionDO = new CompetitionDO();
        BeanUtils.copyProperties(req, competitionDO);
        boolean update = lambdaUpdate().eq(CompetitionDO::getId, req.getId()).update(competitionDO);
        return update;
    }

    /**
     * 软删除
     * @param id
     * @return
     */
    public boolean delete(Long id){
        boolean update = lambdaUpdate().eq(CompetitionDO::getId, id)
                .set(CompetitionDO::getDeleted,YesOrNoEnum.YES.getCode()).update();
        return update;
    }

    public List<CompetitionDO> listAll(PageParam pageParam) {
        return lambdaQuery()
                .eq(CompetitionDO::getDeleted, YesOrNoEnum.NO.getCode())
                .orderByDesc(BaseDO::getCreateTime)
                .last(PageParam.getLimitSql(pageParam))
                .list();
    }

    /**
     * 获取全部比赛数量
     * @return
     */
    public Long countAll() {
        return lambdaQuery().eq(CompetitionDO::getDeleted, YesOrNoEnum.NO.getCode())
                .count();
    }

    @Override
    public Integer getMaxMemberCount(Long competitionId) {
        CompetitionDO competition = this.getById(competitionId);
        return competition.getMaxMember();
    }

    @Override
    public void signUp(Long competitionId, Long userId) {
        CompetitionDO competition = getById(competitionId);
        if (competition == null) {
            throw BusinessException.newInstance(StatusEnum.RECORDS_NOT_EXISTS, "比赛不存在：" + competitionId);
        }

        Date signupDeadline = competition.getSignupDeadline();

        // 将 java.util.Date 转换为 java.time.LocalDate
        LocalDate deadlineDate = signupDeadline.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // 获取今天的日期
        LocalDate today = LocalDate.now();

        if (deadlineDate.isBefore(today)) {
            throw BusinessException.newInstance(StatusEnum.UNEXPECT_ERROR, "比赛已截止报名" + competitionId);
        }

        RegisterDO record = registerDao.getRecord(userId);
        if (record != null) {
            if (!Objects.equals(record.getCompetitionId(), competitionId)) {
                throw BusinessException.newInstance(StatusEnum.ILLEGAL_OPERATE, "您已报名其他比赛");
            }
            throw BusinessException.newInstance(StatusEnum.RECORDS_ALREADY_EXISTS, "您已报名该比赛");
        }

        record = new RegisterDO();
        record.setCompetitionId(competitionId);
        record.setUserId(userId);
        registerDao.save(record);
    }

    /**
     * 查询参赛用户信息
     * @param competitionId
     * @return
     */
    @Override
    public List<BaseUserInfoDTO> queryCompetitionUser(Long competitionId) {
        List<Long> userIds = registerDao.listUserByCompetitionId(competitionId);
        if (userIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<UserInfoDO> users = userDao.listByUserIds(userIds);
        return UserConverter.toDtoList(users);
    }

    @Override
    public CompetitionDTO queryCompetitionInfo(Long competitionId) {
        CompetitionDO competition = getById(competitionId);
        return CompetitionConverter.toDto(competition);
    }

    /**
     * 获取用户参加的比赛id
     * @param userId
     * @return
     */
    @Override
    public Long getUserCompetition(Long userId) {
        return registerDao.listUserCompetition(userId);
    }

    /**
     * 更新组队状态
     * @param competitionId
     * @param userId
     * @param groupStatusEnum
     */
    @Override
    public void updateGroupStatus(Long competitionId, Long userId, GroupStatusEnum groupStatusEnum) {
        registerDao.updateGroupStatus(competitionId, userId, groupStatusEnum.getCode());
    }

    /**
     * 查询用户组队状态
     * @param competitionId
     * @param userId
     * @return
     */
    @Override
    public GroupStatusEnum queryUserGroupStatus(Long competitionId, Long userId) {
        Integer status = registerDao.getGroupStatus(competitionId, userId);
        return GroupStatusEnum.formCode(status);
    }

    @Override
    public void cancelRegister(Long userId, Long competitionId) {
        registerDao.lambdaUpdate().eq(RegisterDO::getUserId, userId)
                .eq(RegisterDO::getCompetitionId, competitionId)
                .remove();
    }

    @Override
    public List<StatisticsDTO> getStatistic() {
        ArrayList<StatisticsDTO> result = new ArrayList<>();

        List<CompetitionDO> competitions = lambdaQuery().list();
        competitions.forEach(s -> {
            StatisticsDTO dto = new StatisticsDTO();
            dto.setCompetitionId(s.getId());
            dto.setCompetitionName(s.getName());

            // 报名人数
            Long userCount = registerDao.lambdaQuery().eq(RegisterDO::getCompetitionId, s.getId()).count();
            dto.setUserCount(userCount);

            // 队伍数
            Long teamCount = teamDao.lambdaQuery().eq(TeamDO::getCompetitionId, s.getId()).count();
            dto.setTeamCount(teamCount);
            result.add(dto);
        });

        return result;
    }
}
