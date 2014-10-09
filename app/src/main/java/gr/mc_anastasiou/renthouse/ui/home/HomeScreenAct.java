package gr.mc_anastasiou.renthouse.ui.home;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import gr.mc_anastasiou.renthouse.MyPropertiesAct;
import gr.mc_anastasiou.renthouse.ProfileFg;
import gr.mc_anastasiou.renthouse.PropertyFiltersFg;
import gr.mc_anastasiou.renthouse.R;
import gr.mc_anastasiou.renthouse.ui.account.AccountAct;


public class HomeScreenAct extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomeScreenFg hsFragment = (HomeScreenFg) this.getFragmentManager().findFragmentByTag(HomeScreenFg.class.getSimpleName());
        if (hsFragment == null) {
            hsFragment = new HomeScreenFg();
            this.getFragmentManager().beginTransaction().replace(android.R.id.content, hsFragment, HomeScreenFg.class.getSimpleName()).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.act_homescreen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_login:
                Intent intent = new Intent(this, AccountAct.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onButtonPressed(View view) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.fg_homescreen_buy:
            case R.id.fg_homescreen_sell:
                PropertyFiltersFg pfFragment = (PropertyFiltersFg) getFragmentManager().findFragmentByTag(PropertyFiltersFg.class.getSimpleName());
                if (pfFragment == null) {
                    pfFragment = new PropertyFiltersFg();
                }
                ft.replace(android.R.id.content, pfFragment, PropertyFiltersFg.class.getSimpleName()).commit();
                break;
            case R.id.fg_homescreen_myProperties:
                Intent intent = new Intent(this, MyPropertiesAct.class);
                startActivity(intent);
                break;
            case R.id.fg_homescreen_profile:
                ProfileFg pFragment = (ProfileFg) getFragmentManager().findFragmentByTag(ProfileFg.class.getSimpleName());
                if (pFragment == null) {
                    pFragment = new ProfileFg();
                }
                ft.replace(android.R.id.content, pFragment, ProfileFg.class.getSimpleName()).commit();
                break;
        }
    }
}