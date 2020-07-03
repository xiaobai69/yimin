package cn.com.service.impl;

import cn.com.entity.Buy;
import cn.com.mapper.BuyMapper;
import cn.com.service.IBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BuyServiceImpl implements IBuyService {

    @Autowired
    private BuyMapper buyMapper;

    @Override
    public Boolean insertInfo(Buy buy) {
        return buyMapper.insertInfo(buy);
    }

    @Override
    public Boolean deleteById(int did) {
        return buyMapper.deleteById(did);
    }

    @Override
    public List<Map<String, Object>> selAllByUid(int uid,int state) {
        return buyMapper.selAllByUid( uid,state);
    }

    @Override
    public Buy selByDid(int did,int uid) {
        return buyMapper.selByDid(did,uid);
    }

    @Override
    public Boolean updateState(int did) {
        return buyMapper.updateState(did);
    }

    @Override
    public List<Map<String, Object>> selAll() {
        return buyMapper.selAll();
    }

    @Override
    public Buy selById(int id) {
        return buyMapper.selById(id);
    }

    @Override
    public Boolean deleteId(int id) {
        return buyMapper.deleteId(id);
    }
}
