package com.gousade.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gousade.excel.WordsVO;
import com.gousade.pojo.Page;
import com.gousade.service.AnalysisService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//添加restcontroller注解之后，return"main"不能再返回main.jsp，需要改写成ModelAndView mv = new ModelAndView("main"); return mv;

@RestController

public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;

    /**
     * 查询词表
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/querywordslist", method = RequestMethod.POST)
    public Map<String, Object> querywordslist(@RequestParam(value = "page", required = false) String page,
                                              @RequestParam(value = "rows", required = false) String rows, HttpServletRequest request) {
        Page pageBean = new Page(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> retMap = new HashMap<String, Object>();
        Map<String, Object> paraMap = new HashMap<String, Object>();
        String wordname = request.getParameter("wordname");
        paraMap.put("firstPage", pageBean.getFirstPage());
        paraMap.put("rows", pageBean.getRows());
        paraMap.put("wordname", wordname);
        List<Map<String, Object>> list = analysisService.querywordslist(paraMap);
        long total = analysisService.querywordslistcnt(paraMap);
        retMap.put("rows", list);
        retMap.put("total", total);
        return retMap;
    }

    /**
     * PageHelper分页
     */
//	@RequiresPermissions("selectwordslist")
    @RequestMapping(value = "/selectwordslist", method = RequestMethod.POST)
    public Map<String, Object> selectwordslist(@RequestParam(value = "page", required = false) String page,
                                               @RequestParam(value = "rows", required = false) String rows, HttpServletRequest request) {
        Page pageBean = new Page(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> retMap = new HashMap<String, Object>();
        Map<String, Object> paraMap = new HashMap<String, Object>();
        String wordname = request.getParameter("wordname");
        paraMap.put("wordname", wordname);
        PageHelper.startPage(pageBean.getPage(), pageBean.getRows());// 引入PageHelper后会自动计算(page - 1) *
        // rows，Page类后续可以考虑删掉
        List<Map<String, Object>> list = analysisService.pagewordslist(paraMap);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
        long total = pageInfo.getTotal();
        retMap.put("rows", list);
        retMap.put("total", total);
        return retMap;
    }

    /**
     * 新增词语
     *
     * @param map
     * @return
     */
    @RequiresPermissions("insertwords")
    @RequestMapping(value = "/insertwords", method = RequestMethod.POST)
    public Map<String, Object> insertwords(@RequestBody Map<String, Object> map) {

        return analysisService.insertwords(map);
    }

    /**
     * 修改词表信息
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/updatewords", method = RequestMethod.POST)
    public Map<String, Object> updatewords(@RequestBody Map<String, Object> map) {

        return analysisService.updatewords(map);
    }

    /**
     * 删除词表信息
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/delwords", method = RequestMethod.POST)
    public Map<String, Object> delwords(@RequestBody Map<String, Object> map) {

        return analysisService.delwords(map);
    }

    /**
     * 获得SSR链接
     */
    @RequestMapping(value = "/getssrlink", method = RequestMethod.POST)
    public Map<String, Object> getssrlink(@RequestBody Map<String, Object> map) {

        return analysisService.getssrlink(map);
    }

    /**
     * 查询评论
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/querycomments", method = RequestMethod.POST)
    public List<Map<String, Object>> querycomments(@RequestBody Map<String, Object> map) {

        return analysisService.querycomments(map);
    }

    /**
     * 中文分词
     *
     * @param map
     * @return
     */

	/*@RequestMapping(value="/segment",method=RequestMethod.POST)
	public Map<String,Object> segment(@RequestBody Map<String,Object> map){
		System.out.println("-------java.library.path:"+System.getProperty("java.library.path"));
	
		return analysisService.segment(map);
	}
	
	*//**
     * 提取情感词
     *
     * @param map
     * @return
     */
	/*
	
	@RequestMapping(value="/extract",method=RequestMethod.POST)
	public Map<String,Object> extract(@RequestBody List<Map<String,Object>> map){
	
	return analysisService.extract(map);
	}
	
	*//**
     * 根据情感词查询所需计算公式
     *
     * @param map
     * @return
     */
	/*
	
	@RequestMapping(value="/selectform",method=RequestMethod.POST)
	public Map<String,Object> selectform(@RequestBody  List<List<Map<String, Object>>> map){
	
	return analysisService.selectform(map);
	}
	
	*//**
     * 根据公式计算情感强度
     *
     * @param map
     * @return
     *//*
			
			@RequestMapping(value="/calculate",method=RequestMethod.POST)
			public Map<String,Object> calculate(@RequestBody  Map<String, Object> qarammap){
			
			return analysisService.calculate(qarammap);
			}*/

    /**
     * 导出词表到excel
     *
     * @param map
     * @return
     */
    @RequiresPermissions("expertword")
    @RequestMapping(value = "/expertwords", method = RequestMethod.POST)
    public Map<String, Object> expertwords(HttpServletResponse response, Map<String, Object> qarammap)
            throws IOException {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("firstPage", 0);
        paraMap.put("rows", 500);
        List<WordsVO> list = analysisService.expertwords(paraMap);
        // excel总体设置
        ExportParams exportParams = new ExportParams();
        // 不需要标题
        // exportParams.setCreateHeadRows(false);
        // 指定sheet名字
        exportParams.setSheetName("词表信息");
        // 生成workbook 并导出
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, WordsVO.class, list);
//		    File directory = new File("");//设定为当前文件夹 
//		    System.out.println(directory.getCanonicalPath());//获取标准的路径 
//		    System.out.println(directory.getAbsolutePath());//获取绝对路径 
		/*File savefile = new File("./src/main/webapp/xls/");
		if (!savefile.exists()) {
		    boolean result = savefile.mkdirs();
		    System.out.println("目录不存在，创建" + result);
		}
		FileOutputStream fos = new FileOutputStream("./src/main/webapp/xls/词表导出.xlsx");
		workbook.write(fos);
		fos.close();*/
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("result", "操作成功");
        return retMap;
    }

}
