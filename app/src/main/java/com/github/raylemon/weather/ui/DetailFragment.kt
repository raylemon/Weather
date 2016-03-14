package com.github.raylemon.weather.ui

import android.app.Fragment
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.raylemon.weather.R
import com.github.raylemon.weather.data.domain.Forecast
import com.github.raylemon.weather.ext.toDate
import kotlinx.android.synthetic.main.detail_fragment.*

/**
 * Created by christophe on 14/03/16.
 */
class DetailFragment : Fragment() {
    private lateinit var forecast : Forecast
        private set

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.detail_fragment, container)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with (forecast) {
            vDate.text = dt.toDate()
            vDescription.text = desc
            vMaxTemp.text = temp.max.toString() + "°C"
            vMinTemp.text = temp.min.toString() + "°C"
            vPressure.text = pressure.toString() + "hPa"
            vHumidity.text = humidity.toString() + "%"
            vDay.apply {
                text = temp.day.toString() + "°C"
                setBackgroundColor(defineColor(temp.day))
            }
            vNight.apply {
                text = temp.night.toString() + "°C"
                setBackgroundColor(defineColor(temp.night))
            }
            vMorn.apply {
                text = temp.morn.toString() + "°C"
                setBackgroundColor(defineColor(temp.morn))
            }

            vEve.apply {
                text = temp.eve.toString() + "°C"
                setBackgroundColor(defineColor(temp.eve))
            }

        }
    }

    private fun defineColor(temp: Float) = when {
        temp < 10f -> ContextCompat.getColor(activity, R.color.frost)
        temp in 10f..20f -> ContextCompat.getColor(activity, R.color.mid_frost)
        temp in 20f..30f -> ContextCompat.getColor(activity, R.color.mid_warm)
        else -> ContextCompat.getColor(activity, R.color.warm)
    }
}