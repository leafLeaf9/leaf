package cn.edu.zstu.manage.pojo;

import lombok.Data;
import java.util.List;
/**
 * 菜单
 *
 * @author liuyanzhao
 */

public class Menu {
   

	/**
     * 菜单ID
     */
    private Integer id;
    /**
     * 父级菜单ID
     */
    private Integer pid;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单类型：top_menu, main_menu, admin_menu
     */
    private String type;
    /**
     * 菜单URL
     */
    private String url;
   
    private String state; 
    
    private String created;
    
    private String updated;
    
    /**
     * 子菜单Menu列表
     */
    
    private List<Menu> childMenu;
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public List<Menu> getChildMenu() {
		return childMenu;
	}

	public void setChildMenu(List<Menu> childMenu) {
		this.childMenu = childMenu;
	}
}