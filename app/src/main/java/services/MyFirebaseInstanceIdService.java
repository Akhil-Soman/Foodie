package services;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import storage.SharedPreferenceStore;
import ws.WebServices;

/**
 * Created by user on 16/1/17.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();
        String refreshedToken= FirebaseInstanceId.getInstance().getToken();
        System.out.println("FOOD :::: TOKEN :::: "+refreshedToken);
        SharedPreferenceStore.getInstance(getApplicationContext()).setFCM_TOKEN(refreshedToken);

        WebServices.sendDevToken(refreshedToken);
        
        //WebServices.sendFCM(refreshedToken);

    }
}
