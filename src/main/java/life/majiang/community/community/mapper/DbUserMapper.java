package life.majiang.community.community.mapper;


import life.majiang.community.community.model.User;
import org.springframework.stereotype.Component;

/**
 * @author 97083
 */
@Component(value = "dbUserMapper")
public interface DbUserMapper {

    void insertUser(User dbUser);

}
