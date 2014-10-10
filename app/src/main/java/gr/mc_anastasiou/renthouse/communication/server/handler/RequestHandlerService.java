package gr.mc_anastasiou.renthouse.communication.server.handler;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class RequestHandlerService extends Service {
    private final LocalBinder localBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public <T> void makeServerRequest(T requestBody){

    }

    public class LocalBinder extends Binder {
        public RequestHandlerService getService(){
            return RequestHandlerService.this;
        }
    }

    public interface OnServerResponse{
        public void onServerResult();
        public void onServerError();
    }
}
