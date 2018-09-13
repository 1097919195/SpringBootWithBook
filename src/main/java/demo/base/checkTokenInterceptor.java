/** */
package demo.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import demo.util.HttpTool;
import demo.util.ResponseData;

import demo.util.redis.JedisUtil;

/**
 * @功能:
 * @项目名:myspringboot
 * @作者:wangjz
 * @日期:2018年7月5日上午10:06:38
 */
@Component
public class checkTokenInterceptor implements HandlerInterceptor{
	 @Autowired
	 private  JedisUtil jedisUtil;
	 @Autowired
	 private  HttpTool httpTool;
	
	/**
	 * @方法重写
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		ResponseData<String> data = new ResponseData<String>();
		StringBuffer url =request.getRequestURL();//得到完整的请求路径
		System.out.println("当前请求路径"+url);
		String token=request.getHeader("userToken");
		String id=request.getHeader("userId");
		String userToken=jedisUtil.getByKey("userToken"+id);//数据库中取得token

		if(token.equals(userToken)){
			jedisUtil.set("userToken"+id, token,500);//将token写入缓存,并延长存活时间
			return true;
		}else{
			data.setTokenState(1);
			httpTool.responseOutWithJson(response, data);
			return false;
		}
		
	}

	/**
	 * @方法重写
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @方法重写
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
