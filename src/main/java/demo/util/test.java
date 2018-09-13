/** */
package demo.util;

/**
 * @功能:
 * @项目名:myspringboot
 * @作者:wangjz
 * @日期:2018年7月5日下午1:36:02
 */
public class test {


    public static void main(String[] args) {
        ResponseData<String> a = new ResponseData<String>();
        a.setData("hahaha");
        String b = a.toString();
        System.out.println(b);

    }

}
