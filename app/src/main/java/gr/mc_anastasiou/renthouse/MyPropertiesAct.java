package gr.mc_anastasiou.renthouse;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MyPropertiesAct extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyPropertiesFg mpFragment = (MyPropertiesFg) getFragmentManager().findFragmentByTag(MyPropertiesFg.class.getSimpleName());
        if(mpFragment == null){
            mpFragment = new MyPropertiesFg();
        }
        getFragmentManager().beginTransaction().replace(android.R.id.content, mpFragment, MyPropertiesFg.class.getSimpleName()).commit();
    }

    public void onButtonPressed(View view) {
        switch (view.getId()){
            case R.id.fg_myProperties_addProperty:

                break;
        }
    }
}
