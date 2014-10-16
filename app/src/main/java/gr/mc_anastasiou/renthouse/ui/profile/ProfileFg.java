package gr.mc_anastasiou.renthouse.ui.profile;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import gr.mc_anastasiou.renthouse.R;
import gr.mc_anastasiou.renthouse.communication.server.requests.ProfileRequestBody;
import gr.mc_anastasiou.renthouse.communication.server.requests.ProfileRequestResult;
import gr.mc_anastasiou.renthouse.core.Singleton;

public class ProfileFg extends Fragment implements View.OnTouchListener{
    private ImageView profileImg;
    private EditText name, lastName, email, phone, mobile;
    private RadioButton maleRButton, femaleRButton;
    private ProfileAct pActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        pActivity = (ProfileAct)activity;
    }

    public ProfileFg() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_profile, container, false);
        name = (EditText) view.findViewById(R.id.fg_profile_name);
        lastName = (EditText) view.findViewById(R.id.fg_profile_lastName);
        maleRButton = (RadioButton) view.findViewById(R.id.fg_profile_male);
        femaleRButton = (RadioButton) view.findViewById(R.id.fg_profile_female);
        email = (EditText) view.findViewById(R.id.fg_profile_email);
        phone = (EditText) view.findViewById(R.id.fg_profile_phone);
        mobile = (EditText) view.findViewById(R.id.fg_profile_mobile);

        TextView updateButton = (TextView) view.findViewById(R.id.fg_profile_updateButton);
        EditText aboutEditText = (EditText) view.findViewById(R.id.fg_profile_aboutEditText);
        LinearLayout agentDetails = (LinearLayout) view.findViewById(R.id.fg_profile_agentDetails);


        if(Singleton.getInstance().getLoginSession().isUserLoggedIn()
            &&Singleton.getInstance().getLoginSession().getAccountType().equals(Singleton.AGENT)){
            agentDetails.setVisibility(View.VISIBLE);
        }
        aboutEditText.setOnTouchListener(this);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gender;
                if(maleRButton.isChecked()){
                    gender = Singleton.MALE;
                }else{
                    gender = Singleton.FEMALE;
                }

                pActivity.onUpdatePressed(new ProfileRequestBody(Singleton.getInstance().getLoginSession().getProfileDetailsId(),
                        name.getText().toString(), lastName.getText().toString(), email.getText().toString(), gender,
                        null,phone.getText().toString(), mobile.getText().toString(), null));
            }
        });
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


    void populateLayoutData(ProfileRequestResult result){
        name.setText(result.getFirstName());
        lastName.setText(result.getLastName());
        if(!TextUtils.isEmpty(result.getGender())){
            if(result.getGender().equals(Singleton.MALE)){
                maleRButton.setChecked(true);
            }else{
                femaleRButton.setChecked(true);
            }
        }
        email.setText(result.getEmail());
        phone.setText(result.getPhone());
        mobile.setText(result.getMobile());
    }
}
