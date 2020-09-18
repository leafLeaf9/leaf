package com.gousade.mapper;

import java.util.List;
import java.util.Map;

import com.gousade.excelvo.WordsVO;

public interface AnalysisMapper {

	/**
	 * 提取情感词(from基础词表words)
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> extractfromwords(Map<String, Object> singlemap);

	/**
	 * 提取情感词(from大连理工词表dutirwords)
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> extractfromdutirwords(Map<String, Object> singlemap);

	/**
	 * 根据情感词和词性查询所属公式类型和情感值
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, Object> selectform(Map<String, Object> singlemap);

	/**
	 * 查询词表
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> querywordslist(Map<String, Object> map);

	public List<Map<String, Object>> selectwordslist(Map<String, Object> map);

	public List<WordsVO> expertwords(Map<String, Object> map);

	/**
	 * 查询词表数量
	 * 
	 * @param map
	 * @return
	 */
	public long querywordslistcnt(Map<String, Object> map);

	/**
	 * 新增情感词
	 * 
	 * @param map
	 * @return
	 */
	public int insertwords(Map<String, Object> map);

	/**
	 * 修改词表信息
	 * 
	 * @param map
	 * @return
	 */
	public int updatewords(Map<String, Object> map);

	/**
	 * 删除词表信息
	 * 
	 * @param map
	 * @return
	 */
	public int delwords(Map<String, Object> map);

	public Map<String, Object> getssrlink(Map<String, Object> map);

	/**
	 * 查询评论
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> querycomments(Map<String, Object> map);

	/**
	 * 查询动态情感词表
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> selectdynamicwords(Map<String, Object> map);

}
