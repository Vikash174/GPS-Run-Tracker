package bawv.app.runtracker.ui.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import bawv.app.runtracker.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CancelTrackingDialog :DialogFragment() {

    private var yesListener:(() ->Unit)? = null

    fun setYesListener(listener:() ->Unit){
        yesListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
     return  MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialog_AppCompat)
            .setTitle("Cancel the run?")
            .setMessage("Are you sure to cancel the current run and delete all it's data?")
            .setIcon(R.drawable.ic_delete)
            .setPositiveButton("Yes") { _, _ ->
                yesListener?.let {yes->
                yes()

                }
            }

            .setNegativeButton("NO") { dialogInterface, _ ->
                dialogInterface.cancel()

            }
            .create()

    }
}