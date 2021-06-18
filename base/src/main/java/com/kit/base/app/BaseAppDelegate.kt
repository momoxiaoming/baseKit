package com.kit.base.app

import android.app.Application
import android.content.Context
import com.mm.kit.common.process.ProcessUtils

/**
 * application生命周期代理
 *
 * @author mmxm
 * @date 2021/6/17 14:51
 */
open class BaseAppDelegate(var app: Application) {
    open fun attachBaseContext(base: Context?) {
        initLog()
    }

    open fun initLog() {
    }

    open fun onCreate() {
        val processName = ProcessUtils.currentProcessName
        val remoteProc = ProcessUtils.isRemoteProcess(processName)

        AppMod.init(app)
        if(!remoteProc){
            onCreateEnv(processName,remoteProc)
        }
    }

    open fun onTerminate() {
    }

    open fun onLowMemory() {
    }

    open fun onTrimMemory(level: Int) {

    }

    /**
     * 初始化环境
     *
     * @param processName 当前进程名
     * @param remoteProc 当前是否是 远程进程。大多数情况下，如果是true,
     *                  则不会做一些sdk的初始化。
     * @see ProcessUtils
     */
    open fun onCreateEnv(processName: String, remoteProc: Boolean) {

    }
}