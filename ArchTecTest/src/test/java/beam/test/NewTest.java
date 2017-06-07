package beam.test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.Test;

public class NewTest {
	public static final int NO_PARAM_KEY = 0;
	public static final int NULL_PARAM_KEY = 53;
	
	public Object generate(Object target, Method method, Object... params) {
		if (params.length == 1) {
			return (params[0] == null ? NULL_PARAM_KEY : params[0]);
		}
		if (params.length == 0) {
			return NO_PARAM_KEY;
		}
		int hashCode = 17;
		for (Object object : params) {
			hashCode = 31 * hashCode + (object == null ? NULL_PARAM_KEY : object.hashCode());
		}
		return Integer.valueOf(hashCode);
	}
	
	@Test
	public void t(){
		/*
		 * {_guid::_id::program:version:{focusid=586001, formatid=PMRS65626, hbointer
nalid=PROD586001}} with {_id:urn:esp:hbo:program:version:4e4247c345cff0766783e8e7f8d36aeb}
{_guid::_id::program:version:{focusid=586002, formatid=PMRS65628, hbointernalid=PROD586002}} with {_id:urn:esp:hbo:program:version:4e4247c345cff0766783e8e7f8d36aeb}
		 * */
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("focusid", "586001");
		map1.put("formatid", "PMRS65626");
		map1.put("hbointernalid", "PROD586001");
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("focusid", "586002");
		map2.put("formatid", "PMRS65628");
		map2.put("hbointernalid", "PROD586002");
		System.out.println(map1.hashCode());
		System.out.println(map2.hashCode());
	}
	
	@Test
	public void f() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("1", null);
		map.put("2", null);
		map.put("3", null);
		map.put("4", null);
		map.put("5", null);
		map.put("6", null);
		Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
		int count = 0;
		map.remove("1");
		while(it.hasNext()){
			it.next();
			count++;
		}
		System.out.println(count);
	}
}
