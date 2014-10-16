package gr.mc_anastasiou.renthouse.ui.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import gr.mc_anastasiou.renthouse.MyPropertiesAct;
import gr.mc_anastasiou.renthouse.PropertyFiltersFg;
import gr.mc_anastasiou.renthouse.R;
import gr.mc_anastasiou.renthouse.communication.server.handler.LoginRequestService;
import gr.mc_anastasiou.renthouse.communication.server.handler.OnServerResponseInterface;
import gr.mc_anastasiou.renthouse.communication.server.requests.LoginRequestBody;
import gr.mc_anastasiou.renthouse.communication.server.requests.LoginRequestResult;
import gr.mc_anastasiou.renthouse.core.Singleton;
import gr.mc_anastasiou.renthouse.ui.account.SignUpAct;
import gr.mc_anastasiou.renthouse.ui.dialogs.NotLoggedInDialog;
import gr.mc_anastasiou.renthouse.ui.profile.ProfileAct;


public class HomeScreenAct extends Activity implements ServiceConnection, OnServerResponseInterface{
    private LoginRequestService loginRequestService;

    @Override
    protected void onResume() {
        super.onResume();
        Intent bindIntent = new Intent(this, LoginRequestService.class);
        bindService(bindIntent, this, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(loginRequestService != null){
            unbindService(this);
        }
    }

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
                displayLoginDialog();
                return true;
            case R.id.action_signup:
                Intent intent = new Intent(this, SignUpAct.class);
                startActivity(intent);
                return true;
            case R.id.action_about:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displayLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dg_login, null);
        final EditText email = (EditText) view.findViewById(R.id.dg_login_email);
        final EditText password = (EditText) view.findViewById(R.id.dg_login_password);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (loginRequestService != null) {
                            loginRequestService.makeLoginRequest(new LoginRequestBody(email.getText().toString(), password.getText().toString()));
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    private void displayNotLoggedInDialog(){
        NotLoggedInDialog dialog = new NotLoggedInDialog();
        dialog.show(getFragmentManager(), "notLoggedInDialog");
    }

    public void onButtonPressed(View view) {
        Intent intent;
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
                if(Singleton.getInstance().getLoginSession().isUserLoggedIn()){
                    intent = new Intent(this, MyPropertiesAct.class);
                    startActivity(intent);
                }else{
                    displayNotLoggedInDialog();
                }
                break;
            case R.id.fg_homescreen_profile:
                if(Singleton.getInstance().getLoginSession().isUserLoggedIn()){
                    intent = new Intent(this, ProfileAct.class);
                    startActivity(intent);
                }else{
                    displayNotLoggedInDialog();
                }
                break;
            case R.id.fg_homescreen_exit:
                this.finish();
                break;
        }
    }



    public void onNotLoggedInDgBtnPressed(int id){
        switch (id){
            case DialogInterface.BUTTON_NEUTRAL:
                Intent intent = new Intent(this, SignUpAct.class);
                startActivity(intent);
                break;
            case DialogInterface.BUTTON_POSITIVE:
                displayLoginDialog();
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        loginRequestService = ((LoginRequestService.LocalBinder)service).getService();
        loginRequestService.serverResponseInterface = this;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        loginRequestService.serverResponseInterface = null;
        loginRequestService = null;
    }

    @Override
    public <T> void onServerResult(T tResult) {
        LoginRequestResult result = LoginRequestResult.class.cast(tResult);
        Singleton.getInstance().getLoginSession().setNewSession(result.getProfileDetailsId(),
                result.getAccountId(),result.getAccountType());
        Toast.makeText(this, "Succesfully Logged in: AccountId = " + String.valueOf(result.getAccountId()), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onServerError(String errMessage) {
        Toast.makeText(this, errMessage, Toast.LENGTH_LONG).show();
    }
}
