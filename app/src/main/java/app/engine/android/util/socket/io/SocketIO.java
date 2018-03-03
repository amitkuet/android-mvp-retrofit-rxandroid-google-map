package app.engine.android.util.socket.io;

import android.app.Activity;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import app.engine.android.AppEngine;

import org.json.JSONObject;

import java.net.URISyntaxException;

public class SocketIO {
    private Socket mSocket;
    private Activity activity;
    private SocketIOListner socketIOListner;
    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    socketIOListner.onMessageReceive(data);
                }
            });
        }
    };
    private Emitter.Listener onNewImageMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    socketIOListner.onImageMessageReceive(data);
                }
            });
        }
    };
    public Emitter.Listener getOnNewMessage() {
        return onNewMessage;
    }
    public Emitter.Listener getOnNewImageMessage() {
        return onNewImageMessage;
    }
    public Socket getConnected() {
        try {
            mSocket = IO.socket(AppEngine.getInstance().constants.MESSAGE_SOCKET_IO_HOST);
        } catch (URISyntaxException e) {
            e.getStackTrace();
        }
        return this.mSocket.connect();
    }
    public Socket getSocket() {
        return this.mSocket;
    }
    public void setSocketListner(SocketIOListner socketIOListner) {
        this.socketIOListner = socketIOListner;
    }
    public void setActivity(Activity activity) {
        this.activity = activity;
    }
    public void disconnect() {
        this.mSocket.disconnect();
    }
}
