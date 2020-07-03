package cn.com.controller;
import	java.util.HashMap;
import java.util.List;
import	java.util.Map;

import cn.com.entity.Roleuser;

import cn.com.service.impl.RoleuserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/role")
public class RoleuserController {

    @Autowired
    private RoleuserServiceImpl roleuserService;

    /**
     * 员工账号登录
     * @param roleuser
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/roleLogin")
    public Map login(@RequestBody Roleuser roleuser, Model model, HttpSession session){
        Map<String,String> map = new HashMap<> ();
        /**
         * 使用shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //前端获取密码进行"MD5"加密
        String password = new SimpleHash("MD5",roleuser.getRolePassword(), roleuser.getRoleName(), 1024).toHex();
        System.out.println(password);
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(roleuser.getRoleName(),password);
        //3.执行登录方法
        try {
            subject.login(token);
            //登录成功
            model.addAttribute("msg","登录成功");
            //将user对象存进session
            session.setAttribute("roleuser",roleuser);
        }catch (UnknownAccountException e){
            //登录失败：用户名不存在
            model.addAttribute("msg","用户名不存在");

        }catch (IncorrectCredentialsException e) {
            //登录失败:密码错误
            model.addAttribute("msg", "密码错误");
        }
        map.put("msg",(String)model.getAttribute("msg"));
        return map;
    }

    /**
     * 添加员工账号
     * @param roleuser
     * @param model
     * @return
     */
    @RequestMapping("/roleInsert")
    public Map insert(@RequestBody Roleuser roleuser, Model model){
        System.out.println("添加信息...");
        Map<String,String> map = new HashMap<> ();
        //前端获取密码进行"MD5"加密
        String password = new SimpleHash("MD5",roleuser.getRolePassword(),roleuser.getRoleName(), 1024).toHex();
        roleuser.setRolePassword(password);
        if(roleuserService.selByName(roleuser.getRoleName()) != null){
            model.addAttribute("msg", "账户名已存在，请重新输入");
        }else if(roleuserService.insertInfo(roleuser)){
            model.addAttribute("msg", "成功");
        }else {
            model.addAttribute("msg", "信息添加失败，请重新输入");
        }
        map.put("msg",(String)model.getAttribute("msg"));
        return map;
    }

    /**
     * 查询所有员工信息
     * @return
     */
    @RequestMapping("/selAll")
    public List<Roleuser> selAll(){
        List<Roleuser> roleuserList = roleuserService.selAll();
        return roleuserList;
    }

    /**
     * 删除员工信息
     * @param roleuser
     * @return
     */
    @RequestMapping("/deleteByName")
    public Map deleteByName(@RequestBody Roleuser roleuser){

        Map<String,String> map = new HashMap<> ();

        if(roleuserService.deleteByName(roleuser.getRoleName())){
            map.put("msg","成功");
        }else{
            map.put("msg","删除失败");
        }
        return map;
    }

    /**
     * 更新员工信息
     * @param roleuser
     * @return
     */
    @RequestMapping("/updateInfo")
    public Map updateInfo(@RequestBody Roleuser roleuser){
        Map<String,String> map = new HashMap<> ();
        String password = new SimpleHash("MD5",roleuser.getRolePassword(),roleuser.getRoleName(), 1024).toHex();
        roleuser.setRolePassword(password);
        if(roleuserService.updatePerms(roleuser)){
            map.put("msg","成功");
        }else{
            map.put("msg","修改失败");
        }
        return map;
    }

    /**
     * 退出登录
     * @param response
     * @throws Exception
     */
    @RequestMapping("/logout")
    public void logout(HttpServletResponse response) throws Exception{
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        response.sendRedirect("../html/roleLogin.html");
    }

    /**
     * 获取登录员工信息
     * @return
     */
    @RequestMapping("/getRoleuser")
    public Roleuser getRoleuser(){
        Subject subject = SecurityUtils.getSubject();
        Roleuser roleuser =(Roleuser) subject.getPrincipal();
        return roleuser;
    }
}
