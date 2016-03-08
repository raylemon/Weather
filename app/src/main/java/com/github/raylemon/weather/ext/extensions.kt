package com.github.raylemon.weather.ext

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.util.*

/**
 * Created by big04 on 06-03-16.
 */

fun ImageView.load(url: String) = Picasso.with(this.context).load(url).into(this)

fun Long.toDate() = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault()).format(this * 1000)

fun ViewGroup.inflate(@LayoutRes layout: Int, attachToRoot: Boolean = false) = LayoutInflater.from(this.context).inflate(layout, this, attachToRoot)