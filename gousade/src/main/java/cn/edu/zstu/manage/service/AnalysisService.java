package cn.edu.zstu.manage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.ResolverUtil.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gousade.textanalysis.excelvo.WordsVO;

import cn.edu.zstu.manage.mapper.AnalysisMapper;

import cn.edu.zstu.manage.pojo.Menu;
import cn.edu.zstu.manage.pojo.User;
import edu.hit.ir.ltp4j.NER;
import edu.hit.ir.ltp4j.Postagger;
import edu.hit.ir.ltp4j.Segmentor;

@Service
public class AnalysisService {
	@Autowired
	private AnalysisMapper analysisMapper;
	
	public List<Map<String, Object>> querywordslist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return analysisMapper.querywordslist(map);
	}
	
	public List<WordsVO> exopertwords(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return analysisMapper.exopertwords(map);
	}
	
	public long querywordslistcnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return analysisMapper.querywordslistcnt(map);
	}
	
	public Map<String, Object> insertwords(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			
			int i=analysisMapper.insertwords(map);
			
				if(i>=1) {
					//retMap.put("success", true);
					retMap.put("result", "新增词语成功");
				}else {
				//	retMap.put("success", false);
					retMap.put("result", "新增词语失败");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retMap;
	}
	
	public Map<String, Object> updatewords(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			
			int i=analysisMapper.updatewords(map);
			
				if(i>=1) {
					//retMap.put("success", true);
					retMap.put("result", "修改词表信息成功");
				}else {
				//	retMap.put("success", false);
					retMap.put("result", "修改词表信息失败");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retMap;
	}
	
	public Map<String, Object> delwords(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			int i=analysisMapper.delwords(map);
			
				if(i>=1) {
					//retMap.put("success", true);
					retMap.put("result", "删除用户信息成功");
				}else {
				//	retMap.put("success", false);
					retMap.put("result", "删除用户信息失败");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retMap;
	}
	
	public List<Map<String, Object>> querycomments(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return analysisMapper.querycomments(map);
	}
	
	public Map<String, Object> segment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		  Segmentor sentenceSplitApp= new Segmentor();
		  Postagger postaggerApp=new Postagger();
//		  NER   nerApp=new NER();
//		  String nerpath=this.getClass().getClassLoader().getResource("./ner.model").getPath();
//		  System.out.println(nerpath);
//		  if(nerApp.create(nerpath.substring(1))<0){//去掉/E:/textanalysis/target/classes/ner.model的第一个/
//		      System.err.println("ner load failed");		
//		    }
		  
		  String cwspath=this.getClass().getClassLoader().getResource("./cws.model").getPath();
		  System.out.println("cwspath:---"+cwspath);	
		    if(sentenceSplitApp.create(cwspath.substring(1))<0){
		      System.err.println("分词模型 load failed");		
		    }
		    String pospath=this.getClass().getClassLoader().getResource("./pos.model").getPath();
		    if(postaggerApp.create(pospath.substring(1))<0) {
			      System.err.println("词性标注模型 load failed");			     
			    }
		    
//		    if(sentenceSplitApp.create("C:\\model\\cws.model")<0){
//			      System.err.println("分词模型 load failed");		
//			    }
//			   
//			    if(postaggerApp.create("C:\\model\\pos.model")<0) {
//				      System.err.println("词性标注模型 load failed");			     
//				    }
		    
		    String sent = (String) map.get("comment");
		    List<String> words = new ArrayList<String>();
		    int asize = sentenceSplitApp.segment(sent,words);
		    for(int i = 0; i<asize; i++) {
			      System.out.print(words.get(i));
			      if(i==asize-1) {
			        System.out.println();
			      } else{
			        System.out.print("\t");
			      }
			    }
		    String result="";
		    List<Map<String, Object>> needmaplist= new ArrayList<Map<String, Object>>();
		    List<String> postags= new ArrayList<String>();
		    int size = postaggerApp.postag(words,postags);
		    for(int i = 0; i < size; i++) {
		      Map<String, Object> singlemap=new HashMap<String, Object>();
		      //new map 必须放到for循环里，否则list中的数据会被覆盖，因为list中存的是map的引用而不是具体值，map变化list里的值就会变化
		      System.out.print(words.get(i)+"_"+postags.get(i));
		      result+=words.get(i)+"_"+postags.get(i);
		      singlemap.put("wordname", words.get(i));
		      singlemap.put("wordtype", postags.get(i));
		      needmaplist.add(singlemap);
		      if(i==size-1) {
		        System.out.println();
		        result+="\n";
		      } else {
		        System.out.print("|");
		        result+="|";
		      }
		    }
		    sentenceSplitApp.release();
		    postaggerApp.release();   	
		    retMap.put("result", result);
		    retMap.put("list", needmaplist);
		return retMap;
	}

	public Map<String, Object> extract(List<Map<String, Object>> map) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		String result="";
		String tempstr="";
		double resultvalue=0.0;
		String originresult="";
		List<Map<String, Object>> maplist= new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> tempmaplist= new ArrayList<Map<String, Object>>();
		List<List<Map<String, Object>>> resultmaplist= new ArrayList<List<Map<String, Object>>>();
		List<List<Map<String, Object>>> tempresultmaplist= new ArrayList<List<Map<String, Object>>>(); 
		String wordname=null;
		String wordtype=null;		
		List<String> stringlist=new ArrayList<String>();
		List<String> originlist=new ArrayList<String>();
		boolean wpflag=false;
		if(!map.isEmpty()) {			
			if(!map.get(map.size()-1).get("wordtype").equals("wp")) {
				Map<String,Object> wpmap=new HashMap<String,Object>();
				wpmap.put("wordtype", "wp");
				wpmap.put("wordname", "。");
				map.add(wpmap);				
			}//由于遇到标点才可以添加分句队列，所以如果只是一个没有标点的短句，就补足标点 防止无法提取情感词。
			for(Map<String,Object> singlemap:map) {
				originresult+=singlemap.get("wordname");
				if(singlemap.get("wordtype").equals("wp")) {
					originlist.add(originresult);
					originresult="";
				}
			}
			for(Map<String,Object> singlemap:map) {
				
				wordname=(String) singlemap.get("wordname");
				wordtype=(String) singlemap.get("wordtype");
				if(wordname.equals("不")||wordname.equals("没")||wordname.equals("是否")||wordname.equals("不是")||wordname.equals("小")||wordname.equals("很不")
				||wordname.equals("没有")||wordname.equals("莫")||wordname.equals("不大")||wordname.equals("勿")||wordname.equals("别")||wordname.equals("未")
				||wordname.equals("不必")||wordname.equals("不要")||wordname.equals("不用")||wordname.equals("未")||wordname.equals("不要")||wordname.equals("却")
				||wordname.equals("居然")||wordname.equals("偏")||wordname.equals("差点")||wordname.equals("乱")||wordname.equals("无奈")||wordname.equals("不能")) {
					System.err.println(singlemap.get("wordname")+"：是关键否定词，改变其类型。");
					singlemap.put("wordtype", "ne");
					wordtype=(String) singlemap.get("wordtype");
				}//由于哈工大LTP分词系统中将否定词也划分为副词，所以要手动处理否定词，将其类型更改为ne
				if(wordname.equals("大")||wordname.equals("小")||wordname.equals("多")||wordname.equals("少")||wordname.equals("慢")||wordname.equals("快")
					||wordname.equals("老")||wordname.equals("大大")||wordname.equals("巨大")||wordname.equals("多多")||wordname.equals("久")||wordname.equals("薄")
					||wordname.equals("厚")||wordname.equals("高")||wordname.equals("低")||wordname.equals("长")||wordname.equals("短")||wordname.equals("响")
					||wordname.equals("硬")||wordname.equals("粗")||wordname.equals("细")||wordname.equals("简单")||wordname.equals("强")) {
							System.err.println(singlemap.get("wordname")+"：是关键动态情感词，改变其类型。");
							singlemap.put("wordtype", "*");
							wordtype=(String) singlemap.get("wordtype");
						}//由于哈工大LTP分词系统无法识别动态情感词，所以要手动处理，将其类型更改为*
				if(wordtype.equals("m")||wordtype.equals("b")) {
					singlemap.put("wordtype", "d");
					wordtype=(String) singlemap.get("wordtype");
				}//由于哈工大LTP分词系统中将"十分","非常"这些词归为数字和其他修饰词，所以要手动处理，将其类型更改为d
				if(wordtype.equals("wp")) {
					if(StringUtils.isBlank(result)) {
						result="非情感句";
					}
						stringlist.add(result);
					
					
					result="";
					//if(!maplist.isEmpty()) {//用标点分割后，有些分句不含情感词，List为空，就没有必要添加到情感小分句队列中去。
						resultmaplist.add(maplist);
					//}
					
					maplist= new ArrayList<Map<String, Object>>();//由于list.add(e)里存的是元素的引用，元素变化时list中的数据也会跟着变化，所以此处要new maplist
				}//由于评论文本可能是很长的句子，所以要根据标点进行分割，形成多个小分句分开进行计算再求和，避免PW,NA,DA出现次数过多无法套用公式的情况。
				List<Map<String, Object>> isextracted=analysisMapper.extractfromwords(singlemap);
				if(!isextracted.isEmpty()) {
					if(wordtype.equals("*")) {//如果该词是动态情感词，提取正则并计算值。
//						Iterator<String> it = originlist.iterator();
//		                while(it.hasNext()){
//		                    String x = it.next();
//		                    if(x.contains(wordname)){
//		                        System.err.println(x+"---包含-"+wordname+"-动态情感词");
//		                    }
//		                }
					}
					result+=singlemap.get("wordname");
					result+=" ";
					maplist.add(singlemap);
					System.err.println(singlemap.get("wordname")+"：是情感词，成功提取。");	
				}else {//如果再基础词表中没有提取到，就再去大连理工词表进行二次提取。(基础词表适用于本项目数据，大连理工词表适用于所有文本)
//					List<Map<String, Object>> isextracted1=analysisMapper.extractfromdutirwords(singlemap);	
//					if(!isextracted1.isEmpty()) {
//						
//						result+=singlemap.get("wordname");
//						result+=" ";
//						maplist.add(singlemap);
//						System.err.println(singlemap.get("wordname")+"：是情感词，成功提取。");	
//					}else {
						System.out.println(singlemap.get("wordname")+"：不是情感词，提取失败。");	
//					}
					
			    }
			}
			for(Map<String,Object> singlemap:map) {//前面的foreach是获得了情感词的分句队列，这个foreach是获得初始评论的分句队列，用于定位动态情感词所在的分句并匹配动态情感词的正则表达式
				 //如果不是单独一个小分句去匹配正则，可能会发生错误，比如：这手机问题很大，音量很小。按照cal方法的迭代器遍历处理的话，"大"会和问题音量都匹配上 ，"小"也和问题音量都匹配上
				//按照上面的originlist迭代器遍历的话，这个list只有String，没有储存type和value，也行不通，只有用这种方法才可以最准确的计算。
				if(singlemap.get("wordtype").equals("wp")) {					
					tempresultmaplist.add(tempmaplist);					
					tempmaplist= new ArrayList<Map<String, Object>>();//由于list.add(e)里存的是元素的引用，元素变化时list中的数据也会跟着变化，所以此处要new maplist
				}else {
				tempmaplist.add(singlemap);
			    }
			}	
			if(!tempresultmaplist.isEmpty()) {
				for(List<Map<String, Object>> singlelist:tempresultmaplist) {
					if(!singlelist.isEmpty()) {
						 Iterator<Map<String, Object>> it = singlelist.iterator();
			                while(it.hasNext()){
			                    Map<String, Object> x = it.next();
			                    if(x.get("wordtype").equals("*")){
			                    	boolean flag=false;//用来判断动态情感词是否匹配到正则，没匹配到的话采取默认情感值。
			                    	for(Map<String,Object> singlemap:singlelist) {
			                    		tempstr+=singlemap.get("wordname");           				
			            			}                    	 
					                 System.err.println(tempstr+":包含-"+(String)x.get("wordname")+"-动态情感词");
					                 List<Map<String, Object>> regexlist=analysisMapper.selectdynamicwords(x);   //根据动态情感词查询它需要匹配的正则表达式集合，依次匹配以选择情感值
					                 for(Map<String,Object> regexmap:regexlist) {
					                	 if(tempstr.matches((String) regexmap.get("regex"))) {
					                		 resultvalue+=Double.parseDouble((String) regexmap.get("value"))/tempresultmaplist.size();
					                		 flag=true;
					                	 }
					                 }
					                 if(flag==false) {
					                	 List<Map<String, Object>> defaultvalue=analysisMapper.extractfromwords(x); 
					                	 if(!defaultvalue.isEmpty()) {
					                		 resultvalue+=Double.parseDouble((String) defaultvalue.get(0).get("value"))/tempresultmaplist.size(); 
					                	 }
					                 }
			                    	 tempstr="";
			                        it.remove();
			                    }
					}
				}
			}	
		}
			
		}
		resultvalue=(double)Math.round(resultvalue*10000)/10000;
		retMap.put("resultvalue", resultvalue);
		retMap.put("result", stringlist);
		retMap.put("originresult", originlist);
		retMap.put("list", resultmaplist);
		return retMap;
	}

	public Map<String, Object> selectform( List<List<Map<String, Object>>> map) {
		// TODO Auto-generated method stub
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, Object> tempMap = new HashMap<String, Object>();
		List<String> stringlist=new ArrayList<String>();
		String resultstr="";
		if(!map.isEmpty()) {
			for(List<Map<String, Object>> singlelist:map) {
				if(!singlelist.isEmpty()) {
					Iterator<Map<String, Object>> it = singlelist.iterator();
	                while(it.hasNext()){
	                    Map<String, Object> x = it.next();
	                    if(x.get("wordtype").equals("*")){
	                        it.remove();
	                    }
	                }
				}   
				if(!singlelist.isEmpty()) {    
				for(Map<String, Object> singlemap:singlelist) {
					tempMap=analysisMapper.selectform(singlemap);
											
						singlemap.put("formtype", tempMap.get("formtype"));
						singlemap.put("value", tempMap.get("value"));
						resultstr+=singlemap.get("formtype")+"+";
					
										
				}
			
				resultstr=resultstr.substring(0,resultstr.length() - 1);
				stringlist.add(resultstr);
				resultstr="";
			}else {
				resultstr="无情感公式";
				stringlist.add(resultstr);
				resultstr="";
			}
			}
		}
		retMap.put("result", stringlist);
		retMap.put("list", map);
		return retMap;
	}

	public String valuetostar(Double value) {
		String star="";
		if(value>0.0&&value<=0.2) star="★";
		if(value>0.2&&value<=0.4) star="★★";
		if(value>0.4&&value<=0.6) star="★★★";
		if(value>0.6&&value<=0.8) star="★★★★";
		if(value>0.8&&value<=1.0) star="★★★★★";
		if(value>1.0) star="★★★★★★";
		if(value>-0.2&&value<=-0.0) star="☆";
		if(value>-0.4&&value<=-0.2) star="☆☆";
		if(value>-0.6&&value<=-0.4) star="☆☆☆";
		if(value>-0.8&&value<=-0.6) star="☆☆☆☆";
		if(value>-1.0&&value<=-0.8) star="☆☆☆☆☆";
		if(value<=-1.0) star="☆☆☆☆☆☆";
		return star;
	}
	
	public Map<String, Object> calculate(Map<String, Object> qarammap) {
		// TODO Auto-generated method stub
		List<List<Map<String, Object>>> map=(List<List<Map<String, Object>>>) qarammap.get("formlist");
		String comment=(String) qarammap.get("comment");
		System.out.println(comment);
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, Object> tempMap = new HashMap<String, Object>();
		List<String> stringlist=new ArrayList<String>();
		String resultstr="";
		String tempstr="";
		double singlevalue=0,resultvalue = 0,PW,PW1,DA1,DA2,NA1,NA2=0.0;
		
		if(!map.isEmpty()) {
			for(List<Map<String, Object>> singlelist:map) {
				if(!singlelist.isEmpty()) {

                Iterator<Map<String, Object>> it = singlelist.iterator();
                while(it.hasNext()){
                    Map<String, Object> x = it.next();
                    if(x.get("wordtype").equals("*")){
//                    	for(Map<String,Object> singlemap:singlelist) {
//                    		tempstr+=singlemap.get("wordname");           				
//            			}                    	 
//		                 System.err.println(tempstr+":包含-"+(String)x.get("wordname")+"-动态情感词");
//		                 List<Map<String, Object>> regexlist=analysisMapper.selectdynamicwords(x);   //根据动态情感词查询它需要匹配的正则表达式集合，依次匹配以选择情感值
//		                 for(Map<String,Object> regexmap:regexlist) {
//		                	 if(tempstr.matches((String) regexmap.get("regex"))) {
//		                		 resultvalue+=Double.parseDouble((String) regexmap.get("value"));
//		                	 }
//		                 }
//                    	 tempstr="";
                        it.remove();
                    }
                }
				String switchstr="";
				
				for(Map<String, Object> singlemap:singlelist) {
					
					tempMap=analysisMapper.selectform(singlemap);
					singlemap.put("formtype", tempMap.get("formtype"));
					singlemap.put("value", tempMap.get("value"));
					switchstr+=singlemap.get("formtype")+"+";
					
				}
				switchstr=switchstr.substring(0,switchstr.length() - 1);
				if(switchstr.equals("NA+DA+DA")||switchstr.equals("DA+DA+NA")||switchstr.equals("DA+NA+DA")
				 ||switchstr.equals("NA+NA+DA")||switchstr.equals("NA+DA+NA")||switchstr.equals("DA+NA+NA")
				 ||switchstr.equals("NA+NA+NA")||switchstr.equals("DA+DA+DA")||switchstr.equals("PW+PW")||switchstr.equals("PW+PW+PW")
				 ||switchstr.equals("NA+DA")||switchstr.equals("DA+NA")||switchstr.equals("DA+DA")||switchstr.equals("NA+NA")) {
					singlevalue=1.0;
					for (Map<String, Object> j : singlelist) {
						singlevalue*=Double.parseDouble((String)j.get("value"));
						}
					singlevalue=(double)Math.round(singlevalue*10000)/10000;
					resultvalue+=singlevalue;
					resultstr+=String.valueOf(singlevalue); 
					resultstr+=valuetostar(singlevalue); 
				}else {
				switch(switchstr) {
				case "PW" :						
					singlevalue=Double.parseDouble((String) singlelist.get(0).get("value"));
					singlevalue=(double)Math.round(singlevalue*10000)/10000;
					resultvalue+=singlevalue;
					resultstr+=String.valueOf(singlevalue); 
					resultstr+=valuetostar(singlevalue); 
					
		            break;	
				case "NA" :						
					singlevalue=Double.parseDouble((String) singlelist.get(0).get("value"));
					singlevalue=(double)Math.round(singlevalue*10000)/10000;
					resultvalue+=singlevalue;
					resultstr+=String.valueOf(singlevalue); 
					resultstr+=valuetostar(singlevalue); 
					
		            break;	
				case "DA" :						
					singlevalue=Double.parseDouble((String) singlelist.get(0).get("value"));
					singlevalue=(double)Math.round(singlevalue*10000)/10000;
					resultvalue+=singlevalue;
					resultstr+=String.valueOf(singlevalue); 
					resultstr+=valuetostar(singlevalue); 
					
		            break;	    
				case "NA+PW" :						
					NA1=Double.parseDouble((String) singlelist.get(0).get("value"));	
					PW=Double.parseDouble((String) singlelist.get(1).get("value"));	
					singlevalue=NA1*PW;
					singlevalue=(double)Math.round(singlevalue*10000)/10000;
					resultstr+=String.valueOf(singlevalue); 
					resultvalue+=singlevalue;
					resultstr+=valuetostar(singlevalue); 
					
		            break; 
				case "PW+NA" :						
					NA1=Double.parseDouble((String) singlelist.get(1).get("value"));	
					PW=Double.parseDouble((String) singlelist.get(0).get("value"));	
					singlevalue=NA1*PW;
					singlevalue=(double)Math.round(singlevalue*10000)/10000;
					resultstr+=String.valueOf(singlevalue); 
					resultvalue+=singlevalue;
					resultstr+=valuetostar(singlevalue); 
					
		            break; 
				case "NA+NA+PW" :						
					NA1=Double.parseDouble((String) singlelist.get(0).get("value"));
					NA2=Double.parseDouble((String) singlelist.get(1).get("value"));
					PW=Double.parseDouble((String) singlelist.get(2).get("value"));	
					singlevalue=NA1*NA2*PW;
					singlevalue=(double)Math.round(singlevalue*10000)/10000;
					resultstr+=String.valueOf(singlevalue); 
					resultvalue+=singlevalue;
					resultstr+=valuetostar(singlevalue); 
		            break;
				case "NA+PW+PW" :						
					NA1=Double.parseDouble((String) singlelist.get(0).get("value"));
					PW=Double.parseDouble((String) singlelist.get(1).get("value"));
					PW1=Double.parseDouble((String) singlelist.get(2).get("value"));	
					singlevalue=NA1*(PW+PW1);
					singlevalue=(double)Math.round(singlevalue*10000)/10000;
					resultstr+=String.valueOf(singlevalue); 
					resultvalue+=singlevalue;
					resultstr+=valuetostar(singlevalue); 
		            break;
				case "DA+PW" :					
					 PW=Double.parseDouble((String) singlelist.get(1).get("value"));
					 DA1=Double.parseDouble((String) singlelist.get(0).get("value"));					 
					if(PW>0) {
					singlevalue=PW+(1-PW)*DA1;
					singlevalue=(double)Math.round(singlevalue*10000)/10000;
					resultstr+=String.valueOf(singlevalue);
					resultvalue+=singlevalue;
					resultstr+=valuetostar(singlevalue); 
					}
					else {
						singlevalue=PW+(-1-PW)*DA1;
						singlevalue=(double)Math.round(singlevalue*10000)/10000;
						resultstr+=String.valueOf(singlevalue); 
						resultvalue+=singlevalue;
						resultstr+=valuetostar(singlevalue); 
						}
		            break;
				case "DA+PW+PW" :						
					DA1=Double.parseDouble((String) singlelist.get(0).get("value"));
					PW=Double.parseDouble((String) singlelist.get(1).get("value"));
					PW1=Double.parseDouble((String) singlelist.get(2).get("value"));	
					singlevalue=DA1*(PW+PW1);
					singlevalue=(double)Math.round(singlevalue*10000)/10000;
					resultstr+=String.valueOf(singlevalue); 
					resultvalue+=singlevalue;
					resultstr+=valuetostar(singlevalue); 
		            break;    
				case "PW+DA" :					
					 PW=Double.parseDouble((String) singlelist.get(0).get("value"));
					 DA1=Double.parseDouble((String) singlelist.get(1).get("value"));					 
					if(PW>0) {
					singlevalue=PW+(1-PW)*DA1;
					singlevalue=(double)Math.round(singlevalue*10000)/10000;
					resultstr+=String.valueOf(singlevalue);
					resultvalue+=singlevalue;
					resultstr+=valuetostar(singlevalue); 
					}
					else {
						singlevalue=PW+(-1-PW)*DA1;
						singlevalue=(double)Math.round(singlevalue*10000)/10000;
						resultstr+=String.valueOf(singlevalue); 
						resultvalue+=singlevalue;
						resultstr+=valuetostar(singlevalue); 
						}
		            break;
				case "DA+DA+PW" :					
					 PW=Double.parseDouble((String) singlelist.get(2).get("value"));
					 DA1=Double.parseDouble((String) singlelist.get(0).get("value"));
					 DA2=Double.parseDouble((String) singlelist.get(1).get("value"));
					if(PW>0) {
					singlevalue=PW+(1-PW)*DA1+(1-PW-(1-PW)*DA1)*DA2;
					singlevalue=(double)Math.round(singlevalue*10000)/10000;
					resultstr+=String.valueOf(singlevalue); 
					resultvalue+=singlevalue;
					resultstr+=valuetostar(singlevalue); 
					}
					else {
						singlevalue=PW+(-1-PW)*DA1+(-1-PW-(-1-PW)*DA1)*DA2;
						singlevalue=(double)Math.round(singlevalue*10000)/10000;
						resultstr+=String.valueOf(singlevalue); 
						resultvalue+=singlevalue;
						resultstr+=valuetostar(singlevalue); 
						}
		            break;
				   
				case "NA+DA+PW" :					
					 PW=Double.parseDouble((String) singlelist.get(2).get("value"));
					 NA1=Double.parseDouble((String) singlelist.get(0).get("value"));
					 DA1=Double.parseDouble((String) singlelist.get(1).get("value"));
					if(PW>0) {
					singlevalue=PW+(-1-PW*NA1)*(DA1*0.5);
					singlevalue=(double)Math.round(singlevalue*10000)/10000;
					resultstr+=String.valueOf(singlevalue); 
					resultvalue+=singlevalue;
					resultstr+=valuetostar(singlevalue); 
					
					}
					else {
						singlevalue=PW+(1-PW*NA1)*(DA1*0.5);
						singlevalue=(double)Math.round(singlevalue*10000)/10000;
						resultstr+=String.valueOf(singlevalue); 
						resultvalue+=singlevalue;
						resultstr+=valuetostar(singlevalue); 
						
						}
		            break;
				case "DA+NA+PW" :					
					 PW=Double.parseDouble((String) singlelist.get(2).get("value"));
					 DA1=Double.parseDouble((String) singlelist.get(0).get("value"));
					 NA1=Double.parseDouble((String) singlelist.get(1).get("value"));
					if(PW>0) {
					singlevalue=PW*NA1+(-1-PW*NA1)*DA1;
					singlevalue=(double)Math.round(singlevalue*10000)/10000;
					resultstr+=String.valueOf(singlevalue); 
					resultvalue+=singlevalue;
					resultstr+=valuetostar(singlevalue); 
					
					}
					else {
						singlevalue=PW*NA1+(1-PW*NA1)*DA1;
						singlevalue=(double)Math.round(singlevalue*10000)/10000;
						resultstr+=String.valueOf(singlevalue); 
						resultvalue+=singlevalue;
						resultstr+=valuetostar(singlevalue); 
						 
						}
		            break;      
		            
				default :
					resultstr+="无匹配算法";
			            System.out.println("未知公式，无法计算！");    
				}
			}
				stringlist.add(resultstr);
				resultstr="";
				  
				}else {
					resultstr="无情感值";
					stringlist.add(resultstr);
					resultstr="";
				}
			}
		}
		if(comment.matches("(.*)再(.*)就好了(.*)")||comment.matches(".*有.*就好了.*")||comment.matches(".*再.*就.*了.*")||comment.matches(".*再.*更.*了.*")
				||comment.matches(".*如果.*就好了.*")||comment.matches(".*要是.*就好了.*")) {
			resultvalue+=0.15*stringlist.size();
		}
		if(comment.matches(".*开始.*担心.*")||comment.matches(".*开始.*以为.*")||comment.matches(".*刚开始.*担心.*")||comment.matches(".*刚开始.*以为.*")
		   ||comment.matches(".*本来.*担心.*")||comment.matches(".*原来.*担心.*")) {
			resultvalue+=0.1*stringlist.size();			
		}
		if(comment.matches(".*有.*问题.*再.*")||comment.matches(".*担心.*问题.*")||comment.matches(".*问题.*理解.*")||comment.matches(".*问题.*回答.*")
				   ||comment.matches(".*问题.*解决.*")||comment.matches(".*问题.*处理.*")||comment.matches(".*问题.*解答.*")) {
					resultvalue+=-0.075*stringlist.size();			
				}
		if(comment.matches(".*问题.*没有.*解决.*")||comment.matches(".*问题.*没有.*处理.*")||comment.matches(".*问题.*没有.*解答.*")||comment.matches(".*问题.*不.*理解.*")
				   ||comment.matches(".*问题.*不.*解决.*")||comment.matches(".*问题.*不.*处理.*")||comment.matches(".*问题.*不.*解答.*")
				   ||comment.matches(".*点意外.*")||comment.matches(".*小意外.*")||comment.matches(".*问题.*不.*推诿.*")) {
					resultvalue+=-0.1*stringlist.size();			
				}
		if(comment.matches(".*挺.*有.*感觉.*")||comment.matches(".*很.*有.*感觉.*")||comment.matches(".*很.*意外.*")||comment.matches(".*太.*意外.*")
				   ||comment.matches(".*挺.*意外.*")) {
					resultvalue+=0.2*stringlist.size();			
				}
		resultvalue=resultvalue/stringlist.size();
		resultvalue=(double)Math.round(resultvalue*10000)/10000;
		//resultstr=resultstr.substring(0,resultstr.length() - 1);		
		resultstr=String.valueOf(resultvalue)+valuetostar(resultvalue); 
		retMap.put("result", stringlist);
		retMap.put("resultstr", resultstr);
		retMap.put("resultvalue", resultvalue);
		retMap.put("list", map);
		return retMap;
	}

}
