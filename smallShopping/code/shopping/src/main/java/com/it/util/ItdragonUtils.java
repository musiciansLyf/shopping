package com.it.util;

import com.it.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

/**
 * 工具类
 *
 * @author itdragon
 */
@Component
public class ItdragonUtils {

    private static final String ALGORITHM_NAME = "MD5";
    private static final Integer HASH_ITERATIONS = 1024;

    /**
     * 加盐加密的策略非常多,根据实际业务来
     */
    public void entryptPassword(User user) {
        String salt = UUID.randomUUID().toString();
        String temPassword = user.getPlainPassword();
        Object md5Password = new SimpleHash(ALGORITHM_NAME, temPassword, ByteSource.Util.bytes(salt), HASH_ITERATIONS);
        user.setSalt(salt);
        user.setPassword(md5Password.toString());
    }

    public static String getCurrentDateTime() {
        TimeZone zone = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(zone);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    /**
     * 获取ShiroSession
     *
     * @return
     */
    public Session getShiroSession() {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        return session;
    }

    /**
     * 获取Session用户信息
     *
     * @return
     */
    public User getSessionUser() {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        User user = (User) session.getAttribute("userInfo");
        return user;
    }

    public String getOrderIdByUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) { //有可能是负数
            hashCodeV = -hashCodeV;
        }
//         0 代表前面补充0     
//         4 代表长度为4     
//         d 代表参数为正数型
        return machineId + String.format("%015d", hashCodeV);
    }

    /**
     * 判断用户是否登录
     */
    public boolean isGogin() {
        Subject currentUser = SecurityUtils.getSubject();
        return currentUser.isAuthenticated();
    }

    public static boolean stringIsNotBlack(String string) {
        if (string != null && !"".equals(string)) {
            return true;
        }
        return false;
    }
}
