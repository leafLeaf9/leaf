package com.gousade.mybatisPlus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gousade.mapper.RoleMapper;
import com.gousade.mapper.UserMapper;
import com.gousade.pojo.Role;
import com.gousade.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void findAll() {
        userMapper.selectList(new QueryWrapper<User>().lambda().like(User::getUserName, "测试"));

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
    public void updateroleById() {
        Role role = new Role();
        role.setId("27f83817878649cb941b2c4fe71de12e");
        role.setRemarks("测试mp remarks");
        role.setSeq(99);
        roleMapper.updateById(role);
    }

    @Test
    public void updaterole() {
        Role role = new Role();
        role.setId("27f83817878649cb941b2c4fe71de12e");
        role.setRemarks("测试mp remarks");
        role.setSeq(99);
        role.setName("测试seta");
        roleMapper.update(role, Wrappers.<Role>lambdaUpdate().set(Role::getName, "测试setz").eq(Role::getId,
                "27f83817878649cb941b2c4fe71de12e"));
    }

    @Test
    public void testDeleteByid() {
        roleMapper.deleteById("19b9914b6c9a342f92377783422d8430");
    }

    @Test
    public void testDeleteBatchIds() {
        roleMapper
                .deleteBatchIds(Arrays.asList("27f83817878649cb941b2c4fe71de12e", "28438d95153646b1ae8a7ee1338dce22"));
    }

    @Test
    public void testSelectQuery() {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
//        wrapper.eq("name","测试mybatisplus角色3");
//        wrapper.likeLeft("name","测试");
        wrapper.like("name", "测试");
        wrapper.orderByAsc("seq", "create_time");
        // 指定要查询的列
        wrapper.select("id", "name", "seq", "create_time");
        List<Role> list = roleMapper.selectList(wrapper);
        log.info(list.toString());
    }

    @Test
    public void testOptimisticLocker() {
        // 根据id查询数据
        User user = userMapper.selectById("d1411e5c58a70c44d72ca016d1acc183");
        // 进行修改
        user.setRemark("备注");
        userMapper.updateById(user);
    }

    /**
     * 批量更新带乐观锁
     * <p>
     * update(et,ew) et:必须带上version的值才会触发乐观锁
     */
    @Test
    public void testUpdateByEntitySucc() {
        QueryWrapper<User> ew = new QueryWrapper<>();
        ew.eq("version", 2);
        int count = userMapper.selectCount(ew);

        User entity = User.builder().build();
        entity.setVersion(2);

        assertEquals(count, userMapper.update(entity, null), "updated records should be same");
        ew = new QueryWrapper<>();
        ew.eq("version", 2);
        assertEquals(0, userMapper.selectCount(ew).intValue(), "No records found with version=1");
        ew = new QueryWrapper<>();
        ew.eq("version", 3);
        assertEquals(count, userMapper.selectCount(ew).intValue(),
                "All records with version=1 should be updated to version=2");
    }
}