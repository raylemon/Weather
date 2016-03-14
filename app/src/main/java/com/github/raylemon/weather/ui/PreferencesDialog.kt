package com.github.raylemon.weather.ui

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatDialogFragment
import android.view.LayoutInflater
import android.widget.NumberPicker
import com.github.raylemon.weather.R
import org.jetbrains.anko.AlertDialogBuilder
import org.jetbrains.anko.find

/**
 * Created by christophe on 14/03/16.
 */
class PreferencesDialog() : AppCompatDialogFragment() {
    companion object {
        const val CITY_KEY = "City"
        const val CNT_KEY = "cnt"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(activity).inflate(R.layout.pref_dialog, null)
        //TODO
        val pick = view.find<NumberPicker>(R.id.vDayPicker).apply {
            minValue = 1
            maxValue = 16
            value = 7
        }
        return AlertDialogBuilder(activity).apply {
            title("Preferences")
            customView(view)
            positiveButton("Set !")
            {
                //TODO
            }
            negativeButton("Cancel") { cancel() }
        }.builder.create()
    }
}