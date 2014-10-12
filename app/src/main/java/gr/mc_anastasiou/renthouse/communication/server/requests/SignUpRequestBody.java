package gr.mc_anastasiou.renthouse.communication.server.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by m.anastasiou on 10/10/2014.
 */
public class SignUpRequestBody {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("accountType")
    private String accountType;

    public SignUpRequestBody(String email, String password, String accountType){
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }


}
