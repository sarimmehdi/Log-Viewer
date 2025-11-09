package com.sarim.logviewer

import android.app.Application
import com.sarim.sidebar_dates_di.module as sideBarDatesModule
import com.sarim.sidebar_sessions_di.module as sideBarSessionsModule
import com.sarim.maincontent_di.module as mainContentModule
import com.sarim.footer_di.module as footerModule
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
            lazyModules(
                sideBarDatesModule(),
                sideBarSessionsModule(),
                mainContentModule(),
                footerModule(),
            )
        }
}
