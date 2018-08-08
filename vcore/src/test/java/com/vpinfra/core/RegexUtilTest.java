package com.vpinfra.core;

import com.vpinfra.core.utils.RegexUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author <a href="mailto:yinjunfeng@vpgame.cn">Yinjf</a>
 * @date 2018年1月16日
 */
public class RegexUtilTest {

    @Test
    public void test() {
        Assert.assertEquals(true, RegexUtil.isEmail("test@qq.com"));
        Assert.assertEquals(false, RegexUtil.isEmail("testqq.com"));
        Assert.assertEquals(true, RegexUtil.isIp("192.168.0.1"));
        Assert.assertEquals(false, RegexUtil.isIp("192.168.0"));
    }

}
