package cn.com.service.impl;

import cn.com.entity.Drug;
import cn.com.mapper.DrugMapper;
import cn.com.service.IDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugServiceImpl implements IDrugService {

    @Autowired
    private DrugMapper drugMapper;

    @Override
    public List<Drug> selAll() {
        return drugMapper.selAll();
    }

    @Override
    public Drug selAllByName(String name) {
        return drugMapper.selAllByName(name);
    }

    @Override
    public Drug selAllById(int id) {
        return drugMapper.selAllById(id);
    }

    @Override
    public List<Drug> selByInfo(String info) {
        return drugMapper.selByInfo(info);
    }

    @Override
    public List<Drug> selByType(String type) {
        return drugMapper.selByType(type);
    }


    @Override
    public List<Drug> selByNote() {
        return drugMapper.selByNote();
    }

    @Override
    public List<Drug> selByNumber() {
        return drugMapper.selByNumber();
    }

    @Override
    public Boolean updateNumber(int number, String drugName) {
        return drugMapper.updateNumber(number, drugName);
    }

    @Override
    public Boolean updateSalByName(float money, String name) {
        return drugMapper.updateSalByName(money, name);
    }

    @Override
    public Boolean delByName(String name) {

        return drugMapper.delByName(name);
    }

    @Override
    public Boolean insertInfo(Drug drug)
    {
        return drugMapper.insertInfo(drug);
    }

    @Override
    public Boolean updateByName(Drug drug) {

        return drugMapper.updateByName(drug);
    }

    @Override
    public List<Drug> selOuttime() {
        return drugMapper.selOuttime();
    }

    @Override
    public Boolean updateCount(int count, int id) {
        return drugMapper.updateCount(count, id);
    }
}
