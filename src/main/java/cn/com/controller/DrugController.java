package cn.com.controller;
import	java.util.HashMap;
import	java.util.List;
import java.util.Map;

import cn.com.entity.Drug;
import cn.com.entity.User;
import cn.com.service.impl.DrugServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/drug")
public class DrugController {

    @Autowired
    private DrugServiceImpl drugServiceImpl;

    /**
     * 获得所有药品信息
     * @return
     */
    @RequestMapping("/getAll")
    public List<Drug> getAll(){
        List<Drug> list = drugServiceImpl.selAll();
        return list;
    }

    /**
     * 获得模糊查询信息
     * @param info
     * @return
     */
    @RequestMapping("/getByInfo")
    public List<Drug> getByInfo(String info){
        List<Drug> list = drugServiceImpl.selByInfo(info);
        return list;
    }

    /**
     * 获取登录时的用户信息
     * @param session
     * @return
     */
    @RequestMapping("/getUser")
    public User getUser(HttpSession session){
        User user =(User) session.getAttribute("user");
        return user;
    }

    /**
     * 根据药品种类获取药品信息
     * @param type
     * @return
     */
    @RequestMapping("/getByType")
    public List<Drug> getByType(String type){
        List<Drug> list = drugServiceImpl.selByType(type);

        return list;
    }

    /**
     * 获取药店推荐药品
     * @return
     */
    @RequestMapping("/getByNote")
    public List<Drug> getByNote(){
        List<Drug> list = drugServiceImpl.selByNote();

        return list;
    }

    /**
     * 根据药品销量获取药品信息，倒序排列
     * @return
     */
    @RequestMapping("/getByNumber")
    public List<Drug> getByNumber(){
        List<Drug> list = drugServiceImpl.selByNumber();


        return list;
    }

    /**
     * 查询快过期药品或过期药品（距离过期时间一个月以内）
     * @return
     */
    @RequestMapping("/selOuttime")
    public List<Drug> selOuttime(){
        List<Drug> list = drugServiceImpl.selOuttime();


        return list;
    }

    /**
     * 添加药品信息
     * @param drug
     * @return
     */
    @RequestMapping("/insertInfo")
    public Map insertInfo(@RequestBody Drug drug) {
        Map<String, String> map = new HashMap<String, String> ();
        if (drugServiceImpl.insertInfo(drug)){
            map.put("msg","添加成功");
        }else {
            map.put("msg","添加失败");
        }
        return map;
    }

    /**
     * 根据药品名称删除药品信息
     * @param name
     * @return
     */
    @RequestMapping("/deleteByName")
    public Map deleteByName(String name ) {

        Map<String, String> map = new HashMap<String, String> ();
        if (drugServiceImpl.delByName(name)){
            map.put("msg","删除成功");
        }else {
            map.put("msg","删除失败");
        }

        return map;
    }

    /**
     * 修改药品信息
     * @param drug
     * @return
     */
    @RequestMapping("/updateByName")
    public Map updateByName(@RequestBody Drug drug ) {
        System.out.println(drug);
        Map<String, String> map = new HashMap<String, String> ();
        if (drugServiceImpl.updateByName(drug)){
            map.put("msg","修改成功");
        }else {
            map.put("msg","修改失败");
        }

        return map;
    }


    /**
     * 根据药品id添加药品库存
     * @param count
     * @param id
     * @return
     */
    @RequestMapping("/updateCount")
    public Map updateCount(int count,int id ) {
        System.out.println(count);
        System.out.println(id);
        Map<String, String> map = new HashMap<String, String> ();
        if (drugServiceImpl.updateCount(count, id)){
            map.put("msg","添加成功");
        }else {
            map.put("msg","添加失败");
        }
        return map;
    }


}
