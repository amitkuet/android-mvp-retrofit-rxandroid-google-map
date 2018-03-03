package app.engine.android;

import app.engine.android.util.AndroidIntent;
import app.engine.android.util.Animation;
import app.engine.android.util.Base64Converter;
import app.engine.android.util.BlurEditText;
import app.engine.android.util.CommonUtils;
import app.engine.android.util.Constants;
import app.engine.android.util.DateTimeHelper;
import app.engine.android.util.GoogleMapView;
import app.engine.android.util.ListViewHelper;
import app.engine.android.util.MenuItemList;
import app.engine.android.util.NetworkAdapter;
import app.engine.android.util.NetworkUtils;
import app.engine.android.util.SVGImage.SVGImageRederer;
import app.engine.android.util.SharedPrefUtils;
import app.engine.android.util.SpinnerUtil;
import app.engine.android.util.TimerUtil;
import app.engine.android.util.socket.io.SocketIO;

public class AppEngine {
    private static volatile AppEngine ourInstance;
    private static final Object mutex = new Object();

    public final NetworkAdapter networkAdapter;
    public final Constants constants;
    public final TimerUtil timerUtil;
    public final AndroidIntent androidIntent;
    public final BlurEditText blurEditText;
    public final GoogleMapView googleMapView;
    public final SVGImageRederer svgImageRederer;
    public final SpinnerUtil spinnerUtil;
    public final MenuItemList menuItemList;
    public final ListViewHelper listViewHelper;
    public final SharedPrefUtils sharedPrefUtils;
    public final CommonUtils commonUtils;
    public final SocketIO socketIO;
    public final Animation animation;
    public final Base64Converter base64Converter;
    public final DateTimeHelper dateTimeHelper;
    public final NetworkUtils networkUtils;

    private AppEngine() {
        networkAdapter = new NetworkAdapter();
        constants = new Constants();
        timerUtil = new TimerUtil();
        androidIntent = new AndroidIntent();
        blurEditText = new BlurEditText();
        googleMapView = new GoogleMapView();
        svgImageRederer = new SVGImageRederer();
        spinnerUtil = new SpinnerUtil();
        menuItemList = new MenuItemList();
        listViewHelper = new ListViewHelper();
        sharedPrefUtils =  new SharedPrefUtils();
        commonUtils = new CommonUtils();
        socketIO = new SocketIO();
        animation = new Animation();
        base64Converter = new Base64Converter();
        dateTimeHelper = new DateTimeHelper();
        networkUtils = new NetworkUtils();
    }

    public static AppEngine getInstance() {
        AppEngine result = ourInstance;
        if (result == null) {
            synchronized (mutex) {
                result = ourInstance;
                if (result == null)
                    ourInstance = result = new AppEngine();
            }
        }
        return result;
    }
}
