package gr.mc_anastasiou.renthouse.communication.server.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by m.anastasiou on 10/15/2014.
 */
public class ProfileRequestBody {
    @SerializedName("profileId")
    private int profileId;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("email")
    private String email;
    @SerializedName("gender")
    private String gender;
    @SerializedName("phone")
    private String phone;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("password")
    private String password;
    @SerializedName("photo_path")
    private String photo_path;

    public ProfileRequestBody(int profileId, String firstName, String lastName, String email, String gender, String phone, String mobile, String password, String photo_path) {
        this.profileId = profileId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.mobile = mobile;
        this.password = password;
        this.photo_path = photo_path;
    }
}
