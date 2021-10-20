package com.gousade.service;

import com.gousade.mapper.UserMapper;
import com.gousade.mapper.UserRoleMapper;
import com.gousade.pojo.User;
import com.gousade.pojo.UserRole;
import com.gousade.pojo.util.AttachmentGeneral;
import com.gousade.utils.DataTablesPageUtil;
import com.gousade.utils.OssUtil;
import com.gousade.utils.SaltUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Resource
    private OssUtil ossUtil;

    public User selectByPrimaryKey(String id) {
        User user = userMapper.selectById(id);
        List<String> roleIdList = userRoleMapper.findRoleIdsByUserId(user.getId());
        user.setRoleIds(String.join(",", roleIdList));
        return user;
    }

    public User SelectUserByLoginName(String userId) {
        return userMapper.SelectUserByLoginName(userId);
    }

    public Map<String, Object> regist(Map<String, Object> map) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        try {
            String firstpsd = (String) map.get("password");
            String uid = SaltUtil.getUUId();
            String uidsalt = DigestUtils.md5Hex(uid);
            String psd = firstpsd.concat(uidsalt);
            String password = DigestUtils.md5Hex(psd);
            map.put("salt", uid);
            map.put("password", password);
            int i = userMapper.regist(map);
            if (i >= 1) {
                retMap.put("result", "注册成功,跳转到登录页面");
            } else {
                retMap.put("result", "注册失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retMap;
    }

    public Map<String, Object> ShiroRegist(Map<String, Object> map) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        try {
            String firstpsd = (String) map.get("password");
            String salt = SaltUtil.getUUId();
            String password = SaltUtil.toHex(firstpsd, salt);
            map.put("salt", salt);
            map.put("password", password);
            int i = userMapper.regist(map);

            if (i >= 1) {
                retMap.put("result", "注册成功,跳转到登录页面。");
            } else {
                retMap.put("result", "注册失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retMap;
    }

    public DataTablesPageUtil<User> selectUserList(DataTablesPageUtil<User> dataTables) {
        List<User> list = userMapper.selectUserList(dataTables.getSearchMap());
		/*for(User user : list){
			List<String> roleIdList = userRoleMapper.findRoleIdsByUserId(user.getUserId());
			user.setRoleIds(String.join(",", roleIdList));
		}*/
        dataTables.setData(list);
        return dataTables;
    }

    public long queryuserlistcnt(Map<String, Object> map) {
        return userMapper.queryuserlistcnt(map);
    }

    public int updateLoginTime(String id) {
        return userMapper.updateLoginTime(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateUserById(User entity) {
        userRoleMapper.deleteByUserId(entity.getId());
        insertUserRole(entity);
        if (!StringUtils.isBlank(entity.getPassword())) {
            entity.setSalt(SaltUtil.generateUUId());
            entity.setPassword(SaltUtil.toHex(entity.getPassword(), entity.getSalt()));
        }
        // 修改密码后要清除shiro缓存中的信息，否则缓存依旧匹配旧密码，新密码无法登录。
		/*Cache<Object,AuthenticationInfo> authentication=shiroRealm.getAuthenticationCache();
		if (authentication!=null){
			authentication.remove(entity.getId());
		}*/
        return userMapper.updateById(entity) > 0;
    }

    private void insertUserRole(User entity) {
        if (!StringUtils.isBlank(entity.getRoleIds())) {
            String[] roles = entity.getRoleIds().split(",");
            UserRole userRole = new UserRole();
            for (String roleId : roles) {
                userRole.setId(SaltUtil.getUUId());
                userRole.setUserId(entity.getId());
                userRole.setRoleId(roleId);
                userRoleMapper.insert(userRole);
            }
        }
    }

    public boolean insert(User user) {
        user.setSalt(SaltUtil.getUUId());
        user.setPassword(SaltUtil.toHex(user.getPassword(), user.getSalt()));
        return userMapper.insert(user) > 0;
    }

    public boolean deleteUserByid(Map<String, Object> map) {
        return userMapper.deleteUserByid(map) > 0;
    }

    public boolean uploadUserAvatar(AttachmentGeneral attachmentGeneral) {
        return userMapper.uploadUserAvatar(attachmentGeneral) > 0;
    }

    public boolean uploadOssAvatar(MultipartFile attachments, User user) {
        String url = ossUtil.uploadOssFile(attachments);
        AttachmentGeneral attachmentGeneral = AttachmentGeneral.builder().id(user.getId()).attachPath(url).build();
        return userMapper.uploadUserAvatar(attachmentGeneral) > 0;
    }

    public void getUserAvatar(HttpServletResponse response, HttpServletRequest request, User user) {
        user = userMapper.selectById(user.getId());
        String rootPath = request.getServletContext().getRealPath("/");
        File file = new File(rootPath + "/static/AdminLTE-3.0.5/dist/img/Tohsaka Rin.jpg");
        if (user.getAvatarPath() != null) {
            file = new File(user.getAvatarPath());
            if (!file.exists()) {
                file = new File(rootPath + "/static/AdminLTE-3.0.5/dist/img/Tohsaka Rin.jpg");
            }
        }
        response.setContentType("image/jpeg"); // 设置返回内容格式
        if (file.exists()) { // 如果文件存在
            InputStream in;
            try {
                in = new FileInputStream(file);
                OutputStream os = response.getOutputStream(); // 创建输出流
                byte[] b = new byte[1024];
                while (in.read(b) != -1) {
                    os.write(b);
                }
                in.close();
                os.flush();
                os.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getOssAvatar(User user) {
        User currentUser = userMapper.selectById(user.getId());
        return currentUser.getAvatarPath();
    }

    public boolean updateOwnPasswordById(User user) {
        return userMapper.updateOwnPasswordById(user) > 0;
    }

}
