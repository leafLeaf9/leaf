package com.gousade.mybatisPlus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author woxigsd@gmail.com
 * @date 2020-8-26 10:59:08
 * @description 实现mybatis-plus自动填充
 */
@Component
public class MyBatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
//        this.setFieldValByName("createTime", new Date(), metaObject);
//        this.setFieldValByName("updateTime", new Date(), metaObject);
//        this.setFieldValByName("delflag",false,metaObject);
        this.setFieldValByName("version", 1, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
        /**
         * 此处setdelflag，但是在调用roleMapper.updateById(role)前未role.setDelflag(1)以及role类中没添加@TableField(fill
         * = FieldFill.UPDATE) 的话，最后执行的sql里也不会包含delflag
         */
//		this.setFieldValByName("delflag", "1", metaObject);
    }

}
