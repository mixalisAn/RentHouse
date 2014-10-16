package gr.mc_anastasiou.renthouse.ui.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import gr.mc_anastasiou.renthouse.ui.home.HomeScreenAct;

/**
 * Created by m.anastasiou on 10/15/2014.
 */
public class NotLoggedInDialog extends DialogFragment {
    private HomeScreenAct hsActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        hsActivity = (HomeScreenAct) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Not Logged in");
        builder.setMessage("Login in or signup for extra features.")
                .setPositiveButton("Log in", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        hsActivity.onNotLoggedInDgBtnPressed(id);
                    }
                })
        .setNeutralButton("Sign up", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                hsActivity.onNotLoggedInDgBtnPressed(id);
            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
