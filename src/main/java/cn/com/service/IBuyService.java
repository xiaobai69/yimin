package cn.com.service;

import cn.com.entity.Buy;

import java.util.List;
import java.util.Map;

public interface IBuyService {
    /**
     * 添加购物车信息
     * @param buy
     * @return
     */
    Boolean insertInfo(Buy buy);

    /**
     * 根据药品id删除购物车信息
     * @param did
     * @return
     */
    Boolean deleteById(int did);

    /**
     * 查询购物车信息
     * @return
     */
    List<Map<String,Object>> selAllByUid(int uid,int state);

    /**
     * 根据用户id和药品id查询购物车信息
     * @param did
     * @return
     */
    Buy selByDid(int did,int uid);

    /**
     * 根据药品id更改药品状态
     * @param did
     * @return
     */
    Boolean updateState(int did);

    /**
     * 查询线上交易信息
     * @return
     */
    List<Map<String,Object>> selAll();

    /**
     * 根据id获取信息
     * @param id
     * @return
     */
    Buy selById(int id);

    /**
     * 根据购物车id删除信息
     * @param id
     * @return
     */
    Boolean deleteId(int id);
}
