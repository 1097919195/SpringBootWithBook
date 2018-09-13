/** */
package demo.base;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @功能:
 * @项目名:myspringboot
 * @作者:wangjz
 * @日期:2018年7月9日上午9:24:33
 */
@RestController
@RequestMapping("/base")
public class VerificationCodeController {
	/**
	 * 登录页面校验验证码
	 */
	@RequestMapping(value = "/checkVerify")
	public boolean checkVerify(String  verify, HttpSession session) {
	    try{
	        //从session中获取随机数
	        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
	        if (random == null) {
	            return false;
	        }
	        if (random.equals(verify)) {
	        	System.out.println("验证码校验成功");
	            return true;
	        } else {
	        	System.out.println("校验码验证失败");
	            return false;
	        }
	    }catch (Exception e){
	    	
	        return false;
	    }
	}
}
