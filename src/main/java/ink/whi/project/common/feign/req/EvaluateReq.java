package ink.whi.project.common.feign.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/14
 */
@Data
public class EvaluateReq {

    @JsonProperty("data")
    private List<DataItem> data;

    @JsonProperty("session_hash")
    private String sessionHash;

    @Data
    public static class DataItem {

        private String data;

        private String name;

        private int size;

        @JsonProperty("orig_name")
        private String origName;

        @JsonProperty("is_file")
        private boolean isFile;
    }


    public EvaluateReq(String url, String name, int size) {
        DataItem item = new DataItem();
        item.setData(url + "/file=" + name);
        item.setName(name);
        item.setSize(size);
        item.setFile(true);
        String[] arr = name.split("/");
        int length = arr.length;
        item.setOrigName(arr[length - 1]);
        List<DataItem> data = new ArrayList<>();
        data.add(item);
        this.data = data;
        this.sessionHash = "se23tjn2uwj";
    }
}
