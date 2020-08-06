package com.gousade.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gousade.controller.common.BaseController;
import com.gousade.pojo.Menu;
import com.gousade.pojo.User;
import com.gousade.service.UserService;
import com.gousade.utils.DataTablesPageUtil;


//添加restcontroller注解之后，return"main"不能再返回main.jsp，需要改写成ModelAndView mv = new ModelAndView("main"); return mv;
@RestController
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/admin/sysuser/selectByPrimaryKey",method=RequestMethod.POST)
	public User selectByPrimaryKey(String id){
		User user = userService.selectByPrimaryKey(id);
		return user;
	}
	
	@RequestMapping(value="/loginShiroUser",method = RequestMethod.POST)
	public ModelAndView loginShiroUser(@RequestParam(value="userId") String userId,
			@RequestParam(value="password") String password,Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
//		logger.info("进行账号"+userId+",密码验证"+password+".....");
    	UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(userId,password);
    	Subject subject = SecurityUtils.getSubject();
    	ModelAndView loginmv = new ModelAndView("login");
		try {
    		subject.login(usernamePasswordToken);   //完成shiro登录验证
    		User user=(User) subject.getPrincipal();
    		session.setAttribute("user", user);
    		session.setAttribute("u", userId);
    		session.setAttribute("user_name", user.getUserName());
    		session.setMaxInactiveInterval(15*60);//以秒为单位，即在没有活动15分钟后，session将失效
//    		session.setAttribute("clickId","home");
    		String currentUser = subject.getPrincipal().toString();
    		logger.info("当前登录的用户是："+currentUser);
    		ModelAndView mv = new ModelAndView("redirect:/admin/index");
    		return mv;
        }catch(UnknownAccountException uae){  
            logger.info("对用户[" + userId + "]进行登录验证..验证未通过,未知账户");  
            request.setAttribute("msg", "账户不存在");  
            return loginmv;//返回登录页面
        }catch(IncorrectCredentialsException ice){  
            logger.info("对用户[" + userId + "]进行登录验证..验证未通过,错误的凭证");  
            request.setAttribute("msg", "密码不正确");  
            return loginmv;//返回登录页面
        }catch(LockedAccountException lae){  
            logger.info("对用户[" + userId + "]进行登录验证..验证未通过,账户已锁定");  
            request.setAttribute("msg", "账户已锁定");
            return loginmv;//返回登录页面
        }catch(ExcessiveAttemptsException eae){  
            logger.info("对用户[" + userId + "]进行登录验证..验证未通过,错误次数过多");  
            request.setAttribute("msg", "用户名或密码错误次数过多");
            return loginmv;//返回登录页面
        }catch(AuthenticationException ae){  
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
            logger.info("对用户[" + userId + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();  
            request.setAttribute("msg", "用户名或密码不正确");  
            return loginmv;//返回登录页面
        }

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
