package gr.mc_anastasiou.renthouse.core;

/**
 * Created by m.anastasiou on 10/13/2014.
 */
public class Singleton {
    private static Singleton instance;
    private boolean userLoggedIn = false;
    private int profileDetailsId, accountId;

    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }

    public void setProfileDetailsId(int id){
        userLoggedIn = true;
        this.profileDetailsId = id;
    }

    public int getProfileDetailsId(){
        return profileDetailsId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAccountId() {
        return accountId;
    }
}
