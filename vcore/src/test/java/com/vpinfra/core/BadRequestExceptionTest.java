package com.vpinfra.core;

import com.google.gson.JsonObject;
import com.vpinfra.core.exception.BadRequestException;
import com.vpinfra.core.model.ErrorInfo;
import com.vpinfra.core.utils.JsonUtil;
import com.vpinfra.core.utils.RandomUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:yinjunfeng@vpgame.cn">Yinjf</a>
 * @date 2018/8/8
 */
public class BadRequestExceptionTest {

    @Test
    public void test() {
        try {
            throw new BadRequestException(ErrorInfo.builder().message("testCommon").build());
        } catch (BadRequestException e) {
            Assert.assertEquals("testCommon", e.getErrorInfo().getMessage());
        }
    }

    @Test
    public void testFormatMessage() {
        String uuid = RandomUtil.getUUID(8);
        try {
            Map<String, Object> extraParam = new HashMap<>();
            extraParam.put("uuid", uuid);
            ErrorInfo errorInfo = ErrorInfo.builder()
                                            .message("uuid is #uuid#")
                                            .extraParam(extraParam)
                                            .needFormat(true)
                                            .build();
            throw new BadRequestException(errorInfo);
        } catch (BadRequestException e) {
            ErrorInfo errorInfo = e.getErrorInfo();
            Assert.assertEquals("uuid is " + uuid , errorInfo.getMessage());

            JsonObject jsonObject = JsonUtil.toJsonObject(errorInfo);
            Assert.assertEquals("uuid is " + uuid , jsonObject.get("message").getAsString());
        }
    }
}
