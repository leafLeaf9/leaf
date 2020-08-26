package com.gousade.mybatisPlus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gousade.mapper.RoleMapper;
import com.gousade.mapper.UserMapper;
import com.gousade.pojo.Role;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusTest {

    @SuppressWarnings("unused")
	@Autowired
    private UserMapper userMapper;
    
    @Autowired
    private RoleMapper roleMapper;
    
    @Test
    public void findAll() {
    	roleMapper.selectList(null);
    }
    
    @Test
    public void insertrole() {
    	Role role = new Role();
//    	role.setId(SaltUtil.generateUUId());
    	role.setName("测试mybatisplus角色3");
    	role.setSeq(10);
    	roleMapper.insert(role);
    }
    
    @Test
    public void updaterole() {
    	Role role = new Role();
    	role.setId("27f83817878649cb941b2c4fe71de12e");
    	role.setRemarks("测试mp remarks");
    	role.setSeq(99);
    	roleMapper.updateById(role);
    }
}