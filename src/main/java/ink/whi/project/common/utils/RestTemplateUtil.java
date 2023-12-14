package ink.whi.project.common.utils;

import ink.whi.project.common.exception.BusinessException;
import ink.whi.project.common.exception.StatusEnum;
import ink.whi.project.common.rest_template.req.EvaluateReq;
import ink.whi.project.common.rest_template.resp.EvaluateResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;


/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/14
 */
@Component
@Slf4j
public class RestTemplateUtil {

    @Autowired
    private final RestTemplate restTemplate;

//    @Value("${aiService.url}")
//    private String fileServiceUrl = "http://10.60.98.106:7860";
    private String fileServiceUrl = "http://10.60.98.106:7860";

    @Autowired
    public RestTemplateUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public EvaluateResp evaluate(EvaluateReq req) {
        String url = fileServiceUrl + "/run/predict";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EvaluateReq> requestEntity = new HttpEntity<>(req, headers);

        log.info("EvaluateReq:{}", req.toString());
        EvaluateResp evaluateResp = restTemplate.postForObject(url, requestEntity, EvaluateResp.class);

        log.info("evaluateResp:{}", evaluateResp.toString());
        log.info("code:{}", evaluateResp.getData().get(0));
        if (evaluateResp.getData().get(0).equals("-404"))throw new BusinessException(StatusEnum.UNEXPECT_ERROR, "文件无效！");
        return evaluateResp;
    }

    public String uploadFile(byte[] fileContent, String fileName) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        ByteArrayResource resource = new ByteArrayResource(fileContent) {
            @Override
            public String getFilename() {
                return fileName;
            }
        };

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("files", resource);

        RequestEntity<MultiValueMap<String, Object>> requestEntity = new RequestEntity<>(body, headers, HttpMethod.POST, URI.create(fileServiceUrl + "/upload"));

        String[] responseEntity = restTemplate.postForObject(fileServiceUrl + "/upload", requestEntity, String[].class);

        for (String s : responseEntity) {
            log.info("s:{}", s  );
        }


        log.info("Response:{}", responseEntity);

        return responseEntity[0];
    }

}
