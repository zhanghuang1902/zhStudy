package life.majiang.community.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class GitHubAccessToken {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitHubAccessToken.class);

    public String getAccessToken(AccessToken accessToken){
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url="https://github.com/login/oauth/access_token";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String params = JSON.toJSONString(accessToken);
            HttpEntity<String> requestEntity = new HttpEntity<>(params, headers);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
            String body = responseEntity.getBody();
            String[] split = body.split("&");
            String token = split[0].split("=")[1];
            System.out.println(token);
            return token;
        } catch (RestClientException e) {
            e.printStackTrace();
            LOGGER.info("获取accessToken内容失败");
        }
        return null;
    }
}
