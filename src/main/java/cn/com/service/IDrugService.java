package cn.com.service;

import cn.com.entity.Drug;

import java.util.List;

public interface IDrugService {

    /**
     * 查询所有药品信息
     * @return
     */
    List<Drug> selAll();

    /**
     * 根据药品名称查询本药品信息
     * @param name
     * @return
     */
    Drug selAllByName(String name);

    /**
     * 根据药品id查询本药品信息
     * @param id
     * @return
     */
    Drug selAllById(int id);

    /**
     * 药品信息模糊查询
     * @param info
     * @return
     */
    List<Drug> selByInfo(String info);

    /**
     * 根据药品类型模糊查询
     * @param type
     * @return
     */
    List<Drug> selByType(String type);

    /**
     * 根据药品备注信息查询
     * @return
     */
    List<Drug> selByNote();

    /**
     * 根据销量查询，降序排列
     * @return
     */
    List<Drug> selByNumber();

    /**
     * 根据药品名称药品销量+number，同时当前库存量-number
     * @param number
     * @param drugName
     * @return
     */
    Boolean updateNumber(int number,String drugName);


    /**
     * 根据药品名称修改售价
     * @param name
     * @return
     */
    Boolean updateSalByName(float money,String name);

    /**
     * 根据药品名称删除药品信息
     * @param name
     * @return
     */
    Boolean delByName(String name);

    /**
     * 添加药品信息
     * @param drug
     * @return
     */
    Boolean insertInfo(Drug drug);

    /**
     * 根据药品名称，更改药品库存信息
     * @param drug
     * @return
     */
    Boolean updateByName(Drug drug);

    /**
     * 查询药品信息，距离过期时间一个月以内
     * @return
     */
    List<Drug> selOuttime();

    /**
     * 根据药品id添加药品库存
     * @param count
     * @param id
     * @return
     */
    Boolean updateCount(int count,int id);
}
