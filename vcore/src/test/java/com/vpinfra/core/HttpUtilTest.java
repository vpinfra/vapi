package com.vpinfra.core;

import com.vpinfra.core.utils.HttpUtil;
import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * http请求工具单元测试
 * 
 * @author <a href="mailto:yinjunfeng@vpgame.cn">Yinjf</a>
 * @date 2017年3月18日
 */
public class HttpUtilTest {

	@Test
	public void testGet() throws IOException {
		HttpResponse result = HttpUtil.get("https://www.baidu.com/", 3, null);
		Assert.assertNotNull(result);
	}
	
	@Test
	public void testPost() throws IOException {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("row_id", 44563);
		paramMap.put("content", 666);
		paramMap.put("comment_id", 0);
		paramMap.put("lang", "zh_CN");
		HttpResponse result = HttpUtil.post("http://www.vpgame.com/webservice/v2/article/comment/publish/", 3, paramMap, null);
		Assert.assertNotNull(result);
	}
	
}
