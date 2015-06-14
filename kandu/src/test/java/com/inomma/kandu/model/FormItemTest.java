/**
 * 
 */
package com.inomma.kandu.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static org.mockito.Mockito.*;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;


/**
 * @author marlinf
 *
 */
public class FormItemTest {

    @Test
    public void testChoicesArray() throws JSONException {
        JSONObject config = mock(JSONObject.class);
        JSONArray choices = mock(JSONArray.class);

        when(choices.length()).thenReturn(4);

        when(choices.getString(0)).thenReturn("a");
        when(choices.getString(1)).thenReturn("a b");
        when(choices.getString(2)).thenReturn("a_b");
        when(choices.getString(3)).thenReturn("a__b");

        when(config.getString("name")).thenReturn("field");
        when(config.getString("type")).thenReturn("choice");
        when(config.has("choices")).thenReturn(true);
        when(config.getJSONArray("choices")).thenReturn(choices);
        when(config.getJSONObject("choices")).thenThrow(JSONException.class);

        FormItem item = new FormItem(config);
        Map<String, String> output = item.getChoices();

        System.out.println(output);
    }

    @Test
    public void testChoicesObject() throws JSONException {
        JSONObject config = mock(JSONObject.class);
        JSONArray names = mock(JSONArray.class);

        when(names.length()).thenReturn(4);

        when(names.get(0)).thenReturn("a");
        when(names.get(1)).thenReturn("a b");
        when(names.get(2)).thenReturn("a_b");
        when(names.get(3)).thenReturn("a__b");

        JSONObject choices = mock(JSONObject.class);

        when(choices.names()).thenReturn(names);

        when(choices.getString("a")).thenReturn("A");
        when(choices.getString("a b")).thenReturn("A B");
        when(choices.getString("a_b")).thenReturn("A_B");
        when(choices.getString("a__b")).thenReturn("A__B");

        when(config.getString("name")).thenReturn("field");
        when(config.getString("type")).thenReturn("choice");
        when(config.has("choices")).thenReturn(true);
        when(config.getJSONArray("choices")).thenThrow(JSONException.class);
        when(config.getJSONObject("choices")).thenReturn(choices);

        FormItem item = new FormItem(config);
        Map<String, String> output = item.getChoices();

        System.out.println(output);
    }

}
