
package com.lly.interceptor;

import com.lly.util.user.SessionUtil;
import com.lly.util.user.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserSession userSession = SessionUtil.getUserSession(request);
        if(userSession==null){
            //还需要验证userSession 中信息正确性，采用将信息存到map或者redis中
            //redisTemplate.getKeySerializer();
            // String username = redisTemplate.boundValueOps(userSession.getUserName()).get();
            //输出语句
            //  Webutil.out(response,"请重新登录");
            response.setContentType("text/html");
            request.setAttribute("name","name");
            //setAttribute("name",name);
            response.sendRedirect("http://localhost:8081/apptab/login.html");


            return false;
        }else {
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
