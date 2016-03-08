package com.github.raylemon.weather.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.github.raylemon.weather.R
import com.github.raylemon.weather.data.domain.Forecast
import com.github.raylemon.weather.data.domain.ForecastList
import com.github.raylemon.weather.ext.inflate
import com.github.raylemon.weather.ext.load
import com.github.raylemon.weather.ext.toDate
import kotlinx.android.synthetic.main.item_list_content.view.*

/**
 * Created by big04 on 06-03-16.
 */
class ForecastAdapter(val items: ForecastList, val click: (Forecast) -> Unit) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.item_list_content, false), click)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindForecast(items[position])

    inner class ViewHolder(view: View, val click: (Forecast) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindForecast(forecast: Forecast) = with(forecast) {
            with(itemView) {
                vDate.text = dt.toDate()
                vDesc.text = desc
                vMaxTemp.text = temp.max.toString()
                vMinTemp.text = temp.min.toString()
                vIcon.load(icon)
                setOnClickListener { click(forecast) }
            }
        }
    }
}