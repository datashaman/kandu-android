package com.inomma.kandu.ui.views;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.inomma.kandu.Utils;
import com.inomma.kandu.model.FormItem;
import com.inomma.kandu.model.FormSubmissionItem;

public class FormItemFileView extends FormItemView {

	private Button button;
	private File file;
	private static final int REQUEST_CAMERA = 1001;
	private static final int REQUEST_SELECT_FILE = 1002;
	private Activity activity;
	private int currentRequest;
	ImageView imageView;

	public FormItemFileView(Context context) {
		super(context);
	}

	public FormItemFileView(Activity context, FormItem item) {
		super(context, item);
		setId(Utils.getRandomUniqueNumber(context));
		this.activity = context;

	}

	public FormItemFileView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FormItemFileView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void fillContent(final Context context) {
		super.fillContent(context);
		button = new Button(context);
		button.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		if (item != null)
			button.setText("Add photo");
		addView(button);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				selectImage();
			}
		});
		imageView = new ImageView(getContext());
		imageView.setVisibility(View.GONE);
		imageView.setLayoutParams(new LinearLayout.LayoutParams(150, 150));
		imageView.setScaleType(ScaleType.CENTER_CROP);
		addView(imageView);

	}

	@Override
	public void setValue(FormSubmissionItem value) {
		try {
			String path = value.getValue();
			this.file = new File(path);
			Bitmap bm = Utils.decodeSampledBitmapFromFile(file, 200, 200);
			bm = Utils.decodeSampledBitmapFromFile(file, 200, 200);
			showCheckMark();
			imageView.setImageBitmap(bm);
			imageView.setVisibility(View.VISIBLE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public File getValue() {
		return file;
	}

	@Override
	public String getValueString() {
		return file.getAbsolutePath();
	}

	private void selectImage() {
		final CharSequence[] items = { "Take Photo", "Choose from Library",
				"Cancel" };
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setTitle("Add Photo!");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int item) {
				if (items[item].equals("Take Photo")) {
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					File f = new File(android.os.Environment
							.getExternalStorageDirectory(), getId()
							+ "temp.jpg");
					intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
					currentRequest = REQUEST_CAMERA;
					activity.startActivityForResult(intent, getId());
				} else if (items[item].equals("Choose from Library")) {
					Intent intent = new Intent(
							Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					intent.setType("image/*");
					currentRequest = REQUEST_SELECT_FILE;
					activity.startActivityForResult(
							Intent.createChooser(intent, "Select File"),
							getId());
				} else if (items[item].equals("Cancel")) {
					dialog.dismiss();
				}
			}
		});
		builder.show();
	}

	public void onActivityResult(int requestCod1e, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			Bitmap bm = null;

			if (currentRequest == REQUEST_CAMERA) {
				file = new File(Environment.getExternalStorageDirectory()
						.toString());
				for (File temp : file.listFiles()) {
					if (temp.getName().equals(getId() + "temp.jpg")) {
						file = temp;
						break;
					}
				}
				bm = Utils.decodeSampledBitmapFromFile(file, 200, 200);

			} else if (currentRequest == REQUEST_SELECT_FILE) {
				Uri selectedImageUri = data.getData();
				String tempPath = getPath(selectedImageUri, activity);
				file = new File(tempPath);
				bm = Utils.decodeSampledBitmapFromFile(file, 200, 200);
			}
			if (bm != null) {
				showCheckMark();
				imageView.setImageBitmap(bm);
				imageView.setVisibility(View.VISIBLE);
			}
		}
	}

	public String getPath(Uri uri, Activity activity) {
		String[] projection = { MediaColumns.DATA };
		@SuppressWarnings("deprecation")
		Cursor cursor = activity
				.managedQuery(uri, projection, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}
}
