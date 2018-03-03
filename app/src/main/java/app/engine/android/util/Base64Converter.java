package app.engine.android.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;

import app.engine.android.AppEngine;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Base64Converter {

    public String imageUriToString(Activity activity, Uri imageUri) {
        Uri selectedfile = imageUri;
        FileInputStream fis = null;
        InputStream in = null;
        try{
            in=activity.getContentResolver().openInputStream(selectedfile);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        Bitmap bm = BitmapFactory.decodeStream(in);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    public String imageToString(Activity activity, Intent data) {
        Bitmap bm = (Bitmap) data.getExtras().get(AppEngine.getInstance().constants.INTENT_DATA_IMAGE);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    public Bitmap stringToBitmap(String b64String) {
        byte[] decodedString = Base64.decode(b64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
