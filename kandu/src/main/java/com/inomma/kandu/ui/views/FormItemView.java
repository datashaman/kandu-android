package com.inomma.kandu.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.inomma.kandu.R;
import com.inomma.kandu.model.FormItem;
import com.inomma.kandu.model.FormSubmissionItem;

public abstract class FormItemView extends LinearLayout {

	protected FormItem item;
	protected TextView label;
	protected ImageView checkMark;

	public FormItemView(Context context, FormItem item) {
		super(context);
		this.item = item;
		fillContent(context);
	}

	public FormItemView(Context context) {
		super(context);
		fillContent(context);
	}

	public FormItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		fillContent(context);
	}

	public FormItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		fillContent(context);
	}

	protected void fillContent(Context context) {
		setPadding(0, 30, 0, 0);
		setOrientation(VERTICAL);
		if (item != null) {
			RelativeLayout layout = new RelativeLayout(context);
			layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT));
			addView(layout);
			checkMark = new ImageView(context);
			checkMark.setImageResource(R.drawable.checkmark);
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			checkMark.setLayoutParams(layoutParams);
			layout.addView(checkMark);
			label = new TextView(context);
			label.setTextSize(20);
			label.setText(item.getVisibleName());
			label.setLayoutParams(new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT));
			layout.addView(label);
			setTag(item);
			checkMark.setVisibility(View.GONE);
		}
	}

	public void setText(CharSequence text) {
		label.setText(text);
	}

	public abstract void setValue(FormSubmissionItem value);

	public abstract Object getValue();

	public abstract String getValueString();

	public void showCheckMark() {
		if (checkMark != null)
			checkMark.setVisibility(View.VISIBLE);
	}

	public void hideCheckmark() {
		if (checkMark != null)

			checkMark.setVisibility(View.GONE);
	}
	
	public boolean showDefaultValue(){
		return false	;
	}
}
