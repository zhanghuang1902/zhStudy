package life.majiang.community.service;

import life.majiang.community.model.DbUser;

/**
 * @author 97083
 */
public interface UserService {
    void insertUser(DbUser dbUser);

    DbUser selectUser(String token);

    Integer selectCount(String id);
}