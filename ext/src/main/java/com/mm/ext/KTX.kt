package com.mm.ext

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.viewModelScope
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * KTX
 *
 * @author mmxm
 * @date 2021/3/25 14:25
 */


fun <T> ComponentActivity.launch(block: (flow: Flow<T>) -> Unit) =
    this.lifecycle.coroutineScope.launch() {
        block(flow {

        })
    }

inline fun <T> Flow<T>.launch(
    scope: CoroutineScope,
    context: CoroutineContext = EmptyCoroutineContext,
    crossinline action: suspend (value: T) -> Unit
) {
    scope.launch(context) {
        this@launch.collect(action)
    }
}

fun <T> ViewModel.launch(block: suspend () -> T, error: (Throwable) -> Unit) =
    viewModelScope.launch {
        try {
            block()
        } catch (e: Throwable) {
            e.printStackTrace()
            error(e)
        }
    }

fun <T> ViewModel.launch(block: suspend () -> T) = viewModelScope.launch {
    try {
        block()
    } catch (e: Throwable) {
        e.printStackTrace()
    }
}

/**
 * if else 扩展
 * @receiver Boolean
 * @param yesBlock Function0<T>
 * @param noBlock Function0<T>
 * @return T
 */
inline fun <T> Boolean.IE(yesBlock: (() -> T), noBlock: (() -> T)): T {
    return if (this) {
        yesBlock.invoke()
    } else {
        noBlock.invoke()
    }
}

/**
 * 主线程运行代码块
 * @param block Function0<Unit>
 */
inline fun runMain(crossinline block: () -> Unit) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        block()
    } else {
        Handler(Looper.getMainLooper()).post { block() }
    }
}

fun Context.inflate(@LayoutRes resource: Int, root: ViewGroup, attachToRoot: Boolean): View {
    return LayoutInflater.from(this).inflate(resource, root, attachToRoot)
}