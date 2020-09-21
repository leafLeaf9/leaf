package com.gousade.service;

import com.gousade.mapper.RoleMapper;
import com.gousade.mapper.RoleResourceMapper;
import com.gousade.mapper.UserRoleMapper;
import com.gousade.pojo.Resource;
import com.gousade.pojo.Role;
import com.gousade.pojo.RoleResource;
import com.gousade.utils.SaltUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

/**
 * 类说明:角色管理Service，并且测试@Transactional注解功能
 *
 * @Transactional 默认只回滚RuntimeException和Error，添加rollbackFor =
 * Exception.class可以让事物在遇到非运行时异常时也回滚
 * 参考https://www.cnblogs.com/clwydjgs/p/9317849.html
 * https://blog.csdn.net/nextyu/article/details/78669997
 * https://www.cnblogs.com/caoyc/p/5632963.html
 * https://www.cnblogs.com/xd502djj/p/10940627.html
 * webapp/img/Exception.png
 */

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    public List<Role> selectRoleList(Map<String, Object> map) {
        List<Role> list = roleMapper.selectRoleList(map);
        return list;
    }

    @Transactional
    public Map<String, Object> insertrole(Role role) {
        Map<String, Object> map = new HashMap<String, Object>();
//		role.setId(SaltUtil.getUUId());
        int i = roleMapper.insert(role);
		/*if (true) {//测试@Transactional注解是否生效，默认只回滚RuntimeException和Error
		    throw new RuntimeException("save 抛异常了");
		}*/
        if (i >= 1) {
            map.put("status", true);
            map.put("msg", "新增角色成功");
        } else {
            map.put("status", false);
            map.put("msg", "新增角色失败");
        }
        return map;
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> updaterole(Role role) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        int i = roleMapper.updateByPrimaryKey(role);
		/*if (true) {//测试@Transactional注解是否生效，默认只回滚RuntimeException和Error，添加rollbackFor = Exception.class可以让事物在遇到非运行时异常时也回滚
		    throw new IOException("save 抛异常了");
		}*/
        if (i >= 1) {
            map.put("status", true);
            map.put("msg", "编辑角色成功");
        } else {
            map.put("status", false);
            map.put("msg", "编辑角色失败");
        }
        return map;
    }

    public Map<String, Object> deleteBatchIds(List<? extends String> idList) {
        Map<String, Object> map = new HashMap<String, Object>();
        int i = roleMapper.deleteBatchIds(idList);
        if (i >= 1) {
            map.put("status", true);
            map.put("msg", "删除角色成功");
        } else {
            map.put("status", false);
            map.put("msg", "删除角色失败");
        }
        return map;
    }

    public Map<String, Object> selectResourceIdListByRoleId(String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> resources = roleMapper.selectResourceIdListByRoleId(id);
        resources.removeAll(Collections.singleton(null));// 由于角色无资源时返回的是[null]，把它变为[]
        map.put("status", true);
        map.put("resultData", resources);
        return map;
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> saveRoleAuthorize(String roleId, String[] resourceIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        roleResourceMapper.deleteByRoleId(roleId);
        List<RoleResource> list = new ArrayList<RoleResource>();
        if (resourceIds != null) {
            String[] resourceIdlist = resourceIds;
            for (String resourceId : resourceIdlist) {
                RoleResource roleResource = new RoleResource();
                roleResource.setId(SaltUtil.getUUId());
                roleResource.setRoleid(roleId);
                roleResource.setResourceid(resourceId);
                list.add(roleResource);
            }
            int i = roleResourceMapper.insertBatch(list);
            if (i >= 1) {
                map.put("status", true);
                map.put("msg", "角色授权成功。");
            } else {
                map.put("status", false);
                map.put("msg", "角色授权失败。");
            }
        } else {
            map.put("status", true);
            map.put("msg", "角色授权成功。");
        }
        return map;
    }

    public List<Role> getRoles() {
        return roleMapper.getRoles();
    }

    public Set<String> findRoleNamesByUserId(String userId) {
        List<String> roleIdList = userRoleMapper.findRoleIdsByUserId(userId);
        Set<String> roles = new HashSet<String>();
        if (roleIdList != null && roleIdList.size() > 0) {
            List<Role> rolels = roleMapper.findByIds(roleIdList);
            if (rolels != null && rolels.size() > 0) {
                for (Role role : rolels) {
                    roles.add(role.getName());
                }
            }
        }
        return roles;
    }

    public Set<String> findUrlsByUserId(String userId) {
        List<String> roleIdList = userRoleMapper.findRoleIdsByUserId(userId);
        Set<String> urls = new HashSet<String>();
        if (roleIdList != null && roleIdList.size() > 0) {
            List<Resource> resources = roleMapper.findAllResourcesByRoleIds(roleIdList);
            if (resources != null && resources.size() > 0) {
                for (Resource resource : resources) {
                    if (!StringUtils.isBlank(resource.getUrl())) {
                        urls.add(resource.getUrl());
                    }
                }
            }
        }
        return urls;
    }
}
