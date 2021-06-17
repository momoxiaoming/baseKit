package com.kit.base.app

import android.app.Application

/**
 * 全局
 *
 * @author mmxm
 * @date 2021/4/14 19:04
 */
object AppMod {
    lateinit var app: Application
    fun init(application: Application) {
        app = application
    }
}