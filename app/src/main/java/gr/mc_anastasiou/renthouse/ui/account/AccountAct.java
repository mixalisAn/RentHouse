package gr.mc_anastasiou.renthouse.ui.account;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import gr.mc_anastasiou.renthouse.R;
import gr.mc_anastasiou.renthouse.communication.server.handler.RequestHandlerService;
import gr.mc_anastasiou.renthouse.communication.server.requests.SignUpRequestBody;

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

    void onSubmitPressed(SignUpRequestBody body){
        if(requestHandlerService != null){
            requestHandlerService.makeSignUpRequest(body);
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        requestHandlerService = ((RequestHandlerService.LocalBinder) service).getService();
        requestHandlerService.serverResponseInterface = this;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        requestHandlerService.serverResponseInterface = null;
        requestHandlerService = null;
    }

    @Override
    public void onServerResult(String response) {
        Toast.makeText(this, response, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onServerError(String errMsg) {
        Toast.makeText(this, errMsg, Toast.LENGTH_LONG).show();
    }
}
