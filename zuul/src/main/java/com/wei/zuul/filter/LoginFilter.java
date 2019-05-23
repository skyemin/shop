package com.wei.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: weizz
 * @Date: 2019/5/23 14:29
 * @Description:
 * @Version:1.0
 */
@Component
public class LoginFilter extends ZuulFilter {

    @Override
    public String filterType() {
        /**
         pre：请求在被路由之前执行
         routing：在路由请求时调用
         post：在routing和errror过滤器之后调用
         error：处理请求时发生错误调用
         */
        return "pre";
    }

    @Override
    public int filterOrder() {
        //过虑器序号，越小越被优先执行
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //返回true表示要执行此过虑器
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String username = request.getParameter("username");// 获取请求的参数
        if("zs".equals(username)){//当请求参数username为“bright”时通过
            ctx.setSendZuulResponse(true);// 对该请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);// 设值，让下一个Filter看到上一个Filter的状态
            return null;
        }else{
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(401);// 返回错误码
            ctx.setResponseBody("{\"result\":\"username is not correct!\"}");// 返回错误内容
            ctx.set("isSuccess", false);
            return null;
        }
    }
}
