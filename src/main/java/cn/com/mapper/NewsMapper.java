package cn.com.mapper;
import	java.util.List;

import cn.com.entity.News;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface NewsMapper {

    /**
     * 添加公告信息
     * @param news
     * @return
     */
    @Insert("insert into news (title,contents,inputtime,inputname) values(#{title},#{contents},#{inputTime},#{inputName})")
    Boolean interInfo(News news);

    /**
     * 查询公告信息,根据id降序排列
     * @return
     */
    @Select("select * from news order by id desc")
    List<News> selAll();

    /**
     * 根据标题删除公告信息
     * @param id
     * @return
     */
    @Delete("delete from news where id = #{id}")
    Boolean deleteById(int id);


}
