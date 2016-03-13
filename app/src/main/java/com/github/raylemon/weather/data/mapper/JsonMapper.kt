package com.github.raylemon.weather.data.mapper

import com.github.raylemon.weather.data.domain.ForecastList
import com.github.raylemon.weather.data.json.Forecast
import com.github.raylemon.weather.data.json.ForecastResult
import com.github.raylemon.weather.data.json.Temperatures
import com.github.raylemon.weather.data.domain.Forecast as DomainForecast
import com.github.raylemon.weather.data.domain.Temperatures as DomainTemperatures

/**
 * Created by big04 on 06-03-16.
 */
object JsonMapper {
    fun convertToDomain(result: ForecastResult) = with(result) {
        ForecastList(city.name, city.country, convertListToDomain(list))
    }

    private fun convertListToDomain(list: List<Forecast>) = list.map { convertItemToDomain(it) }

    private fun convertItemToDomain(item: Forecast) = with(item) {
        DomainForecast(dt = dt, desc = weather.first().description, icon = fullUrl(weather.first().icon), temp = convertTempToDomain(temp), pressure = pressure, humidity = humidity)
    }

    private fun convertTempToDomain(temp: Temperatures) = with(temp) {
        DomainTemperatures(day, min, max, night, eve, morn)
    }

    private fun fullUrl(icon: String) = "http://openweathermap.org/img/w/$icon.png"
}