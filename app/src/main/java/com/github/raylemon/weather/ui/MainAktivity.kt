package com.github.raylemon.weather.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.raylemon.weather.R
import com.github.raylemon.weather.ui.adapter.ForecastAdapter
import kotlinx.android.synthetic.main.forecast_list.*

/**
 * Created by big04 on 06-03-16.
 */
class MainAktivity : AppCompatActivity() {

    val items = (1..16).map { "item n° $it" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun onResume() {
        super.onResume()
        vForecastList.adapter = ForecastAdapter(items)
    }
}