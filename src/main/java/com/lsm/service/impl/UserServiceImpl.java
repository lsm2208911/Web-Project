package com.lsm.service.impl;

import com.lsm.IDao.IUserDao;
import com.lsm.domain.User;
import com.lsm.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/4/17.
 */

@Service("UserService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    public User getUserById(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }
}
