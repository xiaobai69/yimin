package cn.com.mapper;
import	java.util.List;
import java.util.Map;

import cn.com.entity.Buy;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface BuyMapper {

    /**
     * 添加购物车信息
     * @param buy
     * @return
     */
    @Insert("insert into buy (did,uid,number) values(#{did},#{uid},#{number})")
    Boolean insertInfo(Buy buy);

    /**
     * 根据药品id删除购物车信息
     * @param did
     * @return
     */
    @Delete("delete from buy where did =#{did}")
    Boolean deleteById(int did);

    /**
     * 根据购物车id删除信息
     * @param id
     * @return
     */
    @Delete("delete from buy where id =#{id}")
    Boolean deleteId(int id);

    /**
     * 查询购物车信息
     * @return
     */
    @Select("select d.drugname,d.salemoney,SUM(b.number) as number " +
            "from drug d,user u,buy b " +
            "where d.id=b.did and u.id=b.uid and uid = #{uid} and b.state=#{state} " +
            "GROUP BY  drugname " +
            "ORDER BY number desc")
    List<Map<String,Object>> selAllByUid(int uid,int state);

    /**
     * 查询线上交易信息
     * @return
     */
    @Select("select u.*,b.number,d.drugname,d.salemoney,b.id as bid from user u,buy b,drug d " +
            " where u.id = b.uid and d.id = b.did and b.state= 1")
    List<Map<String,Object>> selAll();

    /**
     * 根据用户id和药品id进行分组查询购物车信息
     * @param did
     * @param uid
     * @return
     */
    @Select("select did,uid,SUM(number) as number from buy where did = #{did} and uid = #{uid} GROUP BY did ")
    Buy selByDid(int did,int uid);

    /**
     * 根据药品id更改药品状态
     * @param did
     * @return
     */
    @Update("update buy set state=1 where did=#{did}")
    Boolean updateState(int did);

    /**
     * 线下查询用户购买药品信息
     * @return
     */
    @Select("select u.name,u.phone,u.address ,d.drugname,d.salemoney,b.number  " +
            "from drug d,user u,buy b  " +
            "where d.id=b.did and u.id=b.uid and b.state=1 and uid in (select uid FROM buy ) " +
            "GROUP BY  b.id " +
            "ORDER BY na    me desc")
    List<Map<String,Object>> selAllByState();

    /**
     * 根据id获取信息
     * @param id
     * @return
     */
    @Select("select * from buy where id=#{id}")
    Buy selById(int id);

}
