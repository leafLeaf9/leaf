package com.gousade.controller;

import com.gousade.pojo.Resource;
import com.gousade.pojo.Tree;
import com.gousade.pojo.User;
import com.gousade.service.ResourceService;
import com.gousade.shiro.ShiroUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/admin/resource", method = RequestMethod.POST)
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    public Object tree() {
        User user = ShiroUtil.getShiroSessionUser();
        List<Resource> list = resourceService.selectTree(user);
        return list;
    }

    @RequestMapping(value = "/selectResourceList", method = RequestMethod.POST)
    public List<Resource> selectResourceList() {
        return resourceService.selectResourceList();
    }

    @RequestMapping(value = "/selectAllTree", method = RequestMethod.POST)
    public List<Tree> selectAllTree() {
        return resourceService.selectAllTree();
    }

    /**
     * 删除redis@Tree缓存下的所有值
     */
    @CacheEvict(value = "redis@Tree", allEntries = true)
    @RequestMapping(value = "/saveresource", method = RequestMethod.POST)
    public Map<String, Object> saveresource(Resource resource) {
        if (StringUtils.isBlank(resource.getId())) {
            return resourceService.insertresource(resource);
        } else {
            return resourceService.updateresource(resource);
        }
    }

    @RequestMapping(value = "/deleteresource", method = RequestMethod.POST)
    public Map<String, Object> deleteresource(String[] ids) {
        List<String> list = Arrays.asList(ids);
        return resourceService.deleteBatchIds(list);
    }
}
