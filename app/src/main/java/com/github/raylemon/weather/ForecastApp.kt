package com.github.raylemon.weather

import android.app.Application
import android.content.Context
import info.quantumflux.QuantumFlux

/**
 * Created by big04 on 17-03-16.
 */
class ForecastApp : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        QuantumFlux.initialize(this)
    }
}