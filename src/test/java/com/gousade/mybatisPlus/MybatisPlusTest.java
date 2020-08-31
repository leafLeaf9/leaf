package com.gousade.mybatisPlus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gousade.mapper.RoleMapper;
import com.gousade.mapper.UserMapper;
import com.gousade.pojo.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@Slf4j
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

    @Test
    public void testDeleteByid() {
        roleMapper.deleteById("19b9914b6c9a342f92377783422d8430");
    }

    @Test
    public void testDeleteBatchIds() {
        roleMapper.deleteBatchIds(Arrays.asList("27f83817878649cb941b2c4fe71de12e", "28438d95153646b1ae8a7ee1338dce22"));
    }

    @Test
    public void testSelectQuery() {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
//        wrapper.eq("name","测试mybatisplus角色3");
//        wrapper.likeLeft("name","测试");
        wrapper.like("name","测试");
        wrapper.orderByAsc("seq","create_time");
        //指定要查询的列
        wrapper.select("id","name","seq","create_time");
        List<Role> list= roleMapper.selectList(wrapper);
        log.info(list.toString());
    }
}