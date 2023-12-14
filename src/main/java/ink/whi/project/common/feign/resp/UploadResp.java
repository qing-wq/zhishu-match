package ink.whi.project.common.feign.resp;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/14
 */
@Data
public class UploadResp {

    private List<String> paths;

}
