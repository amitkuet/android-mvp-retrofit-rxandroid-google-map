package app.engine.android.util;


public class Constants {
    public final String SHARED_PREF = "wellTravelSharedPref";


    public final String EMAIL_PATTERN = "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^]+[.-]?[a-zA-Z0-9]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+$";
    public final String PHONENUMBER_PATTERN = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
    public final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
    public final int LOGIN_EMPTY_FIELD = 1;

    //layout type
    public final String DRAWER_LAYOUT = "drawerLayout";
    public final String TOPBAR_LAYOUT = "topBarLayout";
    public final String EMPTY_LAYOUT = "emptyLayout";

    public final String MESSAGE_SOCKET_IO_HOST = "";
    public final String INTENT_DATA_IMAGE = "data";


    public final String API_BASE_URL = "";
    public final String GOOGLE_API_KEY = ""; //Please put your google api key here
}
