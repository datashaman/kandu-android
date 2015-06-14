/**
 * 
 */
package com.inomma.kandu;

import android.util.Log;

import com.google.android.gms.ads.internal.request.StringParcel;

import junit.framework.Assert;

import static org.mockito.Mockito.*;

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
public class UtilsTest {

	/**
	 * Test method for {@link com.inomma.kandu.Utils#mapFromJsonArray(org.json.JSONArray)}.
	 * @throws JSONException 
	 */
	@Test
	public void testMapFromJsonArray() throws JSONException {
        JSONArray array = mock(JSONArray.class);

        when(array.length()).thenReturn(4);

        when(array.getString(0)).thenReturn("a");
        when(array.getString(1)).thenReturn("a b");
        when(array.getString(2)).thenReturn("a_b");
        when(array.getString(3)).thenReturn("a__b");

        Map<String, String> map = Utils.mapFromJsonArray(array);
	}

	/**
	 * Test method for {@link com.inomma.kandu.Utils#mapFromJsonObject(org.json.JSONObject)}.
	 * @throws JSONException
	 */
	@Test
	public void testMapFromJsonObject() throws JSONException {
        JSONArray names = mock(JSONArray.class);

        when(names.length()).thenReturn(4);

        when(names.get(0)).thenReturn("a");
        when(names.get(1)).thenReturn("a b");
        when(names.get(2)).thenReturn("a_b");
        when(names.get(3)).thenReturn("a__b");

        JSONObject object = mock(JSONObject.class);

        when(object.names()).thenReturn(names);

        when(object.getString("a")).thenReturn("A");
        when(object.getString("a b")).thenReturn("A B");
        when(object.getString("a_b")).thenReturn("A_B");
        when(object.getString("a__b")).thenReturn("A__B");

        Map<String, String> map = Utils.mapFromJsonObject(object);
	}
}
