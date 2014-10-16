package gr.mc_anastasiou.renthouse.core;

/**
 * Created by m.anastasiou on 10/13/2014.
 */
public class LoginSession {
    private static LoginSession loginSession = new LoginSession();
    private boolean userLoggedIn = false;
    private int profileDetailsId, accountId;
    private String accountType;

    static LoginSession getInstance(){
        return loginSession;
    }

    private LoginSession(){

    }

    public void setNewSession(int profileDetailsId, int accountId, String accountType){
        this.profileDetailsId = profileDetailsId;
        this.accountId = accountId;
        this.accountType = accountType;
        this.userLoggedIn = true;
    }

    public void closeLogginSession(){
        this.userLoggedIn = false;
        this.profileDetailsId = 0;
        this.accountId = 0;
        this.accountType = null;
    }

    public boolean isUserLoggedIn() {
        return userLoggedIn;
    }

    public int getProfileDetailsId() {
        return profileDetailsId;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getAccountType() {
        return accountType;
    }
}
