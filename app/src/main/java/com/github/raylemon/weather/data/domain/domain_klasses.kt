package com.github.raylemon.weather.data.domain

/**
 * Created by big04 on 06-03-16.
 */
data class ForecastList(val city: String, val country: String, val weather: List<Forecast>) {
    operator fun get(position: Int) = weather[position]
    val size = weather.size
}

data class Forecast(val dt: Long, val desc: String, val icon: String, val temp: Temperatures, val pressure: Float, val humidity: Int)
data class Temperatures(val day: Float, val min: Float, val max: Float, val night: Float, val eve: Float, val morn: Float)
