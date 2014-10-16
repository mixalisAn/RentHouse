package gr.mc_anastasiou.renthouse.communication.server.handler;

/**
 * Created by m.anastasiou on 10/15/2014.
 */
public interface OnServerResponseInterface {
    public <T> void onServerResult(T tResult);
    public void onServerError(String errMessage);
}
