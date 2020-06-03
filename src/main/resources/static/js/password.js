function savePass(){
	var userId = $.cookie("userId"); 
		var oldPassword=$("#oldPassword").val();
		var newPassword=$("#newPassword").val();
		var newPasswords=$("#newPasswords").val();
		if(oldPassword==""||newPassword==""||newPasswords==""){
			  $.DialogByZ.Autofade({Content: "密码不能为空!"});
			  return false;
		}
		if(newPasswords!=newPassword){
			 $.DialogByZ.Autofade({Content: "两次密码不一致请重新输入!"});
			  return false;
		}
		$.ajax({
			type:"post",
			url:"/elgbxy/User/pwdChange.do",
			data:{
				"user_id":userId,
				"oldPassword" :oldPassword,
				"newPassword" :newPassword
		    	},
		 //   dataType:"json", // 返回值类型
			success:function(data){
				if(data.status==1){
					$.DialogByZ.Autofade({Content: "密码修改成功!"})
					//window.location.href="index.html?user_id="+data.data.user_id+"&user_name="+data.data.user_name;
				}else{
					// 
					//
					//alert("账号或密码错误!");
					//layer.alert('Hello World');
					$.DialogByZ.Autofade({Content: "原密码不正确!"})
				}
				
			},error:function(data){
				$.DialogByZ.Autofade({Content: "系统异常!"})
			}	
		});
	}