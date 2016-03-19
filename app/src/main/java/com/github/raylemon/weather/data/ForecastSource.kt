package com.github.raylemon.weather.data

import com.github.raylemon.weather.data.domain.ForecastList

/**
 * Created by big04 on 17-03-16.
 */
interface ForecastSource {
    fun getForecast(cnt: Int, city: String): ForecastList?
}