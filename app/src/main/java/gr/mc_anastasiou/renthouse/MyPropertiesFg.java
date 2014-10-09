package gr.mc_anastasiou.renthouse;



import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class MyPropertiesFg extends Fragment implements View.OnClickListener{
    private MyPropertiesAct mpActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mpActivity = (MyPropertiesAct) activity;
    }

    public MyPropertiesFg() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_my_properties, container, false);
        TextView addProperty = (TextView) view.findViewById(R.id.fg_myProperties_addProperty);

        addProperty.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

        mpActivity.onButtonPressed(view);
    }


}
