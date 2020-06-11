package com.gousade.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gousade.pojo.Role;
import com.gousade.service.RoleService;
import com.gousade.utils.GeneratePDFUtil;

@RestController
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/selectRoleList",method=RequestMethod.POST)
	public Map<String,Object> selectRoleList(@RequestParam(value="page", required=false) String page, 
            @RequestParam(value="rows", required=false) String rows,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		String name = request.getParameter("name");
		param.put("name",name);
		PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(rows));
		List<Role> list = roleService.selectRoleList(param);
		PageInfo<Role> pageInfo = new PageInfo<Role>(list);
		long total = pageInfo.getTotal();
		result.put("rows", list);
		result.put("total", total);
		return result;
	}
	
	@RequestMapping(value="/generatePDFTest",method=RequestMethod.POST)
	public void generatePDFTest(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//模板路径
		String templatePath=request.getServletContext().getRealPath("/")+"static\\template\\order\\feeOrderTemplate.pdf";
		logger.info(templatePath);
		//生成文件的路径
		String filePath=request.getServletContext().getRealPath("/")+"static\\template\\order\\"+System.currentTimeMillis()+".pdf";
		logger.info(filePath);
		Map<String, String> map=new HashMap<String, String>();
		map.put("realName", "名字0");
		map.put("mobile","mobile1");
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
		GeneratePDFUtil.interviewReportPDF(response, templatePath, filePath, map,"费用单.pdf");
	}
	
	@RequestMapping(value="/saverole",method=RequestMethod.POST)
	public Map<String,Object> saverole(@RequestBody Role role) throws IOException{
		if(StringUtils.isBlank(role.getId())){
			return roleService.insertrole(role);
		}else{
			return roleService.updaterole(role);
		}		
	}
	
	@RequestMapping(value="/deleterole",method=RequestMethod.POST)
	public Map<String,Object> deleterole(String[] ids){
		List<String> list=Arrays.asList(ids);
		return roleService.deleteBatchIds(list);
	}
	
	@RequestMapping(value="/selectResourceIdListByRoleId",method=RequestMethod.POST)
	public Map<String,Object> selectResourceIdListByRoleId(@RequestBody Role role){
		String id =  role.getId();
		return roleService.selectResourceIdListByRoleId(id);
	}
	
	@RequestMapping(value="/saveRoleAuthorize",method=RequestMethod.POST)
	public Map<String,Object> saveRoleAuthorize(String roleId, String[] resourceIds){
		return roleService.saveRoleAuthorize(roleId,resourceIds);
	}
}
