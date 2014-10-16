package gr.mc_anastasiou.renthouse.communication.server.volley;

import com.android.volley.Cache.Entry;
import com.android.volley.Response;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Gson request for object lists.
 *
 * @param <T> the type of list elements.
 */
public class GsonListRequest<T> extends GsonRequest<T> {
	private Type mType;
	
	public GsonListRequest(int method, String url, String requestBody, Type type, JsonListener<T> listener, Map<String, String> headers) {
        super(method, url, requestBody, listener, listener, headers);
        mType = type;
    }
	

	@SuppressWarnings("unchecked")
	@Override
	protected Response<T> parseJsonResponse(String jsonString, Entry cacheHeaders) {
		return (Response<T>) Response.success(new GsonBuilder().create().fromJson(jsonString, mType),
                cacheHeaders);
	}

}
