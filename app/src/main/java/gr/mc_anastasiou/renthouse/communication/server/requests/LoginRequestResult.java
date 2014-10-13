package gr.mc_anastasiou.renthouse.communication.server.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by m.anastasiou on 10/13/2014.
 */
public class LoginRequestResult {
    @SerializedName("accountId")
    private int accountId;
    @SerializedName("profileDetailsId")
    private int profileDetailsId;

    public LoginRequestResult(){

    }

    public int getAccountId() {
        return accountId;
    }

    public int getProfileDetailsId() {
        return profileDetailsId;
    }
}
