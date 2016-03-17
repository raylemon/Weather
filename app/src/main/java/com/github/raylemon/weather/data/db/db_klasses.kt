package com.github.raylemon.weather.data.db

import za.co.cporm.model.CPDefaultRecord

/**
 * Created by big04 on 17-03-16.
 */
class ForecastList(var city: String = "", var country: String = "", var weather: List<Forecast> = emptyList()) : CPDefaultRecord<ForecastList>()
class Forecast(var dt: Long = 0L, var desc: String = "", var icon: String = "", var temp: Temperatures = Temperatures(), var pressure: Float = 0f, var humidity: Int = 0) : CPDefaultRecord<Forecast>()
class Temperatures(var day: Float = 0f, var min: Float = 0f, var max: Float = 0f, var night: Float = 0f, var morn: Float = 0f, var eve: Float = 0f) : CPDefaultRecord<Temperatures>()