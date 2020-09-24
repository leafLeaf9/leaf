package com.gousade.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gousade.annotation.OperationRecord;
import com.gousade.pojo.Role;
import com.gousade.service.RoleService;
import com.gousade.utils.GeneratePDFUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleService;

    @OperationRecord(operationNum = 0, operationDescription = "查询角色列表")
    @RequestMapping(value = "/selectRoleList", method = RequestMethod.POST)
    public Map<String, Object> selectRoleList(@RequestParam(value = "page", required = false) String page,
                                              @RequestParam(value = "rows", required = false) String rows, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        String name = request.getParameter("name");
        param.put("name", name);
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(rows));
        List<Role> list = roleService.selectRoleList(param);
        PageInfo<Role> pageInfo = new PageInfo<Role>(list);
        long total = pageInfo.getTotal();
        result.put("rows", list);
        result.put("total", total);
        return result;
    }

    @Cacheable(value = "redis@Cacheable")
    @RequestMapping(value = "/getRoles", method = RequestMethod.POST)
    public List<Role> getRoles() {
        List<Role> list = roleService.getRoles();
        return list;
    }

    /**
     * pdf预览工具方法
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/generatePDFTest", method = RequestMethod.POST)
    public void generatePDFTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 模板路径
        String templatePath = request.getServletContext().getRealPath("/")
                + "static\\template\\order\\feeOrderTemplate.pdf";
        log.info(templatePath);
        // 生成文件的路径
        String filePath = request.getServletContext().getRealPath("/") + "static\\template\\order\\"
                + System.currentTimeMillis() + ".pdf";
        log.info(filePath);
        Map<String, String> map = new HashMap<String, String>();
        map.put("realName", "名字0");
        map.put("mobile", "mobile1");
        map.put("orderType", "费用");
        map.put("expectPrice", "1000");
        map.put("name_1", "物品1");
        map.put("name_2", "物品2");
        map.put("num_1", "1");
        map.put("num_2", "2");
        map.put("price_1", "500");
        map.put("price_2", "250");
        map.put("realName_1", "名字1");
        map.put("realName_2", "名字2");
        map.put("suggestion_1", "同意");
        map.put("suggestion_2", "同意");
        map.put("realName_5", "名字1");
        map.put("remarksss", "名字1");
        GeneratePDFUtil.interviewReportPDF(response, templatePath, filePath, map, "费用单.pdf");
    }

    /**
     * 图片预览工具方法
     *
     * @param response
     * @param id
     * @throws IOException
     */
    @RequestMapping("/picPreview")
    public void picPreview(HttpServletResponse response, @RequestParam String id) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setContentType("image/jpeg");
//		String path = attach.getAttachmentPath();
        String path = "";
        FileInputStream fis = new FileInputStream(path);
        OutputStream os = response.getOutputStream();
        try {
            int count = 0;
            byte[] buffer = new byte[1024 * 1024];
            while ((count = fis.read(buffer)) != -1)
                os.write(buffer, 0, count);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null)
                os.close();
            if (fis != null)
                fis.close();
        }
    }

    @RequestMapping(value = "/saverole", method = RequestMethod.POST)
    public Map<String, Object> saverole(@RequestBody Role role) throws IOException {
        if (StringUtils.isBlank(role.getId())) {
            return roleService.insertrole(role);
        } else {
            return roleService.updaterole(role);
        }
    }

    @RequestMapping(value = "/deleterole", method = RequestMethod.POST)
    public Map<String, Object> deleterole(String[] ids) {
        List<String> list = Arrays.asList(ids);
        return roleService.deleteBatchIds(list);
    }

    @RequestMapping(value = "/selectResourceIdListByRoleId", method = RequestMethod.POST)
    public Map<String, Object> selectResourceIdListByRoleId(@RequestBody Role role) {
        String id = role.getId();
        return roleService.selectResourceIdListByRoleId(id);
    }

    @RequestMapping(value = "/saveRoleAuthorize", method = RequestMethod.POST)
    public Map<String, Object> saveRoleAuthorize(String roleId, String[] resourceIds) {
        return roleService.saveRoleAuthorize(roleId, resourceIds);
    }
}
