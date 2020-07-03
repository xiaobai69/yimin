package cn.com.service;

import cn.com.entity.Roleuser;

import java.util.List;

public interface IRoleuserService {

    /**
     * 员工添加信息
     * @param roleuser
     */
    Boolean insertInfo(Roleuser roleuser);

    /**
     * 根据姓名查询员工信息
     * @param roleName
     * @return
     */
    Roleuser selByName(String roleName);

    /**
     * 根据姓名修改员工权限
     * @param roleuser
     */
    Boolean updatePerms(Roleuser roleuser);

    /**
     * 查询所有员工信息
     * @return
     */
    List<Roleuser> selAll();

    /**
     * 根据姓名删除信息
     * @param name
     * @return
     */
    Boolean deleteByName(String name);
}
