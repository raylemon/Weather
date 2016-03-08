package com.github.raylemon.weather.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.raylemon.weather.R
import com.github.raylemon.weather.data.JsonServer
import com.github.raylemon.weather.ui.adapter.ForecastAdapter
import kotlinx.android.synthetic.main.forecast_list.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

/**
 * Created by big04 on 06-03-16.
 */
class MainAktivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    private val cnt = 16
    private val city = "Wavre"

    override fun onResume() {
        super.onResume()
        async() {
            val items = JsonServer().getForecast(cnt, city)
            uiThread {
                vForecastList.adapter = ForecastAdapter(items)
            }
        }
    }
}