package gr.mc_anastasiou.renthouse.ui.account;



import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import gr.mc_anastasiou.renthouse.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class AccountFg extends Fragment implements View.OnClickListener {
    private AccountAct clActivity;

    public AccountFg() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        clActivity = (AccountAct) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clActivity = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_account, container, false);
        TextView create = (TextView) view.findViewById(R.id.fg_account_create);
        TextView login = (TextView) view.findViewById(R.id.fg_account_login);

        create.setOnClickListener(this);
        login.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        if (clActivity != null) {
            clActivity.onButtonPressed(view);
        }
    }
}
