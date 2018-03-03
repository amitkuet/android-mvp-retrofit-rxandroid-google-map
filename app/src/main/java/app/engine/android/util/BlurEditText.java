package app.engine.android.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class BlurEditText {
    private Activity activity;
    private boolean disable;
    public BlurEditText() {
        this.disable = false;
    }
    public void setDisable(boolean disable) {
        this.disable = disable;
    }
    public BlurEditText setContext(Activity activity) {
        this.activity = activity;
        return this;
    }
    public <T> MotionEvent blurEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = activity.getCurrentFocus();
            if (v instanceof EditText && !this.disable) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return event;
    }
}
