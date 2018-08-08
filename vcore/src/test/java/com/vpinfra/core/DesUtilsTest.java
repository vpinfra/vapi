package com.vpinfra.core;

import com.vpinfra.core.utils.DesUtils;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

/**
 * @author <a href="mailto:yinjunfeng@vpgame.cn">Yinjf</a>
 * @date 2018年1月15日
 */
public class DesUtilsTest {

    @Test
    public void test() {
        try {
            String test = "{\"shared_secret\":\"irj7/tpc4t9dNHc6+zttMDOQF5k=\",\"serial_number\":\"4422934853890004355\",\"revocation_code\":\"R01420\",\"uri\":\"otpauth://totp/Steam:bot7005vpgame?secret=RK4PX7W2LTRN6XJUO45PWO3NGAZZAF4Z&issuer=Steam\",\"server_time\":1483682239,\"account_name\":\"bot7005vpgame\",\"token_gid\":\"8cd695eca3d21c2\",\"identity_secret\":\"+Xoz8KTGyaWOQXJhP11ITDkwt1Q=\",\"secret_1\":\"yXMSRV5uDwg7/fNReslu13vWVzA=\",\"status\":1,\"device_id\":\"android:b1912671-d159-2671-2671-2671d15955b5\",\"fully_enrolled\":true,\"Session\":{\"SessionID\":\"94a4063a52e30e191a10f722\",\"SteamLogin\":\"76561198356136681%7C%7CCDC9F7775319959C112FF99ABCC8B20073D341B1\",\"SteamLoginSecure\":\"76561198356136681%7C%7CF20058F8B1B9D61EAF23F7BB337A00C1AE431129\",\"WebCookie\":null,\"OAuthToken\":\"791d8c99ed149fb5c6c3c9d85dba7f7c\",\"SteamID\":76561198356136681}}";
            String enResult = DesUtils.encrypt(test.getBytes(), "123444444");
            System.out.println(enResult);
            String descResult = DesUtils.decrypt(Base64.decodeBase64(enResult.getBytes()), "123444444");
            System.out.println(descResult);
            System.out.println("加密后的字符：" + enResult);
            System.out.println("解密后的字符：" + descResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
