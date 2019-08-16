package cn.edu.zstu.manage.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.zstu.manage.mapper.Mid_TermMapper;
import cn.edu.zstu.manage.pojo.Declaration;
import cn.edu.zstu.manage.pojo.Project;

@Service
public class Mid_TermService {
	@Autowired
	private Mid_TermMapper midTermMapper;
	
	public void setRule(Declaration declaration){
		midTermMapper.setRule(declaration);
	}
	
	public String toFindRule(String now,int level,int type){
		return midTermMapper.toFindRule(now, level,type);		
	}
	
	public String toUpload(MultipartFile myfile, HttpServletRequest request, int type){
		// 获取文件全名
		String fileOldName = myfile.getOriginalFilename();
		// 获取文件后缀
		String extName = fileOldName.substring(fileOldName.lastIndexOf("."));
		//判断合法性
		if(!extName.matches("^.(doc|docx|DOC|DOCX)$")){
			return "上传失败";
		}
		try {
			// 使用java对象保存流
			BufferedImage bufimage = ImageIO.read(myfile.getInputStream());
			// 生成公用路径dir
			String dir = "/mid_term/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/";
			// 磁盘路径
			String cpPath = request.getSession().getServletContext().getRealPath("/")+dir;
			File _dir = new File(cpPath);
			if(!_dir.exists()){
				_dir.mkdirs();
			}
			// 重命名文件
			String imgName = System.currentTimeMillis()+RandomUtils.nextInt(100, 999)+extName;
			// 输出文件到目录
			myfile.transferTo(new File(cpPath + imgName));
			// 存放在数据库中的图片地址	把这个blogImage存放到数据库中的blog_img字段
			String url = dir+imgName;
			if(type==1){		// type=1  提交为中期检查材料
				midTermMapper.toMidUpload(url,request.getParameter("projId"));
			}else{				// type=2  提交为结题验收材料
				midTermMapper.toEndUpload(url,request.getParameter("projId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "上传失败";
		}
		return "上传成功";
	}

	public String toDeclare_upload(MultipartFile myfile, HttpServletRequest request) {
		// TODO Auto-generated method stub
		// 获取文件全名
				String fileOldName = myfile.getOriginalFilename();
				// 获取文件后缀
				String extName = fileOldName.substring(fileOldName.lastIndexOf("."));
				//判断合法性
				if(!extName.matches("^.(doc|docx|DOC|DOCX)$")){
					return "上传失败";
				}
				try {
					// 使用java对象保存流
					BufferedImage bufimage = ImageIO.read(myfile.getInputStream());
					// 生成公用路径dir
					String dir = "/start_term/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/";
					// 磁盘路径
					String cpPath = request.getSession().getServletContext().getRealPath("/")+dir;
					File _dir = new File(cpPath);
					if(!_dir.exists()){
						_dir.mkdirs();
					}
					// 重命名文件
					String imgName = System.currentTimeMillis()+RandomUtils.nextInt(100, 999)+extName;
					// 输出文件到目录
					myfile.transferTo(new File(cpPath + imgName));
					// 存放在数据库中的图片地址	把这个blogImage存放到数据库中的blog_img字段
					String url = dir+imgName;
					midTermMapper.toDeclare_upload(url,request.getParameter("projId"));
				} catch (Exception e) {
					e.printStackTrace();
					return "上传失败";
				}
				return "上传成功";
	}
	
	public List<Project> toFindProjList(int type){
		if(type==1){		// type=1  提交为中期检查项目列表
			return midTermMapper.toFindMidList();
		}else{				// type=2  提交为结题验收项目列表
			return midTermMapper.toFindEndList();
		}
	}
	
	public String toFindFile(String projId, int type){
		if(type==1){		// type=1  提交为中期检查项目材料
			return midTermMapper.toFindMidFile(projId);
		}else{				// type=2  提交为结题验收项目材料
			return midTermMapper.toFindEndFile(projId);
		}
	}
	
	public void toReview(String projId,int type){
		if(type==1){		// type=1  提交为中期检查---通过后state=2
			midTermMapper.toReview(projId,5);
		}else{				// type=2  提交为结题验收---通过后state=3
			midTermMapper.toReview(projId,6);
		}
	}
	
	public String findUser(String projId){
		return midTermMapper.findUser(projId);
	}
	
}
