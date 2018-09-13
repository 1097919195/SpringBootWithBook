/** */
package demo.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.util.RandomValidateCodeUtil;

/**
 * @功能:
 * @项目名:myspringboot
 * @作者:wangjz
 * @日期:2018年7月6日下午1:57:40
 */
@RestController
@RequestMapping("/base")
public class VerificationCode {
	/**
	 * 生成验证码
	 */
	@RequestMapping(value = "/getVerify")
	public void getVerify(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
	        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expire", 0);
	        RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
	        randomValidateCode.getRandcode(request, response);//输出验证码图片方法
	    } catch (Exception e) {
	        
	    }
	}
}
