
	$(function(){
		var account = $.cookie("account"); 
		var ckpwd = $.cookie("passMiMa"); 
		var rmbUser = $.cookie("rmbUser");
		if(rmbUser=="true"){
			$("#account").val(account); //用户
			$("#passMiMa").val(ckpwd); //用户密码 
			$("#checkMiMa").prop("checked",true);
		}else{
			$("#account").val(account); //用户
			/*$("#passMiMa").val(ckpwd); //用户密码 */	
		    $("#checkMiMa").prop("checked",false);
		}
		/*if($.cookie("login")=="true"){
				$("#account").val($.cookie("account"));
				$("#passMiMa").val($.cookie("passMiMa"));
				$("#checkMiMa").prop("checked",true);
			}*/
		
		
		
	});
	function mouseOver(){
		$("#passMiMa").attr("type","text");
	}
	function mouseOut(){
		$("#passMiMa").attr("type","password");
	}
	function login(){
		var expiresDate= new Date();
		expiresDate.setTime(expiresDate.getTime() + (60*30*1000));//存储一个带30分钟期限的cookie
		var account=$("#account").val();
		var password=$("#passMiMa").val();
		if(account==""||password==""){
			 $.DialogByZ.Alert({Title: "提示", Content: "密码或账号为空!",BtnL:"确定",FunL:alerts}) ;
			 return false;
		}
		
		
		$.ajax({
			type:"post",
			url:"/User/Login.do",
			data:{
				"account" :account,
				"password" :password,
		    	},
			success:function(data){
				

				if(data.status==1){
					if ($("#checkMiMa").is(":checked")) {
						$.cookie("rmbUser", "true", { expires: 30 }); 
						 $.cookie("userId", data.data.user_id, { expires:30 });
						 $.cookie("phone", data.data.user_tel, { expires: 30 });
						 $.cookie("petName", data.data.user_name, { expires: 30 });
					}else{
						$.cookie("rmbUser", "false", { });
						 $.cookie("userId", data.data.user_id, {});
						 $.cookie("petName", data.data.user_name, { });
						 $.cookie("phone", data.data.user_tel, { });
					}
					//window.location.href="index.html?user_id="+data.data.user_id+"&user_name="+data.data.user_name;
					window.location.href="index.html";
				}else{
					$.DialogByZ.Autofade({Content: "账号或密码错误!"})
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
      }