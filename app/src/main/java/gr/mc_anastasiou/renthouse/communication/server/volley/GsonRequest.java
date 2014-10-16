package gr.mc_anastasiou.renthouse.communication.server.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mc on 10/12/2014.
 */
abstract class GsonRequest<T> extends JsonRequest<T> {
    private final Gson gson = new Gson();
    private Map<String, String> headers;

    public GsonRequest(int method, String url, String requestBody, Response.Listener<T> listener, Response.ErrorListener errorListener, Map<String, String> headers) {
        super(method, url, requestBody, listener, errorListener);
        if(headers == null){
            this.headers = new HashMap<String, String>();
        }else{
            this.headers = headers;
        }
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String jsonString = new String(networkResponse.data,
                    HttpHeaderParser.parseCharset(networkResponse.headers));
            return parseJsonResponse(jsonString, HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return com.android.volley.Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return com.android.volley.Response.error(new ParseError(e));
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers;
    }

    protected abstract Response<T> parseJsonResponse(String jsonString, Cache.Entry cacheHeaders);
}
