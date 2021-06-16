package com.kit.base.scope

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * AndroidScope
 *
 * @author mmxm
 * @date 2021/4/15 9:50
 */
class AndroidScope(
    override val coroutineContext: CoroutineContext = EmptyCoroutineContext,
    lifecycleOwner: LifecycleOwner? = null,
    lifeEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
) :
    CoroutineScope {

    init {
        lifecycleOwner?.lifecycle?.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (lifeEvent == event) cancel()
            }
        })
    }
}


