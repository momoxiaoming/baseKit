package com.mm.kit.common.liveData

import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * 忽略livedata首次数据,
 *
 * @author mmxm
 * @date 2021/6/28 14:22
 */
abstract class SingleObserver<T> : Observer<T> {
    var isFlow = AtomicBoolean(false)
    override fun onChanged(t: T) {
        if (isFlow.compareAndSet(false, true)) {
            return
        }
        this.onSingleChanged(t)
    }

    abstract fun onSingleChanged(t: T)
}