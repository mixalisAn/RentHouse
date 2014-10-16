package gr.mc_anastasiou.renthouse.core;

/**
 * Created by m.anastasiou on 10/14/2014.
 */
public class Singleton implements GlobalVariables{
    private static Singleton instance = new Singleton();

    private LoginSession loginSession;

    public static Singleton getInstance() {
        return instance;
    }

    private Singleton() {
        loginSession = LoginSession.getInstance();
    }

    public LoginSession getLoginSession(){
        return this.loginSession;
    }
}
