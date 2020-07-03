package cn.com.service.impl;

import cn.com.entity.News;
import cn.com.mapper.NewsMapper;
import cn.com.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class NewsServiceImpl implements INewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public Boolean interInfo(News news)
    {
        return newsMapper.interInfo(news);
    }

    @Override
    public List<News> selAll() {
        return newsMapper.selAll();
    }

    @Override
    public Boolean deleteById(int id) {

        return newsMapper.deleteById( id);
    }
}
