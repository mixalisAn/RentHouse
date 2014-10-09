package gr.mc_anastasiou.renthouse.ui.account;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import gr.mc_anastasiou.renthouse.R;

public class AccountAct extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AccountFg clFragment = (AccountFg) getFragmentManager().findFragmentByTag(AccountFg.class.getSimpleName());
        if(clFragment == null){
            clFragment = new AccountFg();
            getFragmentManager().beginTransaction().replace(android.R.id.content, clFragment, AccountFg.class.getSimpleName()).commit();
        }
    }

    void onButtonPressed(View view){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        switch (view.getId()){
            case R.id.fg_createLogin_create:
                ft.replace(android.R.id.content, new CreateAccountFg()).commit();
                break;
            case R.id.fg_createLogin_login:
                break;
        }
    }
}
