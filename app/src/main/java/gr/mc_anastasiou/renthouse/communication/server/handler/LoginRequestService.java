package gr.mc_anastasiou.renthouse.communication.server.handler;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.android.volley.Request;
import com.google.gson.GsonBuilder;

import gr.mc_anastasiou.renthouse.communication.server.requests.LoginRequestBody;
import gr.mc_anastasiou.renthouse.communication.server.requests.LoginRequestResult;
import gr.mc_anastasiou.renthouse.communication.server.volley.GsonClassRequest;
import gr.mc_anastasiou.renthouse.communication.server.volley.JsonListener;
import gr.mc_anastasiou.renthouse.communication.server.volley.UiListener;
import gr.mc_anastasiou.renthouse.communication.server.volley.VolleyProvider;

public class LoginRequestService extends Service {
    private final LocalBinder localBinder = new LocalBinder();
    public OnServerResponse serverResponseInterface;

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }


    public void makeLoginRequest(LoginRequestBody requestBody){
        String  loginReqTag = "login_req";

        String url = "http://10.77.0.167/login.php";
        String body = new GsonBuilder().create().toJson(requestBody);

        GsonClassRequest<LoginRequestResult> gsonRequest = new GsonClassRequest<LoginRequestResult>(
                Request.Method.POST, url, body, LoginRequestResult.class, new JsonListener<LoginRequestResult>(new UiListener<LoginRequestResult>() {
            @Override
            public void onResponse(LoginRequestResult response) {
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
        }));
        // Adding request to request queue
        VolleyProvider.getInstance(this).addRequest(gsonRequest, loginReqTag);
    }

    public class LocalBinder extends Binder {
        public LoginRequestService getService(){
            return LoginRequestService.this;
        }
    }

    public interface OnServerResponse{
        public void onServerResult(LoginRequestResult result);
        public void onServerError(String errMessage);
    }
}
