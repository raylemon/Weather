package com.github.raylemon.weather.data

import com.github.raylemon.weather.data.db.DbForecast
import com.github.raylemon.weather.data.domain.ForecastList
import com.github.raylemon.weather.data.mapper.DBMapper
import com.orm.query.Condition
import com.orm.query.Select

/**
 * Created by big04 on 17-03-16.
 */
class DBServer : ForecastSource {
    private val DAY_IN_MILLIS = 1000 * 24 * 60 * 60

    private fun todayInMilis() = System.currentTimeMillis() * DAY_IN_MILLIS / DAY_IN_MILLIS

    override fun getForecast(cnt: Int, city: String): ForecastList? {
        val list = Select.from(DbForecast::class.java)
                .where("dt>= ?", arrayOf(todayInMilis().toString()))
                .and(Condition("city").like(city))
                .limit(cnt.toString())
                .list()
        if (list.size == 0) return null
        else return DBMapper.convertToDomain(list)
    }

    fun save(forecastList: ForecastList) {
        DBMapper.convertFromDomain(forecastList).forEach { it.save() }
    }

}