package cn.com.service;

import cn.com.entity.News;

import java.util.List;

public interface INewsService {

    /**
     * 添加公告信息
     * @param news
     * @return
     */
    Boolean interInfo(News news);

    /**
     * 查询公告信息,根据id降序排列
     * @return
     */
    List<News> selAll();

    /**
     * 根据标题删除公告信息
     * @param id
     * @return
     */
    Boolean deleteById(int id);
}
