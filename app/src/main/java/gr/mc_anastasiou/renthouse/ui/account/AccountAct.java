package gr.mc_anastasiou.renthouse.ui.account;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import gr.mc_anastasiou.renthouse.R;
import gr.mc_anastasiou.renthouse.communication.server.handler.RequestHandlerService;

public class AccountAct extends Activity implements ServiceConnection, RequestHandlerService.OnServerResponse{
    private RequestHandlerService requestHandlerService;

    @Override
    protected void onResume() {
        super.onResume();
        Intent bindIntent = new Intent(this, RequestHandlerService.class);
        bindService(bindIntent, this, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(requestHandlerService != null){
            unbindService(this);
        }
    }

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
            case R.id.fg_account_create:
                ft.replace(android.R.id.content, new CreateAccountFg()).commit();
                break;
            case R.id.fg_account_login:
                break;
        }
    }

    <T> void onSubmitPressed(T requestBody){
        if(requestHandlerService != null){
            requestHandlerService.makeServerRequest(requestBody);
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        requestHandlerService = ((RequestHandlerService.LocalBinder) service).getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        requestHandlerService = null;
    }

    @Override
    public void onServerResult() {

    }

    @Override
    public void onServerError() {

    }
}
