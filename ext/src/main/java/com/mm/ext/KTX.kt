package com.mm.ext

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.flow.Flow
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


fun <T> ComponentActivity.launch(block: (flow: Flow<T>) -> Unit) = this.lifecycle.coroutineScope.launch() {
    block(flow {

    })
}

