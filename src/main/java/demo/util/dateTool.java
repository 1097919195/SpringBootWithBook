/** */
package demo.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * @功能:
 * @项目名:study
 * @作者:wangjz
 * @日期:2018年6月27日下午5:48:32
 */
public class dateTool {
    public static long dateConvert() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long t = d.getTime();

        long time = Long.parseLong(sdf.format(t));
        return time;

    }

}
