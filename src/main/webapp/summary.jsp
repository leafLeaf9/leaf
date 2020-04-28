<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>   
<!DOCTYPE html>
<html>
<head>
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

</head>
<body>
<% String name = (String)session.getAttribute("u"); %>
 <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
  <input type="button" style=" width: 135.96px; height: 24.4px;" value="粗粒度汇总" onclick="thickshow()">  
  <input type="button" style=" width: 135.96px; height: 24.4px;" value="细粒度汇总" onclick="thinshow()">
    <div id="thick" style="height:400px" ></div>
    <div id="thin" style="height:400px" ></div>
    <div id="map" style="height:400px" ></div>  
<script>
var myChart = echarts.init(document.getElementById('thick'));

// 指定图表的配置项和数据
var option = {
    title: {
        text: 'ECharts 入门示例'
    },
    tooltip: {},
    legend: {
        data:['销量']
    },
    xAxis: {
        data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
    },
    yAxis: {},
    series: [{
        name: '销量',
        type: 'bar',
        data: [5, 20, 36, 10, 10, 20]
    }]
};
//使用刚指定的配置项和数据显示图表。
myChart.setOption(option);

var piechart = echarts.init(document.getElementById('thin'));
var pieoption = {
	    tooltip: {
	        trigger: 'item',
	        formatter: '{a} <br/>{b}: {c} ({d}%)'
	    },
	    legend: {
	        orient: 'vertical',
	        left: 10,
	        data: ['直达', '营销广告', '搜索引擎', '邮件营销', '联盟广告', '视频广告', '百度', '谷歌', '必应', '其他']
	    },
	    series: [
	        {
	            name: '访问来源',
	            type: 'pie',
	            selectedMode: 'single',
	            radius: [0, '30%'],

	            label: {
	                position: 'inner'
	            },
	            labelLine: {
	                show: false
	            },
	            data: [
	                {value: 335, name: '直达', selected: true},
	                {value: 679, name: '营销广告'},
	                {value: 1548, name: '搜索引擎'}
	            ]
	        },
	        {
	            name: '访问来源',
	            type: 'pie',
	            radius: ['40%', '55%'],
	            label: {
	                formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
	                backgroundColor: '#eee',
	                borderColor: '#aaa',
	                borderWidth: 1,
	                borderRadius: 4,
	                shadowBlur:3,
	                shadowOffsetX: 2,
	                shadowOffsetY: 2,
	                shadowColor: '#999',
	                padding: [0, 7],
	                rich: {
	                    a: {
	                        color: '#999',
	                        lineHeight: 22,
	                        align: 'center'
	                    },
	                    abg: {
	                        backgroundColor: '#333',
	                        width: '100%',
	                        align: 'right',
	                        height: 22,
	                        borderRadius: [4, 4, 0, 0]
	                    },
	                    hr: {
	                        borderColor: '#aaa',
	                        width: '100%',
	                        borderWidth: 0.5,
	                        height: 0
	                    },
	                    b: {
	                        fontSize: 16,
	                        lineHeight: 33
	                    },
	                    per: {
	                        color: '#eee',
	                        backgroundColor: '#334455',
	                        padding: [2, 4],
	                        borderRadius: 2
	                    }
	                }
	            },
	            data: [
	                {value: 335, name: '直达'},
	                {value: 310, name: '邮件营销'},
	                {value: 234, name: '联盟广告'},
	                {value: 135, name: '视频广告'},
	                {value: 1048, name: '百度'},
	                {value: 251, name: '谷歌'},
	                {value: 147, name: '必应'},
	                {value: 102, name: '其他'}
	            ]
	        }
	    ]
	};
