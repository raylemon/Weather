package com.github.raylemon.weather.ui

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
import org.jetbrains.anko.startActivity

/**
 * Created by big04 on 06-03-16.
 */
class MainAktivity : AppCompatActivity() {

    private val prefs by lazy { PreferenceManager.getDefaultSharedPreferences(this) }
    private val city by lazy { prefs.getString(PreferencesDialog.CITY_KEY, "Brussels") }
    private val cnt by lazy { prefs.getInt(PreferencesDialog.CNT_KEY, 7) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        fab.setOnClickListener {
            PreferencesDialog().show(supportFragmentManager, "prefs")
        }
    }

    override fun onResume() {
        super.onResume()
        async() {
            val items = JsonServer().getForecast(cnt, city)
            uiThread {
                vForecastList.adapter = ForecastAdapter(items) { forecast ->
                    if (!resources.getBoolean(R.bool.twoPane)) startActivity<DetailAktivity>(DetailFragment.KEY to forecast, DetailFragment.CITY to city)
                        else
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.container, DetailFragment().apply {
                                    arguments = Bundle().apply { putParcelable(DetailFragment.KEY, forecast) }
                                })
                                .commit()
                }
            }
        }
    }
}