package com.foodidentifier.data.utilities;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import static com.foodidentifier.data.utilities.Constants.TAG;

/**
 * Created by taras on 11/11/2017.
 */

public class Utility {

    private static final String UTF_8 = "UTF-8";

    public static String loadJSONFromAsset(Context context, String fileName) {
        String json;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, UTF_8);
        } catch (IOException ex) {
            Log.e(TAG, ex.toString());
            return null;
        }
        return json;
    }
}
