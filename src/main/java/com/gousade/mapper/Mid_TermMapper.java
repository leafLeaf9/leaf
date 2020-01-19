package com.gousade.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gousade.pojo.Declaration;
import com.gousade.pojo.Project;

public interface Mid_TermMapper {
	// 设置材料上传规则
	public void setRule(@Param("declaration") Declaration declaration);
	// 查询上传材料的规则
	public String toFindRule(@Param("now") String now,@Param("level") int level,@Param("type") int type);
	// 上传中期检查材料
	public void toMidUpload(@Param("url") String url,@Param("projId") String projId);
	// 上传结题验收材料
	public void toEndUpload(@Param("url") String url,@Param("projId") String projId);
	// 查询中期检查材料已上传的项目列表
	public List<Project> toFindMidList();
	// 查询结题验收材料已上传的项目列表
	public List<Project> toFindEndList();
	// 查询中期检查材料
	public String toFindMidFile(String projId);
	// 查询结题验收材料
	public String toFindEndFile(String projId);
	// 评审结果提交
	public void toReview(@Param("projId") String projId,@Param("state") int state);
	public void toDeclare_upload(@Param("url") String url,@Param("projId") String projId);
	// 根据projId查询负责人id---发送站内消息
	public String findUser(String projId);


}
