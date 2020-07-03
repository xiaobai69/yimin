package cn.com.mapper;

import cn.com.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    /**
     * 添加用户信息
     * @param user
     */
    @Insert("insert into user (name,password,sex,phone,address) values(#{name},#{password},#{sex},#{phone},#{address})")
    Boolean insertInfo(User user);

    /**
     * 根据用户姓名查询用户信息
     * @param name
     * @return
     */
    @Select("select * from user where name=#{name}")
    User selByName(String name);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    @Select("select * from user where id=#{id}")
    User selById(int id);
}
