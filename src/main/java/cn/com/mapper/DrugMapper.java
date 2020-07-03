package cn.com.mapper;
import	java.util.List;

import cn.com.entity.Drug;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface DrugMapper {

    /**
     * 查询所有药品信息
     * @return
     */
    @Select("select * from drug ")
    List<Drug> selAll();

    /**
     * 根据药品名称查询本药品信息
     * @param name
     * @return
     */
    @Select("select * from drug where drugname=#{name} ")
    Drug selAllByName(String name);


    /**
     * 根据药品id查询本药品信息
     * @param id
     * @return
     */
    @Select("select * from drug where id=#{id} ")
    Drug selAllById(int id);

    /**
     * 药品信息模糊查询
     * @param info
     * @return
     */
    @Select("select * from drug where drugname like '%${info}%' or mainFunction like '%${info}%' or type like '%${info}%'")
    List<Drug> selByInfo(String info);

    /**
     * 根据药品类型模糊查询
     * @param type
     * @return
     */
    @Select("select * from drug where type like '%${type}%'")
    List<Drug> selByType(String type);

    /**
     * 根据药品备注信息查询
     * @return
     */
    @Select("select * from drug where note = '推荐'")
    List<Drug> selByNote();

    /**
     * 根据销量查询，降序排列
     * @return
     */
    @Select("select * from drug order by number desc")
    List<Drug> selByNumber();

    /**
     * 根据药品名称药品销量+number，同时当前库存量-number
     * @param number
     * @param drugName
     * @return
     */
    @Update("update drug set number = number+#{number} ,nowcount = nowcount-#{number} where drugname=#{drugName}")
    Boolean updateNumber(int number,String drugName);


    /**
     * 根据药品名称修改售价
     * @param name
     * @return
     */
    @Update("update drug set salemoney=#{money} where drugname=#{nmae}")
    Boolean updateSalByName(float money,String name);

    /**
     * 根据药品名称删除药品信息
     * @param name
     * @return
     */
    @Delete("delete from drug where drugname=#{name}")
    Boolean delByName(String name);

    /**
     * 添加药品信息
     * @param drug
     * @return
     */
    @Insert("insert into drug " +
            "(drugname,type,mainfunction,madetime,totaltime,joinmoney,salemoney,note,count,nowcount)" +
            "values(#{drugName},#{type},#{mainFunction},#{madeTime},#{totalTime},#{joinMoney},#{saleMoney},#{note},#{count},#{nowCount})")
    Boolean insertInfo(Drug drug);

    /**
     * 根据药品名称，更改药品库存信息
     * @param drug
     * @return
     */
    @Update("update drug set type =#{type},mainfunction =#{mainFunction},madeTime =#{madeTime}," +
            "totalTime =#{totalTime},joinMoney =#{joinMoney},saleMoney =#{saleMoney},note =#{note},count =#{count},nowCount =#{nowCount}" +
            " where drugname= #{drugName}")
    Boolean updateByName(Drug drug);

    /**
     * 查询药品信息，距离过期时间一个月以内
     * @return
     */
    @Select("SELECT * FROM drug  WHERE TO_DAYS(NOW()) - TO_DAYS(madeTime)  > (totaltime-1)*30")
    List<Drug> selOuttime();

    /**
     * 根据药品id添加药品库存
     * @param count
     * @param id
     * @return
     */
    @Update("update drug set count = count + #{count},nowCount= nowCount + #{count} where id =#{id}")
    Boolean updateCount(int count,int id);
}
