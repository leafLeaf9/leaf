package com.gousade.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.gousade.pojo.Declaration;
import com.gousade.pojo.Project;
import com.gousade.service.MessageService;
import com.gousade.service.Mid_TermService;

@Controller
public class Mid_TermController {
	@Autowired
	private Mid_TermService midTermService;
	@Autowired
	private MessageService messageService;
	
	/**********************中期检查部分*****************************/
	
	// 中期检查规则设置
	@RequestMapping("/setMidRule")
	public String setMidRule(Declaration declaration,Model model){
		declaration.setType(1); 		// type=1  表示是结题验收
		try {
			midTermService.setRule(declaration);
			model.addAttribute("message", "提交成功!");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "提交失败！");
		}
		return "forward:/mid_term";
	}
	
	// 中期检查材料上传---页面的规则说明：准备
	@RequestMapping("/toMid_upload")
	public String toMidUpload(Model model){
		// 获得当前时间
		Date getDate = Calendar.getInstance().getTime();
		String now = new SimpleDateFormat("yyyy-MM-dd").format(getDate);
		String rule1 = midTermService.toFindRule(now, 1, 1);	// 中期检查type=1
		String rule2 = midTermService.toFindRule(now, 2, 1);
		model.addAttribute("rule1", rule1);
		model.addAttribute("rule2", rule2);
		return "forward:/mid_upload";
	}
	//上传申报书电子版
	@RequestMapping("/toDeclare_upload")
	public String declare_upload(MultipartFile myfile, HttpServletRequest request,Model model){
		String message = null;
		try {
			message = midTermService.toDeclare_upload(myfile,request);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			model.addAttribute("message", message);
		}
		
		return "forward:/declare_upload";
	}
	
	// 上传中期检查材料
	@RequestMapping("/midUpload")
	public String midUpload(MultipartFile myfile, HttpServletRequest request,Model model){
		String message = null;
		try {
			message = midTermService.toUpload(myfile,request,1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			model.addAttribute("message", message);
		}
		return "forward:/mid_upload";
	}
	
	// 查询已经上传中期检查材料的项目列表---准备评审
	@RequestMapping("/toMidList")
	public String toMidReview(Model model){
		List<Project> projList = midTermService.toFindProjList(1);
		model.addAttribute("projList", projList);
		// 可以获取到数据库中的projList
		return "/mid_proj";
	}
	
	// 查询某个项目的评审内容
	@RequestMapping("/toMid_Review")
	public String midReview(@RequestParam("projId") String projId,Model model){
		// 中期检查材料的地址
		String url = midTermService.toFindFile(projId,1);
		model.addAttribute("projId", projId);
		model.addAttribute("url", url);
		return "forward:/mid_review";
	}
	
	// 提交评审结果
	@RequestMapping("/midReview")
	public String midReview(@RequestParam("projId") String projId,
				@RequestParam("result") int result,
				@RequestParam("explain") String explain,Model model){
		if(result==1){		// 审批通过
			try {
				midTermService.toReview(projId,1);
				model.addAttribute("message", "提交成功!");
				String userId = midTermService.findUser(projId);
				messageService.toMessage(userId, "中期检查通过，请注意结题验收进度安排！");
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "提交失败！请稍后重试！");
			}
		}else{				// 驳回整改
			try{
				/*
				 * 	发送站内消息给项目负责人
				 * 	消息内容就是参数列表中的   explain   审批人的评语
				 */
				System.out.println(explain);
				model.addAttribute("message", "提交成功!");
				String userId = midTermService.findUser(projId);
				messageService.toMessage(userId, "中级检查未通过！理由："+explain);
			}catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "提交失败！请稍后重试！");
			}
		}
		return "forward:/mid_review";
	}
	
	/**********************结题验收部分*****************************/
	
	// 结题验收规则设置
	@RequestMapping("/setEndRule")
	public String setEndRule(Declaration declaration,Model model){
		declaration.setType(2); 		// type=2  表示是结题验收
		try {
			midTermService.setRule(declaration);
			model.addAttribute("message", "提交成功!");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "提交失败！");
		}
		return "forward:/end_term";
	}
	
	// 结题验收材料上传---页面的规则说明：准备
	@RequestMapping("/toEnd_upload")
	public String toEndUpload(Model model){
		// 获得当前时间
		Date getDate = Calendar.getInstance().getTime();
		String now = new SimpleDateFormat("yyyy-MM-dd").format(getDate);
		String rule1 = midTermService.toFindRule(now, 1, 2);		// 结题验收type=2
		String rule2 = midTermService.toFindRule(now, 2, 2);
		model.addAttribute("rule1", rule1);
		model.addAttribute("rule2", rule2);
		return "forward:/end_upload";
	}
	
	// 结题验收材料上传
	@RequestMapping("/endUpload")
	public String endUpload(MultipartFile myfile, HttpServletRequest request,Model model){
		String message = null;
		try {
			message = midTermService.toUpload(myfile,request, 2);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			model.addAttribute("message", message);
		}
		return "forward:/end_upload";
	}
	
	// 查询已经上传结题验收材料的项目列表---准备评审
	@RequestMapping("/toEndList")
	public String toEndReview(Model model){
		List<Project> projList = midTermService.toFindProjList(2);
		model.addAttribute("projList", projList);
		// 可以获取到数据库中的projList
		return "/end_proj";
	}
	
	// 查询某个项目的评审内容
	@RequestMapping("/toEnd_Review")
	public String endReview(@RequestParam("projId") String projId,Model model){
		// 结题验收材料的地址
		String url = midTermService.toFindFile(projId,2);
		model.addAttribute("projId", projId);
		model.addAttribute("url", url);
		return "forward:/end_review";
	}
	
	// 提交评审结果
	@RequestMapping("/endReview")
	public String endReview(@RequestParam("projId") String projId,
				@RequestParam("result") int result,
				@RequestParam("explain") String explain,Model model){
		if(result==1){		// 审批通过
			try {
				midTermService.toReview(projId,2);
				model.addAttribute("message", "提交成功!");
				String userId = midTermService.findUser(projId);
				messageService.toMessage(userId, "结题验收通过，请注意结题验收进度安排！");
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "提交失败！请稍后重试！");
			}
		}else{				// 驳回整改
			try{
				/*
				 * 	发送站内消息给项目负责人
				 * 	消息内容就是参数列表中的   explain   审批人的评语
				 */
				System.out.println(explain);
				model.addAttribute("message", "提交成功!");
				String userId = midTermService.findUser(projId);
				messageService.toMessage(userId, "explain");
			}catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "提交失败！请稍后重试！");
			}
		}
		return "forward:/end_review";
	}
}
