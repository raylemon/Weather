package com.github.raylemon.weather.ui.toolbar

import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.github.raylemon.weather.ext.slideEnter
import com.github.raylemon.weather.ext.slideExit

/**
 * Created by big04 on 10-03-16.
 */
interface ToolbarManager {
    val toolbar: Toolbar
    val collapseBar: CollapsingToolbarLayout
    var toolbarTitle: String
        get() = collapseBar.title.toString()
        set(value) {
            collapseBar.title = value
        }

    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    private fun createUpDrawable() = with(DrawerArrowDrawable(toolbar.context)) {
        progress = 1f
        this
    }

    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }
}