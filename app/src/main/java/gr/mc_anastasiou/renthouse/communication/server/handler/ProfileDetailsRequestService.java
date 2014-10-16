package gr.mc_anastasiou.renthouse.communication.server.handler;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.android.volley.Request;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import gr.mc_anastasiou.renthouse.communication.server.requests.ProfileRequestBody;
import gr.mc_anastasiou.renthouse.communication.server.requests.ProfileRequestResult;
import gr.mc_anastasiou.renthouse.communication.server.volley.GsonClassRequest;
import gr.mc_anastasiou.renthouse.communication.server.volley.JsonListener;
import gr.mc_anastasiou.renthouse.communication.server.volley.UiListener;
import gr.mc_anastasiou.renthouse.communication.server.volley.VolleyProvider;
import gr.mc_anastasiou.renthouse.core.Singleton;

public class ProfileDetailsRequestService extends Service {
    private final LocalBinder localBinder = new LocalBinder();
    public OnServerResponseInterface serverResponseInterface;
    private  Map<String, String> headers = new HashMap<String, String>();

    public ProfileDetailsRequestService() {
        headers.put(Singleton.PROFILE_DETAILS_ID_HEADER, String.valueOf(Singleton.getInstance().getLoginSession().getAccountId()));
    }

    public void getProfileDetailsData(){
        String  profileDetailsDataReqTag = "profileDetailsData_req";

        String url = "http://10.77.0.167/profileDetailsData.php";

        GsonClassRequest<ProfileRequestResult> gsonRequest = new GsonClassRequest<ProfileRequestResult>(
                Request.Method.POST, url, null, ProfileRequestResult.class, new JsonListener<ProfileRequestResult>(new UiListener<ProfileRequestResult>() {
            @Override
            public void onResponse(ProfileRequestResult response) {
                if(serverResponseInterface != null){
                    serverResponseInterface.onServerResult(response);
                }
            }

            @Override
            public void onErrorResponse(int errCode, String errMsg) {
                if(serverResponseInterface != null){
                    serverResponseInterface.onServerError(errMsg);
                }
            }
        }), headers);
        // Adding request to request queue
        VolleyProvider.getInstance(this).addRequest(gsonRequest, profileDetailsDataReqTag);
    }

    /* Only update because every profile is being created
    in sign up
     */
    public void updateProfileDetailsRequest(ProfileRequestBody requestBody){
        String  profileDetailsUpdateReqTag = "profileDetailsUpdate_req";

        String url = "http://10.77.0.167/profile_details.php";
        String body = new GsonBuilder().create().toJson(requestBody);

        GsonClassRequest<String> gsonRequest = new GsonClassRequest<String>(
                Request.Method.POST, url, body, String.class, new JsonListener<String>(new UiListener<String>() {
            @Override
            public void onResponse(String response) {
                if(serverResponseInterface != null){
                    serverResponseInterface.onServerResult(response);
                }
            }

            @Override
            public void onErrorResponse(int errCode, String errMsg) {
                if(serverResponseInterface != null){
                    serverResponseInterface.onServerError(errMsg);
                }
            }
        }), headers);
        // Adding request to request queue
        VolleyProvider.getInstance(this).addRequest(gsonRequest, profileDetailsUpdateReqTag);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class LocalBinder extends Binder {
        public ProfileDetailsRequestService getService(){
            return ProfileDetailsRequestService.this;
        }
    }
}
