package gr.mc_anastasiou.renthouse.communication.server.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by m.anastasiou on 10/10/2014.
 */
public class VolleyProvider {
    private static VolleyProvider instance;
    private RequestQueue queue;
    private Context ctx;

    private VolleyProvider(Context ctx) {
        ctx = ctx.getApplicationContext();
    }

    public static VolleyProvider getInstance(Context ctx) {
        if (instance == null) {
            instance = new VolleyProvider(ctx);
        }
        return instance;
    }

    public RequestQueue getQueue() {
        if(queue == null){
            queue = Volley.newRequestQueue(ctx);
        }
        return this.queue;
    }

    public <T> Request<T> addRequest(Request<T> req) {
        return getQueue().add(req);
    }

    public <T> Request<T> addRequest(Request<T> req, String tag) {
        req.setTag(tag);
        return getQueue().add(req);
    }
}
