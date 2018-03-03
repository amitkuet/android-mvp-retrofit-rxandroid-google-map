package app.engine.android.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class AndroidIntent {
    public AndroidIntent() {

    }
    public <T> void startActivity(Context context, Class<T> className) {
        Intent intent = new Intent(context, className);
        context.startActivity(intent);
    }
    public void startCameraActivity(Activity activity, int RESULT_ID) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(cameraIntent, RESULT_ID);
    }
    public void startFileSelectActivity(Activity activity, int RESULT_ID) {
        Intent intent = new Intent().setType("*/*").setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent, "Select a file"), RESULT_ID);
    }
}
