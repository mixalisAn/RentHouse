package gr.mc_anastasiou.renthouse.ui.account;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import gr.mc_anastasiou.renthouse.communication.server.handler.SignUpRequestService;
import gr.mc_anastasiou.renthouse.communication.server.requests.SignUpRequestBody;

public class SignUpAct extends Activity implements ServiceConnection, SignUpRequestService.OnServerResponse{
    private SignUpRequestService signUpRequestService;

    @Override
    protected void onResume() {
        super.onResume();
        Intent bindIntent = new Intent(this, SignUpRequestService.class);
        bindService(bindIntent, this, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(signUpRequestService != null){
            unbindService(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SignUpFg suFragment = (SignUpFg) getFragmentManager().findFragmentByTag(SignUpFg.class.getSimpleName());
        if(suFragment == null){
            suFragment = new SignUpFg();
            getFragmentManager().beginTransaction().replace(android.R.id.content, suFragment, SignUpFg.class.getSimpleName()).commit();
        }
    }

    void onSubmitPressed(SignUpRequestBody body){
        if(signUpRequestService != null){
            signUpRequestService.makeSignUpRequest(body);
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        signUpRequestService = ((SignUpRequestService.LocalBinder) service).getService();
        signUpRequestService.serverResponseInterface = this;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        signUpRequestService.serverResponseInterface = null;
        signUpRequestService = null;
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
