$(function(){
	var rmbUser = $.cookie("rmbUser");
	if(rmbUser=="true"){
		/*var userId;
		var userName;
		var url = location.search; //获取url中"?"符后的字串
		
		if (url.indexOf("?") != -1) {
			var str = url.substr(1);
			strs = str.split("&");
			userId = decodeURIComponent(strs[0].replace("userId=",""));
			userName = decodeURIComponent(strs[1].replace("userName=",""));
		}*/
		var account = $.cookie("account"); 
		var ckpwd = $.cookie("passMiMa"); 
		var data=new Date();
		data.getFullYear(); //获取年 
		var month=data.getMonth()+1;
		 month =(month<10 ? "0"+month:month); 
		 
		var time=data.getFullYear().toString()+"-"+month.toString()+"-"+data.getDate().toString(); 
		var timeString=data.getFullYear()+"年"+month+"月"+data.getDate()+"日"; 
		//项目接口
		$.ajax({
			type:"post",
			url:"/Project/getProjects.do",
			data:{
		    	},
			success:function(data){
				var categoryLevel3='';
				if(data.status==1){
					for(var i=0;i<data.data.length;i++){
						 categoryLevel3+="  <div class='liui-flex b-line' > <a href='projectInfo.html?projectId="+data.data[i].project_id+"&projectName="+data.data[i].project_name+"' > ";
						 categoryLevel3+=" <div class='liui-flex-box '>";
						 categoryLevel3+="<h5>"+data.data[i].project_name+"</h5>";
						 categoryLevel3+=" <p class='sub_word'><em>"+data.data[i].project_start+"</em></p>";
						 categoryLevel3+="  </div> </a></div>";
					}
					$("#projectInit").html(categoryLevel3);	
				}else{
					$.DialogByZ.Autofade({Content: "未查到相关数据!"})
				}
				
			},error:function(data){
				$("#alertBtn").click(function(){
			           $.DialogByZ.Alert({Title: "提示", Content: "系统异常!",BtnL:"确定",FunL:alerts})
				   });
			}	
		});
		//消息接口
		$.ajax({
			type:"post",
			url:"/MsgPush/getMessageList.do",
	     // url:"/MsgPush/getMessageList.do?user_id="+useid,
			data:{
		    	},
			success:function(data){
				if(data.data==null){
					$("#sysMessage h3").text("您还没有新的消息");
				}else{
					var categoryLevel3='';
					if(data.status==1){
						   var listPro=data.data;
						   for (var i = 0; i < listPro.length; i++) {
							   
							   if(listPro[i].msg_type==0){
								var url="textInfo.html?title="+listPro[i].msg_id
								 categoryLevel3+='<div class="liui-flex liui-padded-t-0">  <a href="'+url+'"liui-flex-bb b-line">'
										+'<div class="liui-flex-box ">'
										+'<h5>'+listPro[i].msg_name+'</h5>'
										+'<p class="sub_word"><em>'+listPro[i].msg_send_time+'</em></p></div> </a></div>';
							   }
							   if(listPro[i].msg_type==1){
								   var url;
								   if(listPro[i].msg_name.indexOf("A*")!=-1){
									 var list=listPro[i].msg_name.split("A*")
									 url="backlog.html?projectId="+list[1];	
								   }else{
									   url="textInfo.html?title="+listPro[i].msg_id
								   }
									 categoryLevel3+='<div class="liui-flex liui-padded-t-0">  <a href="'+url+'"liui-flex-bb b-line">'
										+'<div class="liui-flex-box ">'
										+'<h5>'+listPro[i].msg_name+'</h5>'
										+'<p class="sub_word"><em>'+listPro[i].msg_send_time+'</em></p></div> </a></div>';
							   }
							   if(listPro[i].msg_type==2){
								   var list=listPro[i].msg_url.split("?");
								   var msg_id=listPro[i].msg_id;
								   var phoneNumber=$.cookie("phone");
								   var url="/Approve/approve_build.do?"+list[1]+"&phoneNumber="+phoneNumber+"&msg_id="+msg_id;
									 categoryLevel3+='<div class="liui-flex liui-padded-t-0">  <a href="'+url+'"liui-flex-bb b-line">'
										+'<div class="liui-flex-box ">'
										+'<h5>'+listPro[i].msg_name+'</h5>'
										+'<p class="sub_word"><em>'+listPro[i].msg_send_time+'</em></p></div> </a></div>';
							   }
							  
							   if(listPro[i].msg_type==3){
									 categoryLevel3+='<div class="liui-flex liui-padded-t-0">  <a href="'+url+'"liui-flex-bb b-line">'
										+'<div class="liui-flex-box ">'
										+'<h5>'+listPro[i].msg_name+'</h5>'
										+'<p class="sub_word"><em>'+listPro[i].msg_send_time+'</em></p></div> </a></div>';
							   }
							   if(listPro[i].msg_type==4){
									 categoryLevel3+='<div class="liui-flex liui-padded-t-0">  <a href="'+url+'"liui-flex-bb b-line">'
										+'<div class="liui-flex-box ">'
										+'<h5>'+listPro[i].msg_name+'</h5>'
										+'<p class="sub_word"><em>'+listPro[i].msg_send_time+'</em></p></div> </a></div>';
							   }
							   if(listPro[i].msg_type==5){
									 categoryLevel3+='<div class="liui-flex liui-padded-t-0">  <a href="'+url+'"liui-flex-bb b-line">'
										+'<div class="liui-flex-box ">'
										+'<h5>'+listPro[i].msg_name+'</h5>'
										+'<p class="sub_word"><em>'+listPro[i].msg_send_time+'</em></p></div> </a></div>';
							   }
							   
							   if(listPro[i].msg_type==6){
									 categoryLevel3+='<div class="liui-flex liui-padded-t-0">  <a href="'+url+'"liui-flex-bb b-line">'
										+'<div class="liui-flex-box ">'
										+'<h5>'+listPro[i].msg_name+'</h5>'
										+'<p class="sub_word"><em>'+listPro[i].msg_send_time+'</em></p></div> </a></div>';
							   }
						
							   
							}
						$("#messageInit").html(categoryLevel3);	
					}else{
						
						$.DialogByZ.Autofade({Content: "未查到相关数据!"})
					}
				}
				
				
			},error:function(data){
				$("#alertBtn").click(function(){
			           $.DialogByZ.Alert({Title: "提示", Content: "系统异常!",BtnL:"确定",FunL:alerts})
				   });
			}	
		});
		//课程接口
		$.ajax({
			type:"post",
			url:"/Project/getCourseLists.do",
			data:{
				"course_date":time,
				"roomType":2
		    	},
			success:function(data){
				if(data.data==null){
					$("#courseNo h3").text("今日暂无课程安排");
					//$.DialogByZ.Autofade({Content: "未查到相关数据!"})
				}else{
					var categoryLevel3='';
					if(data.status==1){
						for(var i=0;i<data.data.length;i++){
							 categoryLevel3+="   <div class='liui-flex liui-padded-t-0'> <a href='javascript:;' class='liui-flex-bb b-line'> ";
							 categoryLevel3+=" <div class='liui-flex-box '>";
							 categoryLevel3+="<h5>"+data.data[i].courseTheme+"</h5>";
							 categoryLevel3+=" <p class='sub_word'><em>"+timeString+"</em></p>";
							 categoryLevel3+="  </div> </a></div>";
						}
						$("#courseInit").html(categoryLevel3);	
					}
				}
				
				
			},error:function(data){
				$("#alertBtn").click(function(){
			           $.DialogByZ.Alert({Title: "提示", Content: "系统异常!",BtnL:"确定",FunL:alerts})
				   });
			}	
		});
	}else{
		window.location.href="login.html";
	}
	
});
function alerts(){
	 
	  $.DialogByZ.Close();
}