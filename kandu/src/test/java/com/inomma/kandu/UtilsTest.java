/**
 * 
 */
package com.inomma.kandu;

import android.util.Log;

import junit.framework.Assert;

import static org.mockito.Mockito.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author marlinf
 *
 */
public class UtilsTest {

	/**
	 * Test method for {@link com.inomma.kandu.Utils#mapFromJsonArray(org.json.JSONArray)}.
	 * @throws JSONException 
	 */
	@Test
	public void testMapFromJsonArray() throws JSONException {
        JSONArray array = mock(JSONArray.class);

        array.put("a");
        array.put("a b");
        array.put("a_b");
        array.put("a__b");

        Map<String, String> map = Utils.mapFromJsonArray(array);
	}

	/**
	 * Test method for {@link com.inomma.kandu.Utils#mapFromJsonObject(org.json.JSONObject)}.
	 * @throws JSONException
	 */
	@Test
	public void testMapFromJsonObject() throws JSONException {
		Map<String, String> values = new HashMap<String, String>();

        values.put("a", "A");
		values.put("a b", "A B");
		values.put("a_b", "A_B");
		values.put("a__b", "A__B");
		
		JSONObject object = mock(JSONObject.class);

        object.put("a", "A");
        object.put("a b", "A B");
        object.put("a_b", "A_B");
        object.put("a__b", "A__B");

        System.out.println(object);

        assertNotNull("object not null", object);

        Map<String, String> map = Utils.mapFromJsonObject(object);
		
		assertEquals("The output should equals the input", values, map);
		assertNotSame("The output should not be the same as the input", values, map);
	}
}
