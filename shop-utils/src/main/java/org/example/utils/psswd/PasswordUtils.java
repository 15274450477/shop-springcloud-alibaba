package org.example.utils.psswd;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.example.utils.md5.MD5Hash;

public class PasswordUtils {
    /**
     * 加密密码<br/>
     */
    public static String encryptPassword(String pwd) {
        if (StringUtils.isBlank(pwd)) {
            return null;
        }
        return MD5Hash.digest(pwd);
    }

}
