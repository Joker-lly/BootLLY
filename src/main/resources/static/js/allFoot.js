/*$(function(){
	var userId;
	var userName;
	var url = location.search; //获取url中"?"符后的字串
	
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		userId = decodeURIComponent(strs[0].replace("userId=",""));
		userName = decodeURIComponent(strs[1].replace("userName=",""));
	}
	alert(userId);
	alert(userName);
	var footString='';
	footString+=" <a href='notelist.html' class='liui-tabBar-item liui-tabBar-item-active'> <span class='liui-tabBar-item-icon'> <i class='icon icon-notice'></i> </span> <span class='liui-tabBar-item-text'>通知</span> </a>";
	footString+=" <a href='project.html' class='liui-tabBar-item '> <span class='liui-tabBar-item-icon'> <i class='icon icon-project'></i> </span> <span class='liui-tabBar-item-text'>项目</span> </a>";
	footString+=" <a href='course.html' class='liui-tabBar-item'> <span class='liui-tabBar-item-icon'> <i class='icon icon-class'></i> </span> <span class='liui-tabBar-item-text'>课程</span> </a> ";
	footString+=" <a href='classroom.html' class='liui-tabBar-item'> <span class='liui-tabBar-item-icon'> <i class='icon icon-room'></i> </span> <span class='liui-tabBar-item-text'>教室</span> </a>";
	footString+="  <a href='mine.html'  class='liui-tabBar-item'> <span class='liui-tabBar-item-icon'> <i class='icon icon-my'></i> </span> <span class='liui-tabBar-item-text'>我的</span> </a> ";
	$("#showFoot").html(footString);
});*/