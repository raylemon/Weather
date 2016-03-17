package com.github.raylemon.weather.data.db

import za.co.cporm.model.CPOrmConfiguration

/**
 * Created by big04 on 17-03-16.
 */
class CPOrmConfig : CPOrmConfiguration {
    override fun isQueryLoggingEnabled() = true
    override fun getDatabaseName() = "forecast.db"
    override fun getDatabaseVersion() = 1
    override fun getDataModelObjects(): MutableList<Class<*>>? {
        return arrayListOf(ForecastList::class.java, Forecast::class.java, Temperatures::class.java)
    }


}