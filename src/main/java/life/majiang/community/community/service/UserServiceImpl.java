package life.majiang.community.community.service;

import life.majiang.community.community.mapper.DbUserMapper;
import life.majiang.community.community.model.User;
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
    public void insertUser(User dbUser) {
        dbUserMapper.insertUser(dbUser);
    }

}
