package gr.mc_anastasiou.renthouse;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeScreenFg extends Fragment implements View.OnClickListener{
    private HomeScreenAct hsActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        hsActivity = (HomeScreenAct) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_homescreen, container, false);
        Button profileButton = (Button) view.findViewById(R.id.fg_homescreen_profile);


        profileButton.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        hsActivity.onButtonPressed(view);
    }


}