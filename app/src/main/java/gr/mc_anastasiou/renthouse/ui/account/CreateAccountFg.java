package gr.mc_anastasiou.renthouse.ui.account;



import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import gr.mc_anastasiou.renthouse.R;
import gr.mc_anastasiou.renthouse.communication.server.requests.SignUpRequest;
import gr.mc_anastasiou.renthouse.ui.CommonDialog;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class CreateAccountFg extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    private EditText email, password, passwordRetype;
    private String selectedAccountType;
    private Spinner accountType;
    private AccountAct aActivity;

    public CreateAccountFg() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        aActivity = (AccountAct)activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        aActivity = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_create_account, container, false);
        email = (EditText) view.findViewById(R.id.fg_createAccount_email);
        password = (EditText) view.findViewById(R.id.fg_createAccount_password);
        passwordRetype = (EditText) view.findViewById(R.id.fg_createAccount_passwordRetype);
        accountType = (Spinner) view.findViewById(R.id.fg_createAccount_accountType);
        TextView submit = (TextView) view.findViewById(R.id.fg_createAccount_submit);

        //set view's listeners
        submit.setOnClickListener(this);
        accountType.setOnItemSelectedListener(this);

        //Create spinner adapter with string-array resources
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.fg_createAccount_type, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        accountType.setAdapter(adapter);

        return view;
    }

    /*
    Listeners implementations
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedAccountType = (String)parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View view) {
        if(!TextUtils.isEmpty(email.getText())
                && !TextUtils.isEmpty(password.getText())
                && !TextUtils.isEmpty(passwordRetype.getText())){
            if(password.equals(passwordRetype)){
                aActivity.onSubmitPressed(new SignUpRequest(email.getText().toString(), password.getText().toString(), selectedAccountType));
            }else{
                displayErrorDialog(getActivity().getString(R.string.fg_createAccount_notEqualPasswords));
            }
        }else{
            displayErrorDialog(getActivity().getString(R.string.fg_createAccount_emptyfields));
        }
    }

    private void displayErrorDialog(String message){
        CommonDialog dialog = CommonDialog.newInstance(message);
        dialog.setCancelable(false);
        dialog.show(getFragmentManager(), "errorDialog");
    }
}
