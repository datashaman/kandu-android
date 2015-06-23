package com.inomma.kandu.ui.activities;

import com.inomma.kandu.R;
import java.util.Scanner;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

@LargeTest
public class FillFormActivityTest extends ActivityInstrumentationTestCase2<FillFormActivity> {
    public FillFormActivityTest() {
        super(FillFormActivity.class);
    }

	private String convertStreamToString(java.io.InputStream is) {
	    Scanner s = new Scanner(is).useDelimiter("\\A");
	    String string = s.hasNext() ? s.next() : "";
	    s.close();
	    return string;
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		getActivity();
    }

    /*
		Context context = getInstrumentation().getContext();
		Resources resources = context.getResources();
		
		InputStream stream = resources.openRawResource(com.inomma.kandu.R.raw.config);
		String json = convertStreamToString(stream);
		
		JSONArray forms = new JSONArray(json);
        com.inomma.kandu.model.Config config = new com.inomma.kandu.model.Config(forms);

		List<UserForm> userForms = new ArrayList<UserForm>();
		
		UserForm userForm = new UserForm();
		userForm.setUrl("/api/getSubmissions/Test_Form/");
		userForm.setVisibleName("Test Form");
		userForm.setKey("Test_Form");
		
		userForms.add(userForm);
		
		UserFormsHolder.newInstance(config, userForms);
		
		UserForm form = UserFormsHolder.getInstance().getUserFormWithKey("Test_Form");

		Intent intent = new Intent();
		intent.putExtra("userform", form);
		
		setActivityInitialTouchMode(true);
		setActivityIntent(intent);

        FillFormActivity activity = getActivity();
		mainView = activity.getMainView();
	}
	
	@MediumTest
	public void testViewItems()
	{
		FormItemSingleChoiceView choiceView = (FormItemSingleChoiceView) mainView.getViews().get("Array_config");
		
		Spinner spinner = choiceView.getSpinner();
		ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinner.getAdapter();
		
		assertEquals("Adapter should have 3 items", 3, adapter.getCount());
		assertEquals("First key should be a__b", "a__b", adapter.getItem(1));
		assertEquals("Second key should be a", "a", adapter.getItem(2));
		
		TouchUtils.clickView(this, choiceView);
		TouchUtils.clickView(this, choiceView.getChildAt(2));

		assertEquals("Should be a when set to a", "a", choiceView.getValue());
	}
	*/

    public void testIsNotNull() {
        onView(withText("Hello world!")).check(matches(isDisplayed()));
    }
}
