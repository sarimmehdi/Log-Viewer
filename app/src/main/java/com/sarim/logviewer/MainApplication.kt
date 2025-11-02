package com.sarim.logviewer

import android.app.Application
import com.sarim.sidebar_di.module
import org.koin.android.ext.koin.androidContext
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.lazyModules
import org.koin.dsl.koinConfiguration

@OptIn(KoinExperimentalAPI::class)
internal class MainApplication :
    Application(),
    KoinStartup {
    override fun onKoinStartup() =
        koinConfiguration {
            androidContext(this@MainApplication)
            lazyModules(module())
        }
}
