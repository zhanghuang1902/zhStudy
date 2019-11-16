package life.majiang.community.community.controller;

import life.majiang.community.community.dto.AccessGitHubUser;
import life.majiang.community.community.dto.AccessToken;
import life.majiang.community.community.provider.GitHubAccessToken;
import life.majiang.community.community.provider.GitHubUser;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code ,@RequestParam(name="state") String state){

        AccessToken accessToken = new AccessToken();
        accessToken.setClient_id("f2edf418df074bb1dc01");
        accessToken.setClient_secret("9591b2caa4a91415fe95b68cb23e1713a60de07e");
        accessToken.setCode(code);
        accessToken.setRedirect_uri("http://localhost:8887/callback");
        accessToken.setState(state);
        String body = gitHubAccessToken.getAccessToken(accessToken);
        AccessGitHubUser user = gitHubUser.getUser(body);
        System.out.println(user.getName());
        return  "index";

    }

}
