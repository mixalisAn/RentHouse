package gr.mc_anastasiou.renthouse.communication.server.handler;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.android.volley.Request;
import com.google.gson.GsonBuilder;

import gr.mc_anastasiou.renthouse.communication.server.requests.SignUpRequestBody;
import gr.mc_anastasiou.renthouse.communication.server.volley.GsonClassRequest;
import gr.mc_anastasiou.renthouse.communication.server.volley.JsonListener;
import gr.mc_anastasiou.renthouse.communication.server.volley.UiListener;
import gr.mc_anastasiou.renthouse.communication.server.volley.VolleyProvider;

public class SignUpRequestService extends Service {
    private final LocalBinder localBinder = new LocalBinder();
    public OnServerResponseInterface serverResponseInterface;

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }


    public void createAccountRequest(SignUpRequestBody requestBody){
        String  signupReqTag = "signup_req";

        String url = "http://10.77.0.167/signup.php";
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
        }), null);
        // Adding request to request queue
        VolleyProvider.getInstance(this).addRequest(gsonRequest, signupReqTag);
    }

    public class LocalBinder extends Binder {
        public SignUpRequestService getService() {
            return SignUpRequestService.this;
        }
    }
}
