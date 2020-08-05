package com.gousade.mapper;

import com.gousade.pojo.Menu;
import com.gousade.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
	
	public User toCheckUser(@Param(value="userId") String userId,
				@Param(value="password") String password);
	
	public User SelectUserByLoginName(String userId);
	/**
	 * 注册用户
	 * @param map
	 * @return
	 */
	public int regist(Map<String, Object> map);
	/**
	 * 查询用户列表
	 * @param paraMap
	 * @return
	 */
	public List<Map<String, Object>> queryuserlist(Map<String, Object> paraMap);
	
	public List<User> selectUserList(Map<String, Object> searchMap);
	
	public long queryuserlistcnt(Map<String, Object> map);
	/**
	 * 新增用户
	 * @param map
	 * @return
	 */
	public int insertuser(Map<String, Object> map);
	/**
	 * 修改用户信息
	 * @param map
	 * @return
	 */
	public int updateuser(Map<String, Object> map);
	/**
	 * 删除用户信息
	 * @param map
	 * @return
	 */
	public int delusers(Map<String, Object> map);
	/**
	 *重置密码
	 * @param map
	 * @return
	 */
	public int resetpsd(Map<String, Object> map);
	
	/**
	 *根据用户名查询手机号
	 * @param map
	 * @return
	 */
	public Map<String, Object> selectphonenum(Map<String, Object> map);
	
	/**
	 *恢复初始密码
	 * @param map
	 * @return
	 */
	public int cleanpsd(Map<String, Object> map);
	
	/**
	 * 设置申报规则
	 * @param map
	 * @return
	 */
	public  int setrule(Map<String, String> map);
	/**
	 * 填写申报书
	 * @param map
	 * @return
	 */
	public int declare(Map<String, String> map);
	/**
	 * 查询项目列表
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> queryprojlist(Map<String, Object> map);
	/**
	 *根据项目ID查询站内消息接收人ID
	 * @param map
	 * @return
	 */
	public Map<String ,Object> selectidbyproj(Map<String, Object> map);
	/**
	/**
	 *根据ID修改项目状态
	 * @param map
	 * @return
	 */
	public int firstpassbyid(Map<String, Object> map);
	public int secondpassbyid(Map<String, Object> map);
	public int thirdpassbyid(Map<String, Object> map);
	public int dopassbyid(Map<String, Object> map);
	/**
	 * 根据项目ID和专家ID给项目分配专家
	 * @param map
	 * @return
	 */
	public int assignexperts(Map<String, Object> map);
	/**
	 * 保存专家的打分和评语
	 * @param map
	 * @return
	 */
	
	public int savegrading(Map<String, Object> map);
	
	/**
	 * 根据ID查询审核该项目的所有专家是否已经全部打分完成
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> querygradestate(Map<String, Object> map);
	/**
	 * 根据角色ID获取菜单
	 * @param map
	 * @return
	 */
	public List<Menu> listMenuByRoleId(Map<String, Object> map);
	
	/**
	 * 根据用户ID获取角色ID
	 * @param map
	 * @return
	 */
	public Map<String, Object> getroleidByuid(Map<String, Object> map);
	
	/**
	 * 根据用户ID获取密码
	 * @param map
	 * @return
	 */
	public Map<String, Object> getpsdByuid(Map<String, Object> map);
	
	/**
	 * 更新登录时间	 
	 */
	public int updateLoginTime(String userId);

	public int updateUserById(User entity);

	int insert(User user);
}
