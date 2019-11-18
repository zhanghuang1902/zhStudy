package life.majiang.community.controller;

import life.majiang.community.dto.AccessGitHubUser;
import life.majiang.community.dto.AccessToken;
import life.majiang.community.model.DbUser;
import life.majiang.community.provider.GitHubAccessToken;
import life.majiang.community.provider.GitHubUser;
import life.majiang.community.service.UserService;
import life.majiang.community.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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

    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state, HttpServletResponse response) {

        AccessToken accessToken = new AccessToken();
        accessToken.setClient_id(Client_id);
        accessToken.setClient_secret(Client_secret);
        accessToken.setCode(code);
        accessToken.setRedirect_uri(Redirect_uri);
        accessToken.setState(state);
        String body = gitHubAccessToken.getAccessToken(accessToken);
        AccessGitHubUser user = gitHubUser.getUser(body);
        System.out.println(user.getName());
        if(user !=null){
            int count =userService.selectCount(String.valueOf(user.getId()));
            if(count==0){
                DbUser dbUser = new DbUser();
                dbUser.setId(IdWorker.getId());
                String token = UUID.randomUUID().toString();
                dbUser.setToken(token);
                dbUser.setName(user.getName());
                dbUser.setAccountId(String.valueOf(user.getId()));
                userService.insertUser(dbUser);
                response.addCookie(new Cookie("token",token));
            }
            return "redirect:/";
        }else {
            return "redirect:/";
        }
    }
}
