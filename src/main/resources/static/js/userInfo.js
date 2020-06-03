$(function(){
	var userId = $.cookie("userId"); 
	$.ajax({
		type:"post",
		url:"/User/getInfo.do",
		data:{
			"user_id" :userId
	    	},
		success:function(data){
			if(data.status==1){
					$("#userId").val(data.data.user_id);
					$("#userName").val(data.data.account);
					$("#petName").val(data.data.real_name);
					if(data.data.user_sex=='男'){
						$('input[name=sexMan]').attr('checked',true)
					}else{
						$('input[name=sexWoman]').attr('checked',true)
					}
					$("#phone").val(data.data.user_tel);
					$("#email").val(data.data.user_email);
					$("#duty").val(data.data.user_duty);
				
			}else{
				$.DialogByZ.Autofade({Content: "账数据查询失败!"})
			}
			
		},error:function(data){
			$("#alertBtn").click(function(){
		           $.DialogByZ.Alert({Title: "提示", Content: "系统异常!",BtnL:"确定",FunL:alerts})
			   });
		}	
	});
});
 function submit(){
	 
	 var regEamil = /\w+[@]{1}\w+[.]\w+/;
	  if (!regEamil.test($("#email").val())){
	   
	    $.DialogByZ.Autofade({Content: "请输入正确的email!"});
	    $("#email").focus();
		  return false;
	  }else{
		  var user_email=$("#email").val();
	  }
	  var regPhone = /^1[34578]\d{9}$/;
	  if (!regPhone.test($("#phone").val())){
	    $.DialogByZ.Autofade({Content: "请输入正确的手机号!"});
	    $("#phone").focus();
	    return false;
	  }else{
		  var user_tel=$("#phone").val(); 
	  }
	     var account=$("#petName").val();
		 var user_sex=$("#sex").val();
		 var user_duty=$("#duty").val();
		 var user_id=$("#userId").val();
	$.ajax({
		type:"post",
		url:"/User/infoChange.do",
		data:{
			"user_id":user_id,
			"user_name" :account,
			"user_sex" :user_sex,
			"user_duty" :user_duty,
			"user_tel" :user_tel,
			"user_email" :user_email
	    	},
	 //   dataType:"json", // 返回值类型
		success:function(data){
			if(data.status==1){
				$.cookie("petName", account, { expires: 7 });
				$.DialogByZ.Autofade({Content: data.message});
				window.location.href="mine.html";
			}else{
				$.DialogByZ.Autofade({Content: data.message})
			}
			
		},error:function(data){
			$.DialogByZ.Autofade({Content: "系统异常!"})
		}	
	});
} 
