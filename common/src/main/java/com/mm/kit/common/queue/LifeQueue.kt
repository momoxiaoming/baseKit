package com.mm.kit.common.queue

import androidx.lifecycle.*
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

/**
 * life消息队列,根据状态执行队列逻辑
 */
class LifeQueue(var lifecycleOwner: LifecycleOwner, var state: Lifecycle.State) {
    var runing = AtomicBoolean(false)  //线程安全

    private val obj = LifeObserver()
    private val queue = LinkedList<Runnable>()
    private val states: Boolean
        get() = lifecycleOwner.lifecycle.currentState.isAtLeast(state)

    init {
        bindLifecycle(lifecycleOwner)
    }

    fun addQueue(runnable: Runnable) {
        synchronized(queue) {
            queue.add(runnable)
        }
        if (states) {
            startQueue()
        }
    }

    private fun bindLifecycle(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(obj)
    }

    private fun startQueue() {
        if (runing.compareAndSet(false, true)) {
            run()
            runing.compareAndSet(true, false)
        }
    }

    private fun clearQueue() {
        synchronized(queue) {
            if (!queue.isEmpty()) {
                queue.clear()
            }
        }
    }

    private fun run() {
        synchronized(queue) {
            if (queue.isEmpty()) {
                return
            }
        }
        while (true) {
            synchronized(queue) {
                val a = try {
                    queue.poll()
                } catch (e: Exception) {
                    null
                } ?: return
                a.run()
            }
        }

    }


    inner class LifeObserver : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (source.lifecycle.currentState.isAtLeast(state)) {
                //运行队列
                startQueue()
            }
        }
    }
}


