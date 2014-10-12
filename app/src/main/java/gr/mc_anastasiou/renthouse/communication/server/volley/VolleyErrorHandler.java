package gr.mc_anastasiou.renthouse.communication.server.volley;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mc on 10/12/2014.
 */
public class VolleyErrorHandler {
    @SerializedName("errCode")
    private int errCode;
    @SerializedName("errMessage")
    private String errMessage;

    public VolleyErrorHandler(){}

    public int getErrCode() {
        return errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }
}
