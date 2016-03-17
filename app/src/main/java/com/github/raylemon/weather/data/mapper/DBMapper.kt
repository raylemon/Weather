package com.github.raylemon.weather.data.mapper

import com.github.raylemon.weather.data.db.Forecast
import com.github.raylemon.weather.data.db.ForecastList
import com.github.raylemon.weather.data.db.Temperatures
import com.github.raylemon.weather.data.domain.Forecast as DFore
import com.github.raylemon.weather.data.domain.ForecastList as DFList
import com.github.raylemon.weather.data.domain.Temperatures as DTemp

/**
 * Created by big04 on 17-03-16.
 */
object DBMapper {
    fun convertToDomain(forecastList: ForecastList) = with(forecastList) { DFList(city, country, convertListToDomain(weather)) }
    private fun convertListToDomain(list: List<Forecast>) = list.map { convertItemListToDomain(it) }
    private fun convertItemListToDomain(forecast: Forecast) = with(forecast) { DFore(dt, desc, icon, convertTempToDomain(temp), pressure, humidity) }
    private fun convertTempToDomain(temperatures: Temperatures) = with(temperatures) { DTemp(day, min, max, night, eve, morn) }

    fun convertFromDomain(forecastList: DFList) = with(forecastList) { ForecastList(city, country, convertListFromDomain(weather)) }
    private fun convertListFromDomain(list: List<DFore>) = list.map { convertItemListFromDomain(it) }
    private fun convertItemListFromDomain(forecast: DFore) = with(forecast) { Forecast(dt, desc, icon, convertTempFromDomain(temp), pressure, humidity) }
    private fun convertTempFromDomain(temperatures: DTemp) = with(temperatures) { Temperatures(day, min, max, night, eve, morn) }

}