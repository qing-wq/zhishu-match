package ink.whi.project.modules.competition.converter;


import ink.whi.project.common.domain.dto.CompetitionDTO;
import ink.whi.project.modules.competition.repo.entity.CompetitionDO;
import org.springframework.beans.BeanUtils;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/7
 */
public class CompetitionConverter {

    public static CompetitionDTO toDto(CompetitionDO competition) {
        CompetitionDTO dto = new CompetitionDTO();
        BeanUtils.copyProperties(competition, dto);
        dto.setCompetitionId(competition.getId());
        return dto;
    }
}
