package app.engine.android.util;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class TimerUtil {

    private Timer timer;
    private TimerTask timerTask;
    private final Handler handler = new Handler();

    public TimerUtil() {

    }
    public <T> void startTimer(Context context, Class<T> activity, long time) {
        timer = new Timer();
        initializeTimerTask(context, activity);
        timer.schedule(timerTask, time); //
    }

    private void stoptimertask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private <T> void initializeTimerTask(final Context context, final Class<T> activity) {
        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        Intent intent = new Intent(context, activity);
                        context.startActivity(intent);
                        stoptimertask();
                    }
                });
            }
        };
    }
}
