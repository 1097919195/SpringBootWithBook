/** */
package demo.user;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.util.NoteUtil;
import demo.util.ResponseData;
import demo.util.redis.JedisUtil;

/**
 * @功能:
 * @项目名:itcast-springboot2
 * @作者:wangjz
 * @日期:2018年6月20日下午2:53:31
 */
@Service
public class userService {
    @Autowired
    private userDao userdao;
    @Autowired
    private JedisUtil jedisUtil;

    //用户登陆
    public ResponseData<userPojo> login(userPojo user) {
        ResponseData<userPojo> data = new ResponseData<userPojo>();
        userPojo man = userdao.findByUserAccount(user);
        if (man == null) {
            data.setMessage("该用户不存在");
            data.setState(1);
            return data;
        }

        //对请求传过来的密码数据加密
        String md5Password = NoteUtil.md5(user.getPassword());

        if (md5Password.equals(man.getPassword())) {

            String token = UUID.randomUUID().toString();//设置登陆token
            man.setLoginToken(token);
            jedisUtil.set("userToken" + man.getId(), token, 120);//将token写入缓存

            data.setState(0);
            data.setMessage("登陆成功");
            data.setData(man);

        } else {
            data.setMessage("密码错误");
            data.setState(2);
        }

        return data;
    }

    //用户注册
    public ResponseData<userPojo> insertUser(userPojo user) {
        ResponseData<userPojo> data = new ResponseData<userPojo>();
        userPojo mnguser = new userPojo();
        mnguser.setAccount(user.getAccount());
        userPojo man = userdao.findByUserAccount(user);//查找注册的账户是否已存在
        String mdPassword = NoteUtil.md5(user.getPassword());
        if (man != null) {
            data.setMessage("该用户已存在");
            data.setState(1);
            return data;
        } else {
            mnguser.setPassword(mdPassword);
            mnguser.setSex("未知");
            mnguser.setUserName(user.getUserName());
            userdao.insert(mnguser);
            data.setMessage("注册成功");
            data.setState(0);
            data.setData(mnguser);
        }

        return data;
    }

    //修改密码
    public ResponseData<userPojo> change(userPojo user) {
        ResponseData<userPojo> data = new ResponseData<userPojo>();
        userPojo mnguser = new userPojo();
        mnguser.setId(user.getId());
        userPojo man = userdao.findByUserId(user);
        String oldPassword = NoteUtil.md5(user.getPassword());

        if (oldPassword.equals(man.getPassword())) {
            String final_password = NoteUtil.md5(user.getFinal_password());
            mnguser.setPassword(final_password);
            userdao.updatePassword(mnguser);
            data.setState(0);
            data.setMessage("修改密码成功");

        } else {
            data.setState(1);
            data.setMessage("原密码输入错误");
            return data;

        }

        return data;
    }
}
