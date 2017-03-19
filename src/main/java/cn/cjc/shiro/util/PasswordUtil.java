package cn.cjc.shiro.util;

import cn.cjc.shiro.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class PasswordUtil {

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    public static void encryptPassword(User user) {
        user.setPasswordSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash("md5", user.getPassword(), user.getCredentialsSalt(), 2).toHex();
        user.setPassword(newPassword);
    }
}
