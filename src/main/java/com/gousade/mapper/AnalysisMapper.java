package com.gousade.mapper;

import com.gousade.excel.WordsVO;

import java.util.List;
import java.util.Map;

public interface AnalysisMapper {

	/**
	 * 提取情感词(from基础词表words)
	 *
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> extractfromwords(Map<String, Object> singlemap);

	/**
	 * 提取情感词(from大连理工词表dutirwords)
	 *
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> extractfromdutirwords(Map<String, Object> singlemap);

	/**
	 * 根据情感词和词性查询所属公式类型和情感值
	 *
	 * @param map
	 * @return
	 */
	Map<String, Object> selectform(Map<String, Object> singlemap);

	/**
	 * 查询词表
	 *
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> querywordslist(Map<String, Object> map);

	List<Map<String, Object>> selectwordslist(Map<String, Object> map);

	List<WordsVO> expertwords(Map<String, Object> map);

	/**
	 * 查询词表数量
	 *
	 * @param map
	 * @return
	 */
	long querywordslistcnt(Map<String, Object> map);

	/**
	 * 新增情感词
	 *
	 * @param map
	 * @return
	 */
	int insertwords(Map<String, Object> map);

	/**
	 * 修改词表信息
	 *
	 * @param map
	 * @return
	 */
	int updatewords(Map<String, Object> map);

	/**
	 * 删除词表信息
	 *
	 * @param map
	 * @return
	 */
	int delwords(Map<String, Object> map);

	Map<String, Object> getssrlink(Map<String, Object> map);

	/**
	 * 查询评论
	 *
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> querycomments(Map<String, Object> map);

	/**
	 * 查询动态情感词表
	 *
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectdynamicwords(Map<String, Object> map);

}
