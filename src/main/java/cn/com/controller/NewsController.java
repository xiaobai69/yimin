package cn.com.controller;
import	java.util.Map;
import	java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import	java.util.HashMap;

import cn.com.entity.News;
import cn.com.entity.Roleuser;
import cn.com.service.impl.NewsServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/news")
@ResponseBody
@Controller
public class NewsController {

    @Autowired
    private NewsServiceImpl newsService;

    /**
     * 发布公告信息
     * @param news
     * @return
     */
    @RequestMapping("/insertInfo")
    public Map insertInfo(@RequestBody News news){
        Map<String, Object> map = new HashMap<>();
        //获取登录员工信息
        Subject subject = SecurityUtils.getSubject();
        Roleuser roleuser =(Roleuser) subject.getPrincipal();
        news.setInputName(roleuser.getRoleName());
        //我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取String类型的时间
        String createdate = sdf.format(date);
        news.setInputTime(createdate);
        if(newsService.interInfo(news)){
            map.put("msg","公告发布成功");
        }else {
            map.put("msg","公告发布失败");
        }
        return map;
    }

    /**
     * 查询获取全部公告信息
     * @return
     */
    @RequestMapping("/getAll")
    public List<News> getAll(){
        List<News> list = newsService.selAll();
        return list;
    }

    /**
     * 根据id删除公告信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public Map deleteById(int id){
        Map<String, String> map = new HashMap<>();
        if(newsService.deleteById(id)){
            map.put("msg","删除成功");
        }else {
            map.put("msg","删除失败");
        }
        return map;
    }
}
