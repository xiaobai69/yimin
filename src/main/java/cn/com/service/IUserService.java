package cn.com.service;


import cn.com.entity.User;

public interface IUserService {


    /**
     * 添加用户信息
     * @param user
     */
    Boolean insertInfo(User user);

    /**
     * 根据用户姓名查询用户信息
     * @param name
     * @return
     */
    User selByName(String name);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    User selById(int id);
}
