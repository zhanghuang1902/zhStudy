package life.majiang.community.community.provider;

import life.majiang.community.community.dto.AccessGitHubUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GitHubUser {

    public AccessGitHubUser getUser(String accessToken){
        RestTemplate restTemplate = new RestTemplate();
        String url="https://api.github.com/user?access_token="+accessToken;
        ResponseEntity<AccessGitHubUser> entity = restTemplate.getForEntity(url, AccessGitHubUser.class);
        AccessGitHubUser body = entity.getBody();
        return body;
    }
}
