package cn.com.mapper;

import cn.com.entity.Roleuser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RoleuserMapper {

    /**
     * 员工添加信息
     * @param roleuser
     */
    @Insert("insert into roleuser (roleName,rolePassword,perms,role,phone) values(#{roleName},#{rolePassword},#{perms},#{role},#{phone})")
    Boolean insertInfo(Roleuser roleuser);

    /**
     * 根据姓名查询员工信息
     * @param roleName
     * @return
     */
    @Select("select * from roleuser where rolename =#{roleName}")
    Roleuser selByName(String roleName);

    /**
     * 根据姓名修改员工权限
     * @param roleuser
     */
    @Update("update roleuser set perms = #{perms},role=#{role},roleName=#{roleName},rolePassword=#{rolePassword},phone=#{phone} where rolename = #{roleName}")
    Boolean updatePerms(Roleuser roleuser);

    /**
     * 查询员工所以信息
     * @return
     */
    @Select("select rolename,role,phone from roleuser ")
    List<Roleuser> selAll();

    /**
     * 根据姓名删除信息
     * @param name
     * @return
     */
    @Delete("delete from roleuser where rolename=#{roleName}")
    Boolean deleteByName(String name);
}
