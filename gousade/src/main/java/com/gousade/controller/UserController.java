package com.gousade.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
import com.gousade.pojo.Menu;
import com.gousade.pojo.User;
import com.gousade.service.SmsDemo;
import com.gousade.service.UserService;

import edu.hit.ir.ltp4j.Segmentor;

@Controller
//添加restcontroller注解之后，return"main"不能再返回main.jsp，需要改写成ModelAndView mv = new ModelAndView("main"); return mv;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/toLogin")
	public ModelAndView toLogin(@RequestParam(value="userId") String userId,
			@RequestParam(value="password") String password,Model model,HttpServletRequest request){
		User user = userService.toCheck(userId, password);
		request.getSession().setAttribute("u", userId);
		request.getSession().setMaxInactiveInterval(15*60);//以秒为单位，即在没有活动15分钟后，session将失效
		// Segmentor sentenceSplitApp= new Segmentor(); //测试ltp4j包是否可用
		if(user==null){
			model.addAttribute("msg", "用户名或密码错误!");
			ModelAndView mv = new ModelAndView("login");
			return mv;
		}else{
			model.addAttribute("user_name", user.getUserName());
			ModelAndView mv = new ModelAndView("main");
			return mv;
		}
	}
	
	/**
	 * 注册用户
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public Map<String,Object> regist(@RequestBody Map<String,Object> map){				
		return userService.regist(map);
		}
	
	
	/**
	 * 初始化菜单
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/listAdminMenu",method=RequestMethod.POST)
	public List<Menu> listAdminMenu(@RequestBody Map<String,Object> map){
		Map<String, Object> rolemap = new HashMap<String, Object>();
		rolemap=userService.getroleidByuid(map);
		return userService.listAdminMenuByRole(rolemap);
	}
	
	
	
	@RequestMapping(value="/queryuserlist",method=RequestMethod.POST)
	public List<Map<String,String>> queryuserlist(@RequestBody Map<String,String> map){
		/*GridJson gs=new GridJson();
		List<User> list = iMessagePushBizc.queryMessagePushList(map);
		int cnt = iMessagePushBizc.queryMessagePushListCnt(map);
		gs.setRows(list);
		gs.setTotal(cnt);*/
		return userService.queryuserlist(map);
	}
	/**
	 * 新增用户
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/insertuser",method=RequestMethod.POST)
	public Map<String,Object> insertuser(@RequestBody Map<String,Object> map){
		/*GridJson gs=new GridJson();
		List<User> list = iMessagePushBizc.queryMessagePushList(map);
		int cnt = iMessagePushBizc.queryMessagePushListCnt(map);
		gs.setRows(list);
		gs.setTotal(cnt);*/
		return userService.insertuser(map);
	}
	/**
	 * 根据ID修改用户信息
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/updateuser",method=RequestMethod.POST)
	public Map<String,Object> updateuser(@RequestBody Map<String,Object> map){
		
		return userService.updateuser(map);
	}
	/**
	 * 根据ID删除用户信息
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/delusers",method=RequestMethod.POST)
	public Map<String,Object> delusers(@RequestBody Map<String,Object> map){
		
		return userService.delusers(map);
	}
	/**
	 * 重置密码
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/resetpsd",method=RequestMethod.POST)
	public Map<String,Object> resetpsd(@RequestBody Map<String,Object> map){
		
		return userService.resetpsd(map);
	}
	
	/**
	 * 设置申报规则
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/setrule",method=RequestMethod.POST)
	public String setrule(@RequestBody Map<String,String> map){
		String result= userService.setrule(map);
		 Map<String,String> resultmap = new HashMap<String,String>();
		 resultmap.put("result", result);
		String  param= JSON.toJSONString(resultmap);
		return param;
	}
	/**
	 * 填写申报书
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/declare",method=RequestMethod.POST)
	public String declare(@RequestBody Map<String,String> map){
		String result= userService.declare(map);
		 Map<String,String> resultmap = new HashMap<String,String>();
		 resultmap.put("result", result);
		String  param= JSON.toJSONString(resultmap);
		return param;
	}
	/**
	 * 查询项目列表
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/queryprojlist",method=RequestMethod.POST)
	public List<Map<String,Object>> queryprojlist(@RequestBody Map<String,Object> map){
		/*String result= userService.queryprojlist(map);
		 Map<String,String> resultmap = new HashMap<String,String>();
		 resultmap.put("result", result);
		String  param= JSON.toJSONString(resultmap);
		return param;*/
		return userService.queryprojlist(map);
	}
	
	/**
	 * 根据ID修改项目状态
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/firstpassbyid",method=RequestMethod.POST)
	public Map<String,Object> firstpassbyid(@RequestBody Map<String,Object> map){
		/*String result= userService.queryprojlist(map);
		 Map<String,String> resultmap = new HashMap<String,String>();
		 resultmap.put("result", result);
		String  param= JSON.toJSONString(resultmap);
		return param;*/
		
		//System.out.println(received);
		return userService.firstpassbyid(map);
	}
	/**
	 * 根据前端传来的ID和状态修改项目状态
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/dopassbyid",method=RequestMethod.POST)
	public Map<String,Object> dopassbyid(@RequestBody Map<String,Object> map){
		/*String result= userService.queryprojlist(map);
		 Map<String,String> resultmap = new HashMap<String,String>();
		 resultmap.put("result", result);
		String  param= JSON.toJSONString(resultmap);
		return param;*/
	
		return userService.dopassbyid(map);
	}
	
	/**
	 * 根据项目ID和专家ID给项目分配专家
	 * @param map
	 * @return
	 */
	
	@RequestMapping(value="/assignexperts",method=RequestMethod.POST)
	public Map<String,Object> assignexperts(@RequestBody Map<String,Object> map){
		/*String result= userService.queryprojlist(map);
		 Map<String,String> resultmap = new HashMap<String,String>();
		 resultmap.put("result", result);
		String  param= JSON.toJSONString(resultmap);
		return param;*/
		
		return userService.assignexperts(map);
	}
	/**
	 * 保存专家的打分和评语
	 * @param map
	 * @return
	 */
	
	@RequestMapping(value="/savegrading",method=RequestMethod.POST)
	public Map<String,Object> savegrading(@RequestBody Map<String,Object> map){
		/*String result= userService.queryprojlist(map);
		 Map<String,String> resultmap = new HashMap<String,String>();
		 resultmap.put("result", result);
		String  param= JSON.toJSONString(resultmap);
		return param;*/
		
		return userService.savegrading(map);
	}
	
	
	
	/**
	 *获取随机短信验证码
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getcode",method=RequestMethod.POST)
	public Map<String,Object> getcode(@RequestBody Map<String,Object> map){
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			SmsDemo.setNewcode();
			int  newcode =SmsDemo.getNewcode(); 
			
			retMap=userService.selectphonenum(map);
			
			System.out.println("验证码是："+newcode);
			System.out.println("手机号是："+retMap.get("phonenumber"));
			SmsDemo.sendSms((String) retMap.get("phonenumber"),newcode);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retMap;
		
	}
	/**
	 *恢复初始密码
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/cleanpsd",method=RequestMethod.POST)
	public Map<String,Object> cleanpsd(@RequestBody Map<String,Object> map){
		
		return userService.cleanpsd(map);
			
		
		
	}
	
}
