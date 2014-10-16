package gr.mc_anastasiou.renthouse.ui.profile;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import gr.mc_anastasiou.renthouse.communication.server.handler.OnServerResponseInterface;
import gr.mc_anastasiou.renthouse.communication.server.handler.ProfileDetailsRequestService;
import gr.mc_anastasiou.renthouse.communication.server.requests.ProfileRequestBody;
import gr.mc_anastasiou.renthouse.communication.server.requests.ProfileRequestResult;

public class ProfileAct extends Activity implements ServiceConnection, OnServerResponseInterface{
    private ProfileDetailsRequestService profileDetailsRequestService;

    @Override
    protected void onResume() {
        super.onResume();
        Intent bindIntent = new Intent(this, ProfileDetailsRequestService.class);
        bindService(bindIntent, this, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(profileDetailsRequestService != null){
            unbindService(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProfileFg pFragment = (ProfileFg) getFragmentManager().findFragmentByTag(ProfileFg.class.getSimpleName());
        if(pFragment == null){
            pFragment = new ProfileFg();
        }
        getFragmentManager().beginTransaction().replace(android.R.id.content, pFragment, ProfileFg.class.getSimpleName()).commit();

    }

    void onUpdatePressed(ProfileRequestBody requestBody){
        profileDetailsRequestService.updateProfileDetailsRequest(requestBody);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        profileDetailsRequestService = ((ProfileDetailsRequestService.LocalBinder) service).getService();
        profileDetailsRequestService.serverResponseInterface = this;

        profileDetailsRequestService.getProfileDetailsData();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        profileDetailsRequestService.serverResponseInterface = null;
        profileDetailsRequestService = null;
    }

    @Override
    public <T> void onServerResult(T tResult) {
        if(tResult.getClass().isAssignableFrom(String.class)){
            Toast.makeText(this, String.class.cast(tResult), Toast.LENGTH_LONG).show();
        }else if(tResult.getClass().isAssignableFrom(ProfileRequestResult.class)) {
            ProfileRequestResult result = ProfileRequestResult.class.cast(tResult);
            ProfileFg pFragment = (ProfileFg) getFragmentManager().findFragmentByTag(ProfileFg.class.getSimpleName());
            if (pFragment != null) {
                pFragment.populateLayoutData(result);
            }
        }
    }

    @Override
    public void onServerError(String errMessage) {
        Toast.makeText(this, errMessage, Toast.LENGTH_LONG).show();
    }
}
