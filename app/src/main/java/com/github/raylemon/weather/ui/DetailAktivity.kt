package com.github.raylemon.weather.ui

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.github.raylemon.weather.R
import com.github.raylemon.weather.data.domain.Forecast
import org.jetbrains.anko.find

/**
 * Created by big04 on 13-03-16.
 */
class DetailAktivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_aktivity)
        val forecast = intent.getParcelableExtra<Forecast>(DetailFragment.KEY)
        if (savedInstanceState == null ) fragmentManager
                .beginTransaction()
                .replace(R.id.container, DetailFragment().apply {
                    arguments = Bundle().apply { putParcelable(DetailFragment.KEY, forecast) }
                })
                .commit()
    }
}
