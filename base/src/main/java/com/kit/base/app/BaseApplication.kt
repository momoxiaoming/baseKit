package com.kit.base.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

/**
 * BaseApplication
 *
 * @author mmxm
 * @date 2021/6/17 14:50
 */
abstract class BaseApplication : Application() {

    val delegate: BaseAppDelegate by lazy {
        createDelegate()
    }

    abstract fun createDelegate(): BaseAppDelegate

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)  // compat all platform version
        delegate.attachBaseContext(base)
    }
    override fun onCreate() {
        super.onCreate()
        delegate.onCreate()
    }
    override fun onTerminate() {
        super.onTerminate()
        delegate.onTerminate()
    }
    override fun onLowMemory() {
        super.onLowMemory()
        delegate.onLowMemory()
    }
    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        delegate.onTrimMemory(level)
    }
}