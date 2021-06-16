package com.kit.base.scope

import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * DialogScope
 *
 * @author mmxm
 * @date 2021/4/15 11:08
 */
class DialogScope(override val coroutineContext: CoroutineContext) :CoroutineScope,LifecycleObserver {
}