package life.majiang.community.community.controller;

import life.majiang.community.community.dto.AccessGitHubUser;
import life.majiang.community.community.dto.AccessToken;
import life.majiang.community.community.provider.GitHubAccessToken;
import life.majiang.community.community.provider.GitHubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    //8cd120291a25422f9c04b90c3cecaaa76fe9c184
    @Autowired
    private GitHubAccessToken gitHubAccessToken;

    @Autowired
    private GitHubUser gitHubUser;
    @Value("${github.Client_id}")
    private String Client_id;
    @Value("${github.Client_secret}")
    private String Client_secret;
    @Value("${github.Redirect_uri}")
    private String Redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state) {

        AccessToken accessToken = new AccessToken();
        accessToken.setClient_id(Client_id);
        accessToken.setClient_secret(Client_secret);
        accessToken.setCode(code);
        accessToken.setRedirect_uri(Redirect_uri);
        accessToken.setState(state);
        String body = gitHubAccessToken.getAccessToken(accessToken);
        AccessGitHubUser user = gitHubUser.getUser(body);
        System.out.println(user.getName());
        return "index";
    }
}
