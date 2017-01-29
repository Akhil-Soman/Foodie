package storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 19/1/17.
 */

public class SharedPreferenceStore {

    private final String PREF_NAME="SPS";
    private final String FCM_TOKEN="FCM_TOKEN";


    private SharedPreferences pref;
    private static SharedPreferenceStore mInstance;
    private SharedPreferences.Editor editor;


    public SharedPreferenceStore(Context context){
        pref=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
    }

    public static synchronized SharedPreferenceStore getInstance(Context context){
        if (mInstance == null) {
            mInstance = new SharedPreferenceStore(context);
        }
        return mInstance;
    }

    public void setFCM_TOKEN(String token){
        editor=pref.edit();
        editor.putString(FCM_TOKEN,token);
        editor.commit();
    }

    public String getFCM_TOKEN(){
        return pref.getString(FCM_TOKEN,"");
    }

}
