package gr.mc_anastasiou.renthouse;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ProfileFg extends Fragment implements View.OnTouchListener{


    public ProfileFg() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_profile, container, false);
        EditText aboutEditText = (EditText) view.findViewById(R.id.fg_profile_aboutEditText);

        aboutEditText.setOnTouchListener(this);

        return view;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if(view.getId() == R.id.fg_profile_aboutEditText){
            view.getParent().requestDisallowInterceptTouchEvent(true);
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_UP:
                    view.getParent().requestDisallowInterceptTouchEvent(false);
                    break;
            }
        }
        return false;
    }
}
