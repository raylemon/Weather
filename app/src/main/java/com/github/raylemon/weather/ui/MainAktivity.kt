package com.github.raylemon.weather.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import com.github.raylemon.weather.R
import com.github.raylemon.weather.data.JsonServer
import com.github.raylemon.weather.ext.toDate
import com.github.raylemon.weather.ui.adapter.ForecastAdapter
import kotlinx.android.synthetic.main.forecast_list.*
import kotlinx.android.synthetic.main.main_activity.*
import org.jetbrains.anko.async
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

/**
 * Created by big04 on 06-03-16.
 */
class MainAktivity : AppCompatActivity() {

    private val prefs: SharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(this) }
    private val cnt: Int by lazy { prefs.getInt(PreferenceDialog.COUNT_KEY, 16) }
    private val city: String by lazy { prefs.getString(PreferenceDialog.CITY_KEY, "Wavre") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        fab.setOnClickListener { showPreferences() }
    }

    override fun onResume() {
        super.onResume()
        async() {
            val items = JsonServer().getForecast(cnt, city)
            uiThread {
                vForecastList.adapter = ForecastAdapter(items) {
                    toast(it.dt.toDate())
                }
                vCollapseToolbar.title = "${items.city},${items.country}"
            }
        }
    }

    private fun showPreferences() {
        PreferenceDialog().show(supportFragmentManager, "preferences")
    }
}