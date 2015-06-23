/**
 * 
 */
package com.inomma.kandu.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;


/**
 * @author marlinf
 *
 */
@RunWith(RoboelectricTestRunner.class)
@Config(manifest = "src/main/AndroidMainfest.xml", sdk = 19)
public class FormItemTest {

    @Test
    public void testChoicesArray() throws JSONException {
        String[] items = { "a", "a b", "a_b", "a__b" };
        JSONArray choices = new JSONArray(items);

        JSONObject config = new JSONObject();
        config.put("name", "field");
        config.put("type", "choice");
        config.put("choices", choices);

        FormItem item = new FormItem(config);
        Map<String, String> output = item.getChoices();

        System.out.println(output);
    }

    @Test
    public void testChoicesObject() throws JSONException {
        JSONObject choices = new JSONObject();
        choices.put("a", "A");
        choices.put("a b", "A B");
        choices.put("a_b", "A_B");
        choices.put("a__b", "A__B");

        JSONObject config = new JSONObject();
        config.put("name", "field");
        config.put("type", "choices");
        config.put("choices", choices);

        FormItem item = new FormItem(config);
        Map<String, String> output = item.getChoices();

        System.out.println(output);
    }

}
