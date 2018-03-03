package app.engine.android.util.socket.io;

import org.json.JSONObject;

public interface SocketIOListner {
    public void onMessageReceive(JSONObject data);
    public void onImageMessageReceive(JSONObject data);
}
