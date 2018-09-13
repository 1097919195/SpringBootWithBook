/** */
package demo.user;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.util.ResponseData;
import demo.util.redis.JedisUtil;



/**
 * @功能:
 * @项目名:itcast-springboot2
 * @作者:wangjz
 * @日期:2018年6月20日下午2:53:15
 */
@RestController
@RequestMapping("/mngUser")
public class userController {
	@Autowired
	private userService userservice;
	 @Autowired
	 private  JedisUtil jedisUtil;
	//用户登陆
	@RequestMapping("/addLogin")
	public ResponseData<userPojo> addLogin(userPojo user, HttpServletRequest request,
			HttpServletResponse response) {
		return userservice.login(user);
	}
	//新增用户
	@RequestMapping("/insertOne")
	public ResponseData<userPojo> insertOne(userPojo user, HttpServletRequest request,
			HttpServletResponse response) {
		return userservice.insertUser(user);
	}
	//修改密码
	@RequestMapping("/update")
	public ResponseData<userPojo> update(userPojo user, HttpServletRequest request,
			HttpServletResponse response) {
		return userservice.change(user);
	}
	
	//退出登录
	@RequestMapping("/quitLogin")
	public ResponseData<String> quitLogin(String userId, HttpServletRequest request,HttpServletResponse response) {
		 ResponseData<String> data=new ResponseData<String>();
		if(jedisUtil.getByKey("userToken"+userId)!=null){
			 jedisUtil.delByKey("userToken"+userId);//清除token
			 data.setTokenState(0);
		}else{
			 data.setTokenState(1);
		}
		return data;
	}
}
