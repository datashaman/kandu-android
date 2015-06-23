/**
 * 
 */
package com.inomma.kandu;

import android.util.Log;

import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.junit.Test;

import java.lang.RuntimeException;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author marlinf
 *
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", sdk = 19)
public class UtilsTest {

	/**
	 * Test method for {@link com.inomma.kandu.Utils#mapFromJsonArray(org.json.JSONArray)}.
	 * @throws JSONException 
	 */
	@Test
	public void testMapFromJsonArray() throws JSONException {
	    String[] items = { "a", "a b", "a_b", "a__b" };
        JSONArray array = new JSONArray(items);

        Map<String, String> map = Utils.mapFromJsonArray(array);
	}

	/**
	 * Test method for {@link com.inomma.kandu.Utils#mapFromJsonObject(org.json.JSONObject)}.
	 * @throws JSONException
	 */
	@Test
	public void testMapFromJsonObject() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("a", "A");
        object.put("a b", "A B");
        object.put("a_b", "A_B");
        object.put("a__b", "A__B");

        Map<String, String> map = Utils.mapFromJsonObject(object);
	}
}
