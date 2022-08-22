package cn.anntek.springbootinterceptoruserlogin.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor {
    //注册拦截器
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        Object user=request.getSession().getAttribute("user");
        //判断是否登录
        if (user==null){
            try{
                request.getRequestDispatcher("/login").forward(request,response);//转发
            }catch (ServletException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }
}
