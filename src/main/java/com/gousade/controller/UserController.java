package com.gousade.controller;

import com.gousade.controller.common.BaseController;
import com.gousade.pojo.Menu;
import com.gousade.pojo.User;
import com.gousade.service.UserService;
import com.gousade.utils.DataTablesPageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
//添加restcontroller注解之后，return"main"不能再返回main.jsp，需要改写成ModelAndView mv = new ModelAndView("main"); return mv;
@RestController
@RequestMapping(value = "/admin/sysUser", method = RequestMethod.POST)
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/selectByPrimaryKey",method=RequestMethod.POST)
	public User selectByPrimaryKey(String id){
		User user = userService.selectByPrimaryKey(id);
		return user;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/loginShiroUser",method = RequestMethod.POST)
	public Object loginShiroUser(@RequestParam(value="userId") String userId,
			@RequestParam(value="password") String password,@RequestParam(value = "rememberMe", defaultValue = "0") String rememberMe,
			Model model,HttpServletRequest request) {
    	UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(userId,password);
    	Subject subject = SecurityUtils.getSubject();
    	ModelAndView loginmv = new ModelAndView("login");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
    		subject.login(usernamePasswordToken);   //完成shiro登录验证
    		User user=(User) subject.getPrincipal();
			result.put("status", true);
			String url =request.getServerPort()+request.getContextPath() + "/admin/index";
			log.info(url);
			result.put("msg", "/admin/index");//{}特殊符号要转义
//    		ModelAndView mv = new ModelAndView("redirect:/admin/index");
//    		return mv;
        }catch(UnknownAccountException uae){  
            log.info("对用户[" + userId + "]进行登录验证..验证未通过,未知账户");
			result.put("status", false);
			result.put("msg", "账号不存在");
        }catch(IncorrectCredentialsException ice){  
            log.info("对用户[" + userId + "]进行登录验证..验证未通过,错误的凭证");
			result.put("status", false);
			result.put("msg", "密码不正确");
            /*request.setAttribute("msg", "密码不正确");
			return renderError("密码不正确");*/
        }catch(LockedAccountException lae){  
            log.info("对用户[" + userId + "]进行登录验证..验证未通过,账户已锁定");
			result.put("status", false);
			result.put("msg", "账号被锁定");
        }catch(ExcessiveAttemptsException eae){  
            log.info("对用户[" + userId + "]进行登录验证..验证未通过,错误次数过多");
			result.put("status", false);
			result.put("msg", "密码错误错误次数过多");
        }catch(AuthenticationException ae){  
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
            log.info("对用户[" + userId + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
			result.put("status", false);
			result.put("msg", "用户名或密码不正确");
        }
		return result;
	}
	
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public Object logout(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            res.put("status", true);
            res.put("msg", "退出成功");
        } catch (Exception e) {
            res.put("status", false);
        }
		return res;
	}
	
	/**
	 * 注册用户
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/ShiroRegist",method=RequestMethod.POST)
	public Map<String,Object> ShiroRegist(@RequestBody Map<String,Object> map){				
		return userService.ShiroRegist(map);
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
	
	@RequestMapping(value="/selectUserList",method=RequestMethod.POST)
	public DataTablesPageUtil<User> selectUserList(HttpServletRequest request){
		DataTablesPageUtil<User> dataTables = new DataTablesPageUtil<User>(request);
		dataTables = userService.selectUserList(dataTables);
		return dataTables;
	}

	@RequestMapping(value = "/userEdit", method = RequestMethod.POST)
	public Object userEdit(User user){
		boolean b=false;
		if(user.getId()==null||"".equals(user.getId())){
			b = userService.insert(user);
		}else {
			b = userService.updateUserById(user);
		}
		return renderBoolean(b);
	}
	
	@RequestMapping(value="/admin/sysuser/deleteUserByid",method=RequestMethod.POST)
	public Object deleteUserByid(@RequestParam Map<String,Object> map){
		boolean b = userService.deleteUserByid(map);
		return renderBoolean(b);
	}
	
}
