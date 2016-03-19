package com.github.raylemon.weather.data.mapper

import com.github.raylemon.weather.data.db.DbForecast
import com.github.raylemon.weather.data.domain.Forecast
import com.github.raylemon.weather.data.domain.ForecastList
import com.github.raylemon.weather.data.domain.Temperatures

/**
 * Created by big04 on 17-03-16.
 */
object DBMapper {
    fun convertToDomain(weather: List<DbForecast>) = with(weather) { ForecastList(first().city, first().city, convertListToDomain(this)) }
    private fun convertListToDomain(list: List<DbForecast>) = list.map { convertItemListToDomain(it) }
    private fun convertItemListToDomain(dbForecast: DbForecast) = with(dbForecast) { Forecast(dt, desc, icon, Temperatures(day, min, max, night, eve, morn), pressure, humidity) }

    fun convertFromDomain(forecastList: ForecastList) = with(forecastList) {
        convertListFromDomain(weather, city, country)
    }

    private fun convertListFromDomain(list: List<Forecast>, city: String, country: String) = list.map { convertItemListFromDomain(it, city, country) }
    private fun convertItemListFromDomain(forecast: Forecast, city: String, country: String) = with(forecast) { DbForecast(dt, city, country, desc, icon, pressure, humidity, temp.day, temp.min, temp.max, temp.night, temp.morn, temp.eve) }

}