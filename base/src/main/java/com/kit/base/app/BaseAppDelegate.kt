package com.kit.base.app

import android.app.Application
import android.content.Context

/**
 * application生命周期代理
 *
 * @author mmxm
 * @date 2021/6/17 14:51
 */
class BaseAppDelegate(var app: Application) {
    open fun attachBaseContext(base: Context?) {

    }

    open fun onCreate() {
        AppMod.init(app)
    }

    open fun onTerminate() {
    }

    open fun onLowMemory() {
    }

    open fun onTrimMemory(level: Int) {

    }
}