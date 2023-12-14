package ink.whi.project.common.feign.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/14
 */
@Data
public class EvaluateResp {

    @JsonProperty("data")
    private List<String> data;

    @JsonProperty("is_generating")
    private boolean isGenerating;

    @JsonProperty("duration")
    private double duration;

    @JsonProperty("average_duration")
    private double averageDuration;

}
