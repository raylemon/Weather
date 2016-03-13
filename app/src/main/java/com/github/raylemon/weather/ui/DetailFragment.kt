package com.github.raylemon.weather.ui

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.raylemon.weather.data.domain.Forecast
import com.github.raylemon.weather.ext.load
import com.github.raylemon.weather.ext.toDate
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {
    lateinit var forecast: Forecast
        private set

    companion object {
        const val KEY = "forecast"
        const val CITY = "city"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null && arguments.containsKey(KEY)) forecast = arguments.getParcelable(KEY)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View
            = inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(forecast) {
            vDate.text = dt.toDate()
            vPressure.text = pressure.toString() + "hPa"
            vHumidity.text = humidity.toString() + "%"
            vIcon.load(icon)
            vDescription.text = desc
            with(temp) {
                vDay.text = "${day.toString()}°C" ; vDay.setBackgroundColor(defineColor(day))
                vMinTemp.text = "${min.toString()}°C"
                vMaxTemp.text = "${max.toString()}°C"
                vNight.text = "${night.toString()}°C" ; vNight.setBackgroundColor(defineColor(night))
                vMorn.text = "${morn.toString()}°C" ; vMorn.setBackgroundColor(defineColor(morn))
                vEve.text = "${eve.toString()}°C" ;vEve.setBackgroundColor(defineColor(eve))
            }
        }
    }

    private fun defineColor(t: Float): Int = when {
        t < 10f -> R.color.frost
        t in 10f..20f -> R.color.mid_frost
        t in 20f..30f -> R.color.mid_warm
        else -> R.color.warm
    }
}