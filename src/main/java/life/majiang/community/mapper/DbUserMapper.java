package life.majiang.community.mapper;


import life.majiang.community.model.DbUser;
import org.springframework.stereotype.Component;

/**
 * @author 97083
 */
@Component(value = "dbUserMapper")
public interface DbUserMapper {

    void insertUser(DbUser dbUser);

    DbUser selectUser(String token);

    Integer selectCount(String id);
}