piechart.setOption(pieoption);
var mapChart = echarts.init(document.getElementById('map'));
$.get('./static/echarts/USA.json', function (usaJson) {
	mapChart.hideLoading();

    echarts.registerMap('USA', usaJson, {
        Alaska: {              // 把阿拉斯加移到美国主大陆左下方
            left: -131,
            top: 25,
            width: 15
        },
        Hawaii: {
            left: -110,        // 夏威夷
            top: 28,
            width: 5
        },
        'Puerto Rico': {       // 波多黎各
            left: -76,
            top: 26,
            width: 2
        }
    });
    var mapoption = {
        title: {
            text: 'USA Population Estimates (2012)',
            subtext: 'Data from www.census.gov',
            sublink: 'http://www.census.gov/popest/data/datasets.html',
            left: 'right'
        },
        tooltip: {
            trigger: 'item',
            showDelay: 0,
            transitionDuration: 0.2,
            formatter: function (params) {
                var value = (params.value + '').split('.');
                value = value[0].replace(/(\d{1,3})(?=(?:\d{3})+(?!\d))/g, '$1,');
                return params.seriesName + '<br/>' + params.name + ': ' + value;
            }
        },
        visualMap: {
            left: 'right',
            min: 500000,
            max: 38000000,
            inRange: {
                color: ['#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
            },
            text: ['High', 'Low'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            //orient: 'vertical',
            left: 'left',
            top: 'top',
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        series: [
            {
                name: 'USA PopEstimates',
                type: 'map',
                roam: true,
                map: 'USA',
                emphasis: {
                    label: {
                        show: true
                    }
                },
                // 文本位置修正
                textFixed: {
                    Alaska: [20, -20]
                },
                data:[
                    {name: 'Alabama', value: 4822023},
                    {name: 'Alaska', value: 731449},
                    {name: 'Arizona', value: 6553255},
                    {name: 'Arkansas', value: 2949131},
                    {name: 'California', value: 38041430},
                    {name: 'Colorado', value: 5187582},
                    {name: 'Connecticut', value: 3590347},
                    {name: 'Delaware', value: 917092},
                    {name: 'District of Columbia', value: 632323},
                    {name: 'Florida', value: 19317568},
                    {name: 'Georgia', value: 9919945},
                    {name: 'Hawaii', value: 1392313},
                    {name: 'Idaho', value: 1595728},
                    {name: 'Illinois', value: 12875255},
                    {name: 'Indiana', value: 6537334},
                    {name: 'Iowa', value: 3074186},
                    {name: 'Kansas', value: 2885905},
                    {name: 'Kentucky', value: 4380415},
                    {name: 'Louisiana', value: 4601893},
                    {name: 'Maine', value: 1329192},
                    {name: 'Maryland', value: 5884563},
                    {name: 'Massachusetts', value: 6646144},
                    {name: 'Michigan', value: 9883360},
                    {name: 'Minnesota', value: 5379139},
                    {name: 'Mississippi', value: 2984926},
                    {name: 'Missouri', value: 6021988},
                    {name: 'Montana', value: 1005141},
                    {name: 'Nebraska', value: 1855525},
                    {name: 'Nevada', value: 2758931},
                    {name: 'New Hampshire', value: 1320718},
                    {name: 'New Jersey', value: 8864590},
                    {name: 'New Mexico', value: 2085538},
                    {name: 'New York', value: 19570261},
                    {name: 'North Carolina', value: 9752073},
                    {name: 'North Dakota', value: 699628},
                    {name: 'Ohio', value: 11544225},
                    {name: 'Oklahoma', value: 3814820},
                    {name: 'Oregon', value: 3899353},
                    {name: 'Pennsylvania', value: 12763536},
                    {name: 'Rhode Island', value: 1050292},
                    {name: 'South Carolina', value: 4723723},
                    {name: 'South Dakota', value: 833354},
                    {name: 'Tennessee', value: 6456243},
                    {name: 'Texas', value: 26059203},
                    {name: 'Utah', value: 2855287},
                    {name: 'Vermont', value: 626011},
                    {name: 'Virginia', value: 8185867},
                    {name: 'Washington', value: 6897012},
                    {name: 'West Virginia', value: 1855413},
                    {name: 'Wisconsin', value: 5726398},
                    {name: 'Wyoming', value: 576412},
                    {name: 'Puerto Rico', value: 3667084}
                ]
            }
        ]
    };

    mapChart.setOption(mapoption);
});
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