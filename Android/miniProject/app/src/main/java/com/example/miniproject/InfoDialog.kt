package com.example.miniproject

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class InfoDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity)
            .setTitle("Info")
            .setMessage("Mini Project 1 - Campbell Schweickhardt")
            .create()
    }
}//InfoDialog