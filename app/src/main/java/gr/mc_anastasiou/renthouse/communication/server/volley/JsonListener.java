package gr.mc_anastasiou.renthouse.communication.server.volley;

import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

/**
 * Created by mc on 10/12/2014.
 */
public class JsonListener<T> implements Response.Listener<T>, Response.ErrorListener {
    private UiListener<T> uiListener;
    private Gson gson = new Gson();

    public JsonListener(UiListener<T> uiListener) {
        this.uiListener = uiListener;
    }

    @Override
    public void onResponse(T response) {
        uiListener.onResponse(response);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        NetworkResponse response = error.networkResponse;

        int errCode = 0;
        String errMessage = "default";

        if (response == null) {
            //TODO type general error message
        } else {
            if (response.data != null) {
                try {
                    VolleyErrorHandler errHandler = gson.fromJson(new String(response.data), VolleyErrorHandler.class);
                    errCode = errHandler.getErrCode();
                    errMessage = errHandler.getErrMessage();
                } catch (Exception e) {
                    //TODO type general error message
                }
            } else if (error instanceof NetworkError) {
                //TODO network error message
            } else if (error instanceof NoConnectionError) {
                //TODO type no connection error message
            } else if (error instanceof TimeoutError) {
                //TODO type timeout error message
            }
        }
        uiListener.onErrorResponse(errCode, errMessage);
    }
}
