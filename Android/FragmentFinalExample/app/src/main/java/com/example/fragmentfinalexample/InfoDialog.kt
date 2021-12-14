package com.example.fragmentfinalexample

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class InfoDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity)
            .setTitle("Info")
            .setMessage("Sample fragment app with Fragment tag, Dialog Fragment, menu and landscape")
            .create()
    }
}//InfoDialog