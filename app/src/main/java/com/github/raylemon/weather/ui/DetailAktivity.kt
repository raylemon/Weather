package com.github.raylemon.weather.ui

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.github.raylemon.weather.R
import com.github.raylemon.weather.ui.toolbar.ToolbarManager
import org.jetbrains.anko.find

/**
 * Created by big04 on 13-03-16.
 */
class DetailAktivity : AppCompatActivity(), ToolbarManager {
    override val toolbar by lazy { find<Toolbar>(R.id.vToolbar) }
    override val collapseBar by lazy { find<CollapsingToolbarLayout>(R.id.vCollapseToolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        toolbarTitle = "Any Title" //TODO change title
        enableHomeAsUp { onBackPressed() }
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //TODO add forecast
        //TODO fragment
    }
}