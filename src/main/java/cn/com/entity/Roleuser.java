package cn.com.entity;

import java.io.Serializable;

public class Roleuser implements Serializable {

    private int roleId;
    private String roleName;
    private String rolePassword;
    private String perms;
    private String role;
    private String phone;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRolePassword() {
        return rolePassword;
    }

    public void setRolePassword(String rolePassword) {
        this.rolePassword = rolePassword;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Roleuser{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", rolePassword='" + rolePassword + '\'' +
                ", perms='" + perms + '\'' +
                ", role='" + role + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
