package gr.mc_anastasiou.renthouse.communication.server.volley;

import com.android.volley.Cache.Entry;
import com.android.volley.Response;
import com.google.gson.GsonBuilder;

import java.util.Map;


/**
 * Gson request for single objects.
 * @param <T> T the type of the object to return.
 */
public class GsonClassRequest<T> extends GsonRequest<T> {
	private Class<T> mResponseClass;
    

    public GsonClassRequest(int method, String url, String requestBody, Class<T> responseClass, 
    		JsonListener<T> listener, Map<String, String> headers) {
        super(method, url, requestBody, listener, listener, headers);
        mResponseClass = responseClass;        
    }
    
	@Override
	protected Response<T> parseJsonResponse(String jsonString,
			Entry cacheHeaders) {
		return Response.success(
        		new GsonBuilder().create().fromJson(
        				jsonString, mResponseClass), cacheHeaders);
	}
	
	
}
