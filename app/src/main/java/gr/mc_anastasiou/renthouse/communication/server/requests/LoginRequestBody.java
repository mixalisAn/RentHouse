package gr.mc_anastasiou.renthouse.communication.server.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by m.anastasiou on 10/13/2014.
 */
public class LoginRequestBody {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public LoginRequestBody(String email, String password){
        this.email = email;
        this.password = password;
    }
}
