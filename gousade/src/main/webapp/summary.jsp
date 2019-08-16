<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>    
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>Echarts属性汇总</title>
<style>
a{
 cursor:pointer;
 color:blue;
}
#label{
display:none;
}
table
  {
  border-collapse:collapse;

  }
  table, td, th
  {
  border:1px solid black;
  }
  input{
  cursor:pointer;
  }
  input:hover
{
background-color:skyblue;
}
</style>
  <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
 <script src="http://cdn.bootcss.com/jquery/1.12.2/jquery.js"></script>
 <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
  <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>  
    <script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        // 使用
        require(
            [
                'echarts',
                'echarts/theme/macarons',//加载皮肤主题
                'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec,theme) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('thick'),theme); 
                
                var option = {
                	    title : {
                	        text: '粗粒度汇总',
                	        
                	        x:'center'
                	    },
                	    tooltip : {
                	        trigger: 'item',
                	        formatter: "{a} <br/>{b} : {c} ({d}%)"
                	    },
                	    legend: {
                	        orient : 'vertical',
                	        x : 'left',
                	        data:['正向评论','负向评论']
                	    },
                	    
                	    calculable : true,
                	    series : [
                	        {
                	            name:'情感倾向',
                	            type:'pie',
                	            radius : '55%',
                	            center: ['50%', '60%'],
                	            data:[
                	                {value:1736, name:'正向评论'},
                	                {value:664, name:'负向评论'}
                	                
                	            ]
                	        }
                	    ]
                	};
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            } 
                    
        );
        
     // 使用
        require(
            [
                'echarts',
                'echarts/theme/macarons',
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec,theme) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('thin'),theme); 
                
                var option = {
                    title : {
                		        text: '细粒度汇总',                		        
                		    },
                    tooltip: {
                        show: true,
                        trigger: 'axis',
                    },
                    legend: {
                        data:['正向评论数','负向评论数']
                    },
//                     toolbox: {
//                         show : true,
//                         feature : {
//                             mark : {show: true},
//                             dataView : {show: true, readOnly: false},
//                             magicType : {show: true, type: ['line', 'bar']},
//                             restore : {show: true},
//                             saveAsImage : {show: true}
//                         }
//                     },
                    calculable : true,
                    xAxis : [
                        {
                            type : 'category',
                            data : ["性价比","配置","性能","操作","系统","外观"]
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            "name":"正向评论数",
                            "type":"bar",
                            "data":[1031, 564, 861, 1599, 127, 1394],
//                             barGap:'20%',
                            markPoint : {
                                data : [
                                    {type : 'max', name: '最大值'},
                                    {type : 'min', name: '最小值'}
                                ]
                            },
                            markLine : {
                                data : [
                                    {type : 'average', name: '平均值'}
                                ]
                            }
                        },
                        {
                            name:'负向评论数',
                            type:'bar',
                            data:[508, 344, 1120, 264, 1482, 103],
                            
                            markPoint : {
                                data : [
                                    {name : '负向最高', value : 1482, xAxis:4, yAxis: 1482, symbolSize:18},
                                    {name : '负向最低', value : 103, xAxis: 5, yAxis:103}
                                ]
                            },
                            markLine : {
                                data : [
                                    {type : 'average', name : '平均值'}
                                ]
                            }
                        } 
                    ]
                };
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
                    
        );
        
    </script>
</head>
<body>
<% String name = (String)session.getAttribute("u"); %>
 <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
  <input type="button" style=" width: 135.96px; height: 24.4px;" value="粗粒度汇总" onclick="thickshow()">  
  <input type="button" style=" width: 135.96px; height: 24.4px;" value="细粒度汇总" onclick="thinshow()">
    <div id="thick" style="height:400px" ></div>
     <div id="thin" style="height:400px" ></div>
      
 <script>

function  allhide() {
	$("#thick").hide();$("#thin").hide();
}

function  thickshow() {
	$("#thick").show();$("#thin").hide();
}
function  thinshow() {
	$("#thick").hide();$("#thin").show();
}
 </script>
</body>
</html>