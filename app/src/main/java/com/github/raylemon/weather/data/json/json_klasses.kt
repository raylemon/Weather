package com.github.raylemon.weather.data.json

/**
 * Created by big04 on 06-03-16.
 */
data class ForecastResult(val cod: Int, val city: City, val list: List<Forecast>)

data class City(val id: Long, val name: String, val coord: Coordinates, val country: String)
data class Coordinates(val lon: Double, val lat: Double)
data class Forecast(val dt: Long, val temp: Temperatures, val pressure: Float, val humidity: Int, val weather: List<Weather>)
data class Temperatures(val day: Float, val min: Float, val max: Float, val night: Float, val eve: Float, val morn: Float)
data class Weather(val id: Int, val description: String, val icon: String)