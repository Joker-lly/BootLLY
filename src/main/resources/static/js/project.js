$(function(){
	var allInfo=$("#allSearch").val();
	$.ajax({
		type:"post",
		url:"/Project/getProjects.do",
		data:{
			"str" :allInfo
	    	},
		success:function(data){
			var categoryLevel3="";
			if(data.status==1){
				for(var i=0;i<data.data.length;i++){
					 categoryLevel3+=" <div class='liui-show-sp-box bg_white' > ";
					categoryLevel3+=" <a href='projectInfo.html?projectId="+data.data[i].project_id+"&projectName="+data.data[i].project_name+"'>";
					categoryLevel3+=" <div class='liui-flex-sp b-line'>";
					categoryLevel3+=" <div class='liui-flex-sp-box  b-line:after'>";
					categoryLevel3+=" <p>"+data.data[i].project_unit+"</p>";
					
					categoryLevel3+=" </div>";
					categoryLevel3+=" <div class='liui-flex-sp-state'>";
					categoryLevel3+=" <p class='doing'>"+data.data[i].running_status+"</p>";
					categoryLevel3+=" </div>";
					categoryLevel3+=" </div>";
					categoryLevel3+=" <div class='liui-flex'>";
					categoryLevel3+=" <div class='liui-flex-box'>";
					categoryLevel3+="  <h5>"+data.data[i].project_name+"</h5>";
					categoryLevel3+=" <p class='sub_word'>负责人：谷风波<br>周 &emsp;期："+data.data[i].project_start+"——"+data.data[i].project_end+"</p>";
					categoryLevel3+=" </div>";
					categoryLevel3+=" </div>";
					categoryLevel3+=" </a> </div>";
					console.log(categoryLevel3);
				}
				$("#allPro").html(categoryLevel3);	
			}else{
				// 
				//
				//alert("账号或密码错误!");
				//layer.alert('Hello World');
				$.DialogByZ.Autofade({Content: "未查到相关数据!"})
			}
			
		},error:function(data){
			$("#alertBtn").click(function(){
		           $.DialogByZ.Alert({Title: "提示", Content: "系统异常!",BtnL:"确定",FunL:alerts})
			   });
		}	
	});
});

function searchTitle(){
	var allInfo=$("#allSearch").val();
	$.ajax({
		type:"post",
		url:"/Project/getProjects.do",
		data:{
			"str" :allInfo
	    	},
		success:function(data){
			var categoryLevel3='';
			if(data.status==1){
				for(var i=0;i<data.data.length;i++){
					 categoryLevel3+="  <div class='liui-show-sp-box bg_white' > ";
					categoryLevel3+=" <a href='projectInfo.html?projectId="+data.data[i].project_id+"'>";
					categoryLevel3+=" <div class='liui-flex-sp b-line'>";
					categoryLevel3+=" <div class='liui-flex-sp-box'>";
					categoryLevel3+=" <p>"+data.data[i].project_unit+"</p>";
					categoryLevel3+=" </div>";
						categoryLevel3+=" <div class='liui-flex-sp-state'>";
						categoryLevel3+=" <p class='doing'>"+data.data[i].running_status+"</p>";
						categoryLevel3+=" </div>";
					categoryLevel3+=" </div>";
					categoryLevel3+=" <div class='liui-flex'>";
					categoryLevel3+=" <div class='liui-flex-box'>";
					
					categoryLevel3+="  <h5>"+data.data[i].project_name+"</h5>";
					categoryLevel3+=" <p class='sub_word'>负责人："+data.data[i].project_manager+"<br>周 &emsp;期："+data.data[i].project_start+"——"+data.data[i].project_end+"</p></div>";
					categoryLevel3+=" </div>";
					categoryLevel3+=" </a>";
					categoryLevel3+=" </div'>";
				}
				$("#allPro").html(categoryLevel3);	
			}else{
				$.DialogByZ.Autofade({Content: "未查到相关数据!"})
			}
			
		},error:function(data){
			$("#alertBtn").click(function(){
		           $.DialogByZ.Alert({Title: "提示", Content: "系统异常!",BtnL:"确定",FunL:alerts})
			   });
		}	
	});
};
function alerts(){
	 
	  $.DialogByZ.Close();
}