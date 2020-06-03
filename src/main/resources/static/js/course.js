$(function(){
			var date = new Date();
			var time;
			if($("#time").val()==""){
				
				var month=date.getMonth()+1;
				 month =(month<10 ? "0"+month:month); 
				 time=date.getFullYear()+"-"+month+"-"+date .getDate();
				$("#time").val(time);
			}
			$.ajax({
				type:"post",
				url:"/Project/getCourseListCourse.do",
				data:{
					course_date:$("#time").val(),
					roomType:1						//因为该页面和index页面用的是同一个方法，但是该页面是根据一周统计课程；所以以roomType进行区分
			    	},
				success:function(data){
					//var courseList=[];
					
					if(data.status==1){
						var regionStr1;
						var regionStr2;
						var regionStr3;
						var regionStr4;
						var regionStr5;
						var regionStr6;
						var regionStr7;
						var timeMon1;
						var timeMon2;
						var timeMon3;
						var timeMon4;
						var timeMon5;
						var timeMon6;
						var timeMon7;
						for(var i=0;i<data.data.length;i++){
							timeMon1=data.data[i].timeMon1;
							timeMon2=data.data[i].timeMon2;
							timeMon3=data.data[i].timeMon3;
							timeMon4=data.data[i].timeMon4;
							timeMon5=data.data[i].timeMon5;
							timeMon6=data.data[i].timeMon6;
							timeMon7=data.data[i].timeMon7;
						}
						 if(timeMon1==""){
							 regionStr1 = "[" + timeMon1 + "]";
						}else{
							var  region=timeMon1.split(",");
							var timeMon1s="";
							for(var k=0;k<region.length;k++){
								timeMon1s = timeMon1s + "\"" + region[k] + "\"";
	                             //构建xAxis中categories['x1','x2','x3'...]
	                             if (k < region.length - 1) {
	                            	 timeMon1s = timeMon1s + ",";
	                             }
							}
							 regionStr1 = "[" + timeMon1s + "]";
						}
						if(timeMon2==""){
							regionStr2= "[" + timeMon2 + "]";
						}else{
							var region=timeMon2.split(",");
							var timeMon2s="";
							for(var k=0;k<region.length;k++){
								timeMon2s = timeMon2s + "\"" + region[k] + "\"";
	                             //构建xAxis中categories['x1','x2','x3'...]
	                             if (k < region.length - 1) {
	                            	 timeMon2s = timeMon2s + ",";
	                             }
							}
							 regionStr2 = "[" + timeMon2s + "]";
						}
						if(timeMon3==""){
							regionStr3 = "[" + timeMon3 + "]";
						}else{
							var timeMon3s="";
							var	region=timeMon3.split(",");
							for(var k=0;k<region.length;k++){
								timeMon3s = timeMon3s + "\"" + region[k] + "\"";
	                             //构建xAxis中categories['x1','x2','x3'...]
	                             if (k < region.length - 1) {
	                            	 timeMon3s = timeMon3s + ",";
	                             }
							}
							 regionStr3 = "[" + timeMon3s + "]";
						}
						if(timeMon4==""){
							regionStr4 = "[" + timeMon4 + "]";
						}else{
							var timeMon4s="";
							var	region=timeMon4.split(",");
							for(var k=0;k<region.length;k++){
								timeMon4s = timeMon4s + "\"" + region[k] + "\"";
	                             //构建xAxis中categories['x1','x2','x3'...]
	                             if (k < region.length - 1) {
	                            	 timeMon4s = timeMon4s + ",";
	                             }
							}
							 regionStr4 = "[" + timeMon4s + "]";
						}
						if(timeMon5==""){
							 regionStr5 = "[" + timeMon5 + "]";
						}else{
							var timeMon5s="";
							var	region=timeMon5.split(",");
							for(var k=0;k<region.length;k++){
								timeMon5s = timeMon5s + "\"" + region[k] + "\"";
	                             //构建xAxis中categories['x1','x2','x3'...]
	                             if (k < region.length - 1) {
	                            	 timeMon5s = timeMon5s + ",";
	                             }
							}
							 regionStr5 = "[" + timeMon5s + "]";
						}
						if(timeMon6==""){
							regionStr6 = "[" + timeMon6 + "]";
						}else{
							var timeMon6s="";
							var	region=timeMon6.split(",");
							for(var k=0;k<region.length;k++){
								timeMon6s = timeMon6s + "\"" + region[k] + "\"";
	                             //构建xAxis中categories['x1','x2','x3'...]
	                             if (k < region.length - 1) {
	                            	 timeMon6s = timeMon6s + ",";
	                             }
							}
							 regionStr6 = "[" + timeMon6s + "]";
						}
						if(timeMon7==""){
							 regionStr7= "[" + timeMon7 + "]";
						}else{
							var timeMon7s="";
							var	region=timeMon7.split(",");
							for(var k=0;k<region.length;k++){
								timeMon7s = timeMon7s + "\"" + region[k] + "\"";
	                             //构建xAxis中categories['x1','x2','x3'...]
	                             if (k < region.length - 1) {
	                            	 timeMon7s = timeMon7s + ",";
	                             }
							}
							 regionStr7= "[" + timeMon7s + "]";
						} 
						
						var courseList = [
						                  JSON.parse(regionStr1),
						                  JSON.parse(regionStr2),
						                  JSON.parse(regionStr3),
						                  JSON.parse(regionStr4),
						                  JSON.parse(regionStr5),
						                  JSON.parse(regionStr6), 
						                  JSON.parse(regionStr7),
						                ]; 
						console.log(courseList);
						var Timetable = new Timetables({
						    el: '#coursesTable',
						    timetables: courseList,
						    week: week,
						    timetableType: courseType,
						    highlightWeek: day,
						    gridOnClick: function (e) {
						      alert(e.name + '  '+',周' + e.week + ', 第' + e.index + '节课, 课长' + e.length + '节');
						      console.log(e);
						    },
						    styles: {
						      Gheight: 50
						    }
						  }); 
					}else{
						$("#kInfo").text("暂无其他课程信息");
						var courseList = [
						                  ['', '', '', '', '', '', '', '', '', '', '', ''],
						                  ['', '', '', '', '', '', '', '', '', '', '', ''],
						                  ['', '', '', '', '', '', '', '', '', '', '', ''],
						                  ['', '', '', '', '', '', '', '', '', '', '', ''],
						                  ['', '', '', '', '', '', '', '', '', '', '', ''],
						                ];
						// 实例化(初始化课表)
						  var Timetable = new Timetables({
							    el: '#coursesTable',
							    timetables: courseList,
							    week: week,
							    timetableType: courseType,
							    highlightWeek: day,
							    gridOnClick: function (e) {
							      alert(e.name + '  '+',周' + e.week + ', 第' + e.index + '节课, 课长' + e.length + '节');
							      console.log(e);
							    },
							    styles: {
							      Gheight: 50
							    }
							  }); 
					}
					
				},error:function(data){
					 $("#alertBtn").click(function(){
				           $.DialogByZ.Alert({Title: "提示", Content: "系统异常!",BtnL:"确定",FunL:alerts})
					   }); 
				}	
			})
		});
  
