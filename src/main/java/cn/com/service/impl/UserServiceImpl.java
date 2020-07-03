package cn.com.service.impl;

import cn.com.entity.User;
import cn.com.mapper.UserMapper;
import cn.com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public Boolean insertInfo(User user) {
        Boolean bool = false;
        if(userMapper.insertInfo(user)){
            bool = true;
        }
        return bool;
    }

    @Override
    public User selByName(String name) {
        return userMapper.selByName(name);
    }

    @Override
    public User selById(int id) {
        return userMapper.selById(id);
    }


}
