package com.changgou.user.service.impl;

import com.changgou.user.dao.AddressMapper;
import com.changgou.user.service.AddressService;
import com.changgou.user.pojo.Address;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 查询全部列表
     * @return
     */
    @Override
    public List<Address> findAll() {
        return addressMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public Address findById(Integer id){
        return  addressMapper.selectByPrimaryKey(id);
    }


    /**
     * 增加
     * @param address
     */
    @Override
    public void add(Address address){
        addressMapper.insert(address);
    }


    /**
     * 修改
     * @param address
     */
    @Override
    public void update(Address address){
        addressMapper.updateByPrimaryKey(address);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        addressMapper.deleteByPrimaryKey(id);
    }


    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<Address> findList(Map<String, Object> searchMap){
        Example example = createExample(searchMap);
        return addressMapper.selectByExample(example);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Address> findPage(int page, int size){
        PageHelper.startPage(page,size);
        return (Page<Address>)addressMapper.selectAll();
    }

    /**
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<Address> findPage(Map<String,Object> searchMap, int page, int size){
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        return (Page<Address>)addressMapper.selectByExample(example);
    }

    @Override
    public List<Address> list(String username) {
        Address address = new Address();
        address.setUsername(username);
        List<Address> addressList = addressMapper.select(address);
        return addressList;
    }

    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Address.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 用户名
            if(searchMap.get("username")!=null && !"".equals(searchMap.get("username"))){
                criteria.andEqualTo("username",searchMap.get("username"));
           	}
            // 省
            if(searchMap.get("provinceid")!=null && !"".equals(searchMap.get("provinceid"))){
                criteria.andLike("provinceid","%"+searchMap.get("provinceid")+"%");
           	}
            // 市
            if(searchMap.get("cityid")!=null && !"".equals(searchMap.get("cityid"))){
                criteria.andLike("cityid","%"+searchMap.get("cityid")+"%");
           	}
            // 县/区
            if(searchMap.get("areaid")!=null && !"".equals(searchMap.get("areaid"))){
                criteria.andLike("areaid","%"+searchMap.get("areaid")+"%");
           	}
            // 电话
            if(searchMap.get("phone")!=null && !"".equals(searchMap.get("phone"))){
                criteria.andLike("phone","%"+searchMap.get("phone")+"%");
           	}
            // 详细地址
            if(searchMap.get("address")!=null && !"".equals(searchMap.get("address"))){
                criteria.andLike("address","%"+searchMap.get("address")+"%");
           	}
            // 联系人
            if(searchMap.get("contact")!=null && !"".equals(searchMap.get("contact"))){
                criteria.andLike("contact","%"+searchMap.get("contact")+"%");
           	}
            // 是否是默认 1默认 0否
            if(searchMap.get("isDefault")!=null && !"".equals(searchMap.get("isDefault"))){
                criteria.andEqualTo("isDefault",searchMap.get("isDefault"));
           	}
            // 别名
            if(searchMap.get("alias")!=null && !"".equals(searchMap.get("alias"))){
                criteria.andLike("alias","%"+searchMap.get("alias")+"%");
           	}

            // id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }

        }
        return example;
    }

}