function timeClick(){
	$("#coursesTable").html(" ")	;
			var date = new Date();
			var time;
			if($("#time").val()==""){
				
				var month=date.getMonth()+1;
				 month =(month<10 ? "0"+month:month); 
				 time=date.getFullYear()+"-"+month+"-"+date .getDate();
				$("#time").val(time);
			}
			$.ajax({
				type:"post",
				url:"/Project/getCourseListCourse.do",
				data:{
					course_date:$("#time").val(),
					roomType:1						//因为该页面和index页面用的是同一个方法，但是该页面是根据一周统计课程；所以以roomType进行区分
			    	},
				success:function(data){
					//var courseList=[];
					
					if(data.status==1){
						var regionStr1;
						var regionStr2;
						var regionStr3;
						var regionStr4;
						var regionStr5;
						var regionStr6;
						var regionStr7;
						var regionStr8;
						var timeMon1;
						var timeMon2;
						var timeMon3;
						var timeMon4;
						var timeMon5;
						var timeMon6;
						var timeMon7;
						var timeMon8;
						for(var i=0;i<data.data.length;i++){
							timeMon1=data.data[i].timeMon1;
							timeMon2=data.data[i].timeMon2;
							timeMon3=data.data[i].timeMon3;
							timeMon4=data.data[i].timeMon4;
							timeMon5=data.data[i].timeMon5;
							timeMon6=data.data[i].timeMon6;
							timeMon7=data.data[i].timeMon7;
						}
						 if(timeMon1==""){
							 regionStr1 = "[" + timeMon1 + "]";
						}else{
							var  region=timeMon1.split(",");
							var timeMon1s="";
							for(var k=0;k<region.length;k++){
								timeMon1s = timeMon1s + "\"" + region[k] + "\"";
	                             //构建xAxis中categories['x1','x2','x3'...]
	                             if (k < region.length - 1) {
	                            	 timeMon1s = timeMon1s + ",";
	                             }
							}
							 regionStr1 = "[" + timeMon1s + "]";
						}
						if(timeMon2==""){
							regionStr2= "[" + timeMon2 + "]";
						}else{
							var region=timeMon2.split(",");
							var timeMon2s="";
							for(var k=0;k<region.length;k++){
								timeMon2s = timeMon2s + "\"" + region[k] + "\"";
	                             //构建xAxis中categories['x1','x2','x3'...]
	                             if (k < region.length - 1) {
	                            	 timeMon2s = timeMon2s + ",";
	                             }
							}
							 regionStr2 = "[" + timeMon2s + "]";
						}
						if(timeMon3==""){
							regionStr3 = "[" + timeMon3 + "]";
						}else{
							var timeMon3s="";
							var	region=timeMon3.split(",");
							for(var k=0;k<region.length;k++){
								timeMon3s = timeMon3s + "\"" + region[k] + "\"";
	                             //构建xAxis中categories['x1','x2','x3'...]
	                             if (k < region.length - 1) {
	                            	 timeMon3s = timeMon3s + ",";
	                             }
							}
							 regionStr3 = "[" + timeMon3s + "]";
						}
						if(timeMon4==""){
							regionStr4 = "[" + timeMon4 + "]";
						}else{
							var timeMon4s="";
							var	region=timeMon4.split(",");
							for(var k=0;k<region.length;k++){
								timeMon4s = timeMon4s + "\"" + region[k] + "\"";
	                             //构建xAxis中categories['x1','x2','x3'...]
	                             if (k < region.length - 1) {
	                            	 timeMon4s = timeMon4s + ",";
	                             }
							}
							 regionStr4 = "[" + timeMon4s + "]";
						}
						if(timeMon5==""){
							 regionStr5 = "[" + timeMon5 + "]";
						}else{
							var timeMon5s="";
							var	region=timeMon5.split(",");
							for(var k=0;k<region.length;k++){
								timeMon5s = timeMon5s + "\"" + region[k] + "\"";
	                             //构建xAxis中categories['x1','x2','x3'...]
	                             if (k < region.length - 1) {
	                            	 timeMon5s = timeMon5s + ",";
	                             }
							}
							 regionStr5 = "[" + timeMon5s + "]";
						}
						if(timeMon6==""){
							regionStr6 = "[" + timeMon6 + "]";
						}else{
							var timeMon6s="";
							var	region=timeMon6.split(",");
							for(var k=0;k<region.length;k++){
								timeMon6s = timeMon6s + "\"" + region[k] + "\"";
	                             //构建xAxis中categories['x1','x2','x3'...]
	                             if (k < region.length - 1) {
	                            	 timeMon6s = timeMon6s + ",";
	                             }
							}
							 regionStr6 = "[" + timeMon6s + "]";
						}
						if(timeMon7==""){
							 regionStr7= "[" + timeMon7 + "]";
						}else{
							var timeMon7s="";
							var	region=timeMon7.split(",");
							for(var k=0;k<region.length;k++){
								timeMon7s = timeMon7s + "\"" + region[k] + "\"";
	                             //构建xAxis中categories['x1','x2','x3'...]
	                             if (k < region.length - 1) {
	                            	 timeMon7s = timeMon7s + ",";
	                             }
							}
						} 
					
						var courseList = [
						                  JSON.parse(regionStr1),
						                  JSON.parse(regionStr2),
						                  JSON.parse(regionStr3),
						                  JSON.parse(regionStr4),
						                  JSON.parse(regionStr5),
						                  JSON.parse(regionStr6), 
						                  JSON.parse(regionStr7),
						                ]; 
						var Timetable = new Timetables({
						    el: '#coursesTable',
						    timetables: courseList,
						    week: week,
						    timetableType: courseType,
						    highlightWeek: day,
						    gridOnClick: function (e) {
						      alert(e.name + '  '+ e.week + ', 第' + e.index + '节课, 课长' + e.length + '节');
						      console.log(e);
						    },
						    styles: {
						      Gheight: 50
						    }
						  }); 
					}else{
						$("#kInfo").text("暂无其他课程信息");
						$("#kInfo").text("暂无其他课程信息");
						var courseList = [
						                  ['', '', '', '', '', '', '', '', '', '', '', ''],
						                  ['', '', '', '', '', '', '', '', '', '', '', ''],
						                  ['', '', '', '', '', '', '', '', '', '', '', ''],
						                  ['', '', '', '', '', '', '', '', '', '', '', ''],
						                  ['', '', '', '', '', '', '', '', '', '', '', ''],
						                ];
						// 实例化(初始化课表)
						  var Timetable = new Timetables({
							    el: '#coursesTable',
							    timetables: courseList,
							    week: week,
							    timetableType: courseType,
							    highlightWeek: day,
							    gridOnClick: function (e) {
							      alert(e.name + '  '+',周' + e.week + ', 第' + e.index + '节课, 课长' + e.length + '节');
							      console.log(e);
							    },
							    styles: {
							      Gheight: 50
							    }
							  }); 
					}
					
				},error:function(data){
					 $("#alertBtn").click(function(){
				           $.DialogByZ.Alert({Title: "提示", Content: "系统异常!",BtnL:"确定",FunL:alerts})
					   }); 
				}	
			})
		}

 
 
 
 
 
  
var week = window.innerWidth > 360 ? ['周一', '周二', '周三', '周四', '周五','周六','周日'] :
    ['一', '二', '三', '四', '五','六','日'];
  var day = new Date().getDay();
 /* var courseType = [
    [{name: '上午'}, 4],
    [{name: '下午'}, 4],
    [{name: '晚上'}, 4]
   
  ];*/
  var courseType = [
                    [{ name: '8:30'}, 1],
                    [{ name: '9:30'}, 1],
                    [{ name: '10:30'}, 1],
                    [{ name: '11:30'}, 1],
                    [{ name: '12:30'}, 1],
                    [{ name: '14:30'}, 1],
                    [{ name: '15:30'}, 1],
                    [{ name: '16:30'}, 1],
                    [{ name: '17:30'}, 1],
                    [{ name: '18:30'}, 1],
                    [{ name: '19:30'}, 1],
                    [{ name: '20:30'}, 1]
                  ];
  function alerts(){
		 
  	  $.DialogByZ.Close();
  }
  