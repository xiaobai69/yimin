package cn.com.service.impl;

import cn.com.entity.Roleuser;
import cn.com.mapper.RoleuserMapper;
import cn.com.service.IRoleuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleuserServiceImpl implements IRoleuserService {

    @Autowired
    private RoleuserMapper roleuserMapper;

    @Override
    public Boolean insertInfo(Roleuser roleuser) {
        Boolean bool = false;
        if(roleuserMapper.insertInfo(roleuser)){

            bool = true;
        }

        return bool;
    }

    @Override
    public Roleuser selByName(String roleName) {
        return roleuserMapper.selByName(roleName);
    }

    @Override
    public Boolean updatePerms(Roleuser roleuser) {
        Boolean bool = false;
        if(roleuserMapper.updatePerms(roleuser)){
            bool = true;
        }

        return bool;
    }

    @Override
    public List<Roleuser> selAll() {

        return roleuserMapper.selAll();
    }

    @Override
    public Boolean deleteByName(String name) {
        return roleuserMapper.deleteByName(name);
    }
}
