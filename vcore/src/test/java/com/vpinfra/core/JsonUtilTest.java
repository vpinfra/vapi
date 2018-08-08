package com.vpinfra.core;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vpinfra.core.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * json工具单元测试类
 * 
 * @author <a href="mailto:yinjunfeng@vpgame.cn">Yinjf</a>
 * @date 2017年3月18日
 */
public class JsonUtilTest {

	@Test
	public void test() {
		String jsonStr = "{\"times\":[\"2017-03-01\",\"2017-03-02\",\"2017-03-03\"],\"datas\":[3,1,2]}";
		
    	JsonObject jsonObject = JsonUtil.toJsonObject(jsonStr);
    	Assert.assertNotNull(jsonObject);
    	
    	JsonArray array = JsonUtil.toJsonArray(jsonObject.get("times").toString());
    	Assert.assertNotNull(array);
    	Assert.assertEquals(3, array.size());
    	
    	List<String> times = JsonUtil.toList(jsonObject.get("times"));
    	Assert.assertNotNull(times);
    	Assert.assertEquals(3, times.size());
    	
    	Map<String, JsonArray> map = JsonUtil.toMap(jsonObject);
    	Assert.assertNotNull(map);
    	Assert.assertNotNull(map.get("datas"));
    	
    	TestModel model = JsonUtil.toObject(jsonObject, TestModel.class);
    	Assert.assertNotNull(model);
    	Assert.assertEquals(3, model.getTimes().size());
	}
	
	class TestModel {
		
		private List<String> times;
		
		private List<Integer> datas;

		public List<String> getTimes() {
			return times;
		}

		public void setTimes(List<String> times) {
			this.times = times;
		}

		public List<Integer> getDatas() {
			return datas;
		}

		public void setDatas(List<Integer> datas) {
			this.datas = datas;
		}
	}

}
