package cn.com.controller;
import	java.util.HashMap;
import java.util.List;
import	java.util.Map;

import cn.com.entity.Buy;
import cn.com.entity.User;
import cn.com.service.impl.BuyServiceImpl;
import cn.com.service.impl.DrugServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/buy")
@ResponseBody
public class BuyController {

    @Autowired
    private BuyServiceImpl buyServiceImpl;

    @Autowired
    private DrugServiceImpl drugServiceImpl;

    /**
     * 添加购物车信息
     * @param buy
     * @return
     */
    @RequestMapping("/insertInfo")
    public Map insertInfo(@RequestBody Buy buy){
        Map<String, String> map = new HashMap<> ();
        if(buyServiceImpl.insertInfo(buy)){
            map.put("msg","添加成功，请到购物车查看");
        }else {
            map.put("msg","添加失败");
        }
        return map;
    }

    /**
     * 查询购物车信息
     * @return
     */
    @RequestMapping("/selAll")
    public List<Map<String,Object>> selAll(HttpSession session){

        User user  = (User) session.getAttribute("user");

        if(user == null){
            return null;
        }
        int uid = user.getId();
        List<Map<String,Object>> list =buyServiceImpl.selAllByUid(uid,0);
        return list;
    }



    /**
     * 删除购物车信息
     * @param drugName
     * @return
     */
    @RequestMapping("/deleteInfo")
    public Map deleteInfo(String drugName){
        //通过药品名称获取药品id
       int did = drugServiceImpl.selAllByName(drugName).getId();
        Map<String, String> map = new HashMap<> ();
        if(buyServiceImpl.deleteById(did)){
            map.put("msg","删除成功");
        }else {
            map.put("msg","删除失败");
        }
        return map;
    }

    /**
     * 购买成功更改购物车药品状态
     * @param drugName
     * @return
     */
    @RequestMapping("/buy")
    public Map buy(String drugName,HttpSession session){
        Map<String, String> map = new HashMap<> ();
        //通过药品名称获取药品did
        int did = drugServiceImpl.selAllByName(drugName).getId();
   /*     //获取用户uid
        int uid = ((User) session.getAttribute("user")).getId();
        //获取当前用户uid购物车内药品did的数量number
        int number = buyServiceImpl.selByDid(did,uid).getNumber();*/
        //确认购买更改药品状态
        if(buyServiceImpl.updateState(did)){
            map.put("msg","购买成功");
        }else {
            map.put("msg","购买失败");
        }
        return map;
    }

    /**
     * 确认发货
     * @param id
     * @param session
     * @return
     */
    @RequestMapping("/buyUp")
    public Map buyUp(int id,HttpSession session){
        Map<String, String> map = new HashMap<> ();
        String drugName = drugServiceImpl.selAllById(buyServiceImpl.selById(id).getDid()).getDrugName();
        System.out.println(id);
        System.out.println(drugName);
        if(buyServiceImpl.deleteId(id) && drugServiceImpl.updateNumber(1,drugName)){
            map.put("msg","成功");
        }else {
            map.put("msg","失败");
        }
        return map;
    }

    /**
     * 获取线上交易订单信息
     * @return
     */
    @RequestMapping("/selAllUp")
    public List<Map<String,Object>> selAll(){
        List<Map<String,Object>> list =buyServiceImpl.selAll();
        return list;
    }

    /**
     * 线下购物信息
     * @return
     */
    @RequestMapping("/underSelAll")
    public List<Map<String,Object>> selAll(int uid){
        List<Map<String,Object>> list =buyServiceImpl.selAllByUid(uid,0);
        return list;
    }

    /**
     * 购买成功删除购物车信息，同时修改药品表的现有库存和销量信息
     * @param drugName
     * @return
     */
    @RequestMapping("/underBuy")
    public Map buy(String drugName,int uid){
        Map<String, String> map = new HashMap<> ();

        //通过药品名称获取药品did
        int did = drugServiceImpl.selAllByName(drugName).getId();

        //获取当前用户uid购物车内药品did的数量number
        int number = buyServiceImpl.selByDid(did,uid).getNumber();

        //确认购买后删除该药品在购物车信息，并改变药品的销量和现有库存量
        if(buyServiceImpl.deleteById(did)&&drugServiceImpl.updateNumber(number,drugName)){
            map.put("msg","购买成功");
        }else {
            map.put("msg","购买失败");
        }
        return map;
    }

}
