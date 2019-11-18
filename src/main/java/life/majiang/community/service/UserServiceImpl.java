package life.majiang.community.service;

import life.majiang.community.mapper.DbUserMapper;
import life.majiang.community.model.DbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 97083
 */
@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    private DbUserMapper dbUserMapper;

    @Override
    public void insertUser(DbUser dbUser) {
        dbUserMapper.insertUser(dbUser);
    }

    @Override
    public DbUser selectUser(String token) {
        try {
            return dbUserMapper.selectUser(token);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Integer selectCount(String id) {
        try {
            return dbUserMapper.selectCount(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
