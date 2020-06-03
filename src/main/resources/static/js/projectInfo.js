$(function(){
	
	var projectId;
	var projectName;
	var url = location.search; //获取url中"?"符后的字串
	
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		projectId = decodeURIComponent(strs[0].replace("projectId=",""));
		projectName = decodeURIComponent(strs[1].replace("projectName=",""));
	}
	$("#proName").text(projectName);
		$.ajax({
			type:"post",
			url:"/Project/getProjects.do",//路径会有所不同，请根据实际情况调整
			data:{
				"project_id" :projectId
		    	},
			success:function(data){
				var categoryLevel3='';
				if(data.status==1){
					for(var i=0;i<data.data.length;i++){
						 categoryLevel3+="  <div class='liui-flex-page'><div class='liui-flex-title'>项目名称：</div><div class='liui-flex-box '><span>"+data.data[i].project_name+"</span></div> </div> ";
						 categoryLevel3+="  <div class='liui-flex-page'><div class='liui-flex-title'>委托单位：</div><div class='liui-flex-box '><span>"+data.data[i].project_unit+"</span></div></div>";
						 categoryLevel3+="  <div class='liui-flex-page'><div class='liui-flex-title'>联系人：</div><div class='liui-flex-box '><span>"+data.data[i].link_Name+"</span></div></div>";
						 categoryLevel3+="  <div class='liui-flex-page'><div class='liui-flex-title'>联系人电话：</div><div class='liui-flex-box '><span>"+data.data[i].clientContext_Phone+"</span></div></div>";
						 categoryLevel3+="  <div class='liui-flex-page'><div class='liui-flex-title'>项目主题：</div><div class='liui-flex-box '><span>"+data.data[i].project_title+"</span></div></div>";
						 categoryLevel3+="  <div class='liui-flex-page'><div class='liui-flex-title'>培训天数：</div><div class='liui-flex-box '><span>"+data.data[i].project_days+"</span></div></div>";
						 categoryLevel3+="  <div class='liui-flex-page'><div class='liui-flex-title'>学员人数：</div><div class='liui-flex-box '><span>"+data.data[i].project_planNum+"</span></div></div>";
						 categoryLevel3+="  <div class='liui-flex-page'><div class='liui-flex-title'>学员层次：</div><div class='liui-flex-box '><span>"+data.data[i].project_studLvl+"</span></div></div>";
						 categoryLevel3+="  <div class='liui-flex-page'><div class='liui-flex-title'>项目性质：</div><div class='liui-flex-box '><span>"+data.data[i].project_nature+"</span></div></div>";
						 categoryLevel3+="  <div class='liui-flex-page'><div class='liui-flex-title'>排课周期：</div><div class='liui-flex-box '><span>"+data.data[i].project_start+"————"+data.data[i].project_end+"</span></div></div>";
						 categoryLevel3+="  <div class='liui-flex-page'><div class='liui-flex-title'>报到日期：</div><div class='liui-flex-box '><span>"+data.data[i].startReg_Time+"</span></div></div>";
						 categoryLevel3+="  <div class='liui-flex-page'><div class='liui-flex-title'>项目对接：</div><div class='liui-flex-box '><span>"+data.data[i].proNego_name+"</span></div></div>";
						 categoryLevel3+="  <div class='liui-flex-page'><div class='liui-flex-title'>课程负责：</div><div class='liui-flex-box '><span>张丽丽</span></div></div>";
					}
					$("#project").html(categoryLevel3);	
				}else{
					$.DialogByZ.Autofade({Content: "未查到相关数据!"})
				}
				
			},error:function(data){
				$("#alertBtn").click(function(){
			           $.DialogByZ.Alert({Title: "提示", Content: "系统异常!",BtnL:"确定",FunL:alerts})
				   });
			}	
		});
});
function studnetShow(){
	var projectId;
	var projectName;
	var url = location.search; //获取url中"?"符后的字串
	
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		projectId = decodeURIComponent(strs[0].replace("projectId=",""));
		projectName = decodeURIComponent(strs[1].replace("projectName=",""));
	}
	$.ajax({
		type:"post",
		url:"/elgbxy/Mentee/getMenteeList.do",
		data:{
			"project_id" :projectId
	    	},
	    	success:function(data){
				var categoryLevel="";
				if(data.status==1){
					var arr = [20];
					for(var i=0;i<data.data.length;i++){
						categoryLevel+="  <div class='liui-flex b-line' >";
						categoryLevel+="   <div class='liui-name-box liui-flex-bb'><p>"+data.data[i].mentee_name.substring(0,1)+"</p></div>";
						categoryLevel+="	<div class='liui-flex-box'>";
						categoryLevel+="   <h5 class='liui-padded-b-5'>"+data.data[i].mentee_name+"<small class='sex'>"+data.data[i].mentee_sex+"</small></h5>";
						categoryLevel+="   <p class='sub_word'><em>电话：</em>"+data.data[i].mentee_tel+"";
						categoryLevel+="   <em class='inline_block liui-margin-l-15'>小组：</em>"+data.data[i].mentee_group+"<br>";
						categoryLevel+="    <em>职务：</em>"+data.data[i].mentee_duty+"</p> ";
						categoryLevel+="  </div>";
						categoryLevel+="  </div>";
					}
					$("#studentInfo").html(categoryLevel);	
				}else{
					$("#studentInfo").removeClass();
					$.DialogByZ.Autofade({Content: "未查到相关数据!"})
				}
				
			},error:function(data){
				$("#alertBtn").click(function(){
			           $.DialogByZ.Alert({Title: "提示", Content: "系统异常!",BtnL:"确定",FunL:alerts})
				   });
			}	
	});
};
		function courseShow(){
			var projectId;
			var projectName;
			var url = location.search; //获取url中"?"符后的字串
			
			if (url.indexOf("?") != -1) {
				var str = url.substr(1);
				strs = str.split("&");
				projectId = decodeURIComponent(strs[0].replace("projectId=",""));
				projectName = decodeURIComponent(strs[1].replace("projectName=",""));
			}
			$.ajax({
				type:"post",
				url:"/elgbxy/Project/getProjectCourseList.do",
				data:{
					"project_id" :projectId
			    	},
			    	success:function(data){
						var categoryLevel="";
						if(data.status==1){
							for(var i=0;i<data.data.length;i++){
								categoryLevel+="  <div class='liui-flex b-line' >";
								categoryLevel+="   <div class='liui-flex-box '>";
								categoryLevel+=" <h5 class='liui-padded-b-5'>"+data.data[i].course_name+"</h5>";
								categoryLevel+="  <p class='room'>教室："+data.data[i].course_classroom+"</p>"
								categoryLevel+="   <p class='sub_word'><em>教师：</em>"+data.data[i].course_teacher+"<br>";
								categoryLevel+="  <em>时间：</em>"+data.data[i].course_start+"————"+data.data[i].course_end+"</p>";
								categoryLevel+="  </div>";
								categoryLevel+="  </div>";
							}
							$("#courseInfo").html(categoryLevel);	
						}else{
							$("#courseInfo").removeClass();
							$.DialogByZ.Autofade({Content: "未查到相关数据!"})
						}
						
					},error:function(data){
						$("#alertBtn").click(function(){
					           $.DialogByZ.Alert({Title: "提示", Content: "系统异常!",BtnL:"确定",FunL:alerts})
						   });
					}	
			});
		}
function alerts(){
	 
	  $.DialogByZ.Close();
};