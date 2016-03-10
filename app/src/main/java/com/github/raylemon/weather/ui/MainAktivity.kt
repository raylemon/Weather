package com.github.raylemon.weather.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.github.raylemon.weather.R
import com.github.raylemon.weather.data.JsonServer
import com.github.raylemon.weather.ext.toDate
import com.github.raylemon.weather.ui.adapter.ForecastAdapter
import com.github.raylemon.weather.ui.toolbar.ToolbarManager
import kotlinx.android.synthetic.main.forecast_list.*
import kotlinx.android.synthetic.main.main_activity.*
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

/**
 * Created by big04 on 06-03-16.
 */
class MainAktivity : AppCompatActivity(), ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.vToolbar) }
    override val collapseBar by lazy { find<CollapsingToolbarLayout>(R.id.vCollapseToolbar) }
    private val prefs: SharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(this) }
    private val cnt: Int by lazy { prefs.getInt(PreferenceDialog.COUNT_KEY, 16) }
    private val city: String by lazy { prefs.getString(PreferenceDialog.CITY_KEY, "Wavre") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        fab.setOnClickListener { showPreferences() }
        attachToScroll(vForecastList)
    }

    override fun onResume() {
        super.onResume()
        async() {
            val items = JsonServer().getForecast(cnt, city)
            uiThread {
                vForecastList.adapter = ForecastAdapter(items) {
                    toast(it.dt.toDate())
                }
                toolbarTitle = "${items.city},${items.country}"
            }
        }
    }

    private fun showPreferences() {
        PreferenceDialog().show(supportFragmentManager, "preferences")
    }
}