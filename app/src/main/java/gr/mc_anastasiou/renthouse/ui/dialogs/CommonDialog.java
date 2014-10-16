package gr.mc_anastasiou.renthouse.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import gr.mc_anastasiou.renthouse.R;

/**
 * Created by m.anastasiou on 10/10/2014.
 */
public class CommonDialog extends DialogFragment{
    private final static String MESSAGE_TAG = "message";

    public static CommonDialog newInstance(String message){
        CommonDialog dialog = new CommonDialog();

        Bundle args =  new Bundle();
        args.putString(MESSAGE_TAG, message);
        dialog.setArguments(args);

        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(this.getArguments().getString(MESSAGE_TAG))
                .setPositiveButton(R.string.common_dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
