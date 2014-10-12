package gr.mc_anastasiou.renthouse.communication.server.volley;

/**
 * Created by mc on 10/12/2014.
 */
public interface UiListener<R> {
    public void onResponse(R response);
    public void onErrorResponse(int errCode, String errMsg);
}