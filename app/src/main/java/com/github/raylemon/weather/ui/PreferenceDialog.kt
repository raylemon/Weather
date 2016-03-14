package com.github.raylemon.weather.ui

import android.app.Dialog
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatDialogFragment
import android.widget.EditText
import android.widget.NumberPicker
import com.github.raylemon.weather.R
import org.jetbrains.anko.AlertDialogBuilder
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

/**
 * Created by big04 on 06-03-16.
 */
class PreferenceDialog : AppCompatDialogFragment() {

    companion object {
        val CITY_KEY = "city"
        val COUNT_KEY = "cnt"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = activity.layoutInflater.inflate(R.layout.pref_dialog, null)
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val vCntPick = view.find<NumberPicker>(R.id.vCountPicker).apply {
            minValue = 1
            maxValue = 16
            value = prefs.getInt(COUNT_KEY, 7)
        }
        val vCity = view.find<EditText>(R.id.vPrefCity).apply { setText(prefs.getString(CITY_KEY, "")) }

        return AlertDialogBuilder(context).apply {
            customView(view)
            title(resources.getString(R.string.pref_title))
            positiveButton(resources.getString(R.string.pref_done)) {
                with(prefs.edit()) {
                    putString(CITY_KEY, vCity.text.toString())
                    putInt(COUNT_KEY, vCntPick.value)
                    commit()
                    activity.toast(resources.getString(R.string.pref_toast))
                }
            }
            negativeButton(resources.getString(R.string.pref_cancel)) { cancel() }
        }.builder.create()
    }
}
