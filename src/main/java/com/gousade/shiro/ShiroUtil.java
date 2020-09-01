package com.gousade.shiro;

import com.gousade.pojo.User;
import org.apache.shiro.SecurityUtils;

/**
 * @author: woxigousade <woxigsd@gmail.com>
 * @date: 2020/9/1/0001 21:19
 * @description: shiro util class
 */
public class ShiroUtil {

    public static User getShiroSessionUser() {
        if (SecurityUtils.getSubject() == null || SecurityUtils.getSubject().getPrincipal() == null) {
            return null;
        }
        return (User) SecurityUtils.getSubject().getPrincipal();
    }
}
