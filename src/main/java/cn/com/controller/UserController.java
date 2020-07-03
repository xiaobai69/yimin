package cn.com.controller;

import cn.com.entity.User;
import cn.com.service.impl.UserServiceImpl;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller

@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 用户注册
     * @param user
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/register")
    public Map register(@RequestBody User user, Model model){

        Map<String,String> map = new HashMap<>();
        if(userService.selByName(user.getName()) != null){
            model.addAttribute("msg","此用户姓名已存在，请重命名");
        }else if(user.getName() == ""){
            model.addAttribute("msg","用户姓名不能为空");
        } else{
            //数据进行"MD5"加密
            String password = new SimpleHash("MD5",user.getPassword(),user.getName(),1024).toHex();
            user.setPassword(password);
            if(userService.insertInfo(user)){
                model.addAttribute("msg","信息添加成功，请登录");
            }else {
                model.addAttribute("msg","信息添加失败，请重新输入");
            }
        }
        map.put("msg",(String) model.getAttribute("msg"));
        System.out.println(map.get("msg"));
        return map;
    }

    /**
     * 用户登录
     * @param user
     * @param model
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public Map login(@RequestBody User user,Model model,HttpSession session){
        Map<String,String> map = new HashMap<>();
        String password = new SimpleHash("MD5",user.getPassword(), user.getName(), 1024).toHex();
                if(userService.selByName(user.getName()) == null){
                    model.addAttribute("msg","用户名不存在");
                }else if (!userService.selByName(user.getName()).getPassword().equals(password)){
                    model.addAttribute("msg", "密码错误");
                }else if(userService.selByName(user.getName()).getPassword().equals(password)){
                    model.addAttribute("msg","登录成功");
                    //将user对象存进session
                    session.setAttribute("user",userService.selByName(user.getName()));
                }
        map.put("msg",(String)model.getAttribute("msg"));
        return map;
    }

    /**
     * 用户退出，删除session中的登录数据
     * @param session
     * @param response
     * @throws Exception
     */
    @RequestMapping("/outLog")
    public void outLog(HttpSession session, HttpServletResponse response) throws Exception{
        session.removeAttribute("user");
        response.sendRedirect("../html/login.html");
    }

}
