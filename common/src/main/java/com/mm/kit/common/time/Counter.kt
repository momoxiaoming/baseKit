package com.mm.kit.common.time


import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.TickerMode
import kotlinx.coroutines.channels.ticker
import java.io.Serializable
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * 计时器
 * @property start Long 开始
 * @property end Long  结束时数值, -1 代表永不结束
 * @property period Long 计时间隔
 * @property delay Long 第一次开始计时的延迟时间
 * @property unit TimeUnit 计时器单位
 * @property scope CoroutineScope 作用域
 * @constructor
 * @author mmxm
 * @date 2021/4/14 12:07
 */
class Counter(
    lifecycleOwner: LifecycleOwner,
    private val stopEvent:Lifecycle.Event = Lifecycle.Event.ON_DESTROY,  //这里停止的周期根据实际去把控
    private val start: Long,
    private val end: Long,
    private val period: Long = 1,
    private val initDelay: Long = 0,
    private val unit: TimeUnit = TimeUnit.SECONDS,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main),
) :
    Serializable, ICounter {

    private val listReceiver = LinkedList<(Long) -> Unit>()
    private val listFinish = LinkedList<(Long) -> Unit>()
    private var state = CounterStatus.STATE_IDLE
    private var count = start
    private var countTime: Long = 0
    private var delay = 0L

    @ObsoleteCoroutinesApi
    private lateinit var ticker: ReceiveChannel<Unit>

    init {

        lifecycleOwner.lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == stopEvent) {
                    stop()
                }
            }
        })
    }


    @ObsoleteCoroutinesApi
    private fun launch(delay: Long = unit.toMillis(initDelay)) {
        scope.launch {
            ticker=ticker(unit.toMillis(period), initDelay, mode = TickerMode.FIXED_DELAY)
            for (unit in ticker) {
                noticeSubscribe()
                if (end != -1L && count == end) {
                    scope.cancel()
                    noticeSubscribeFinish()
                }

                if (end != -1L && start > end) count-- else count++
                countTime = System.currentTimeMillis()
            }
        }
    }

    private fun noticeSubscribe() {
        listReceiver.forEach {
            it.invoke(count)
        }
    }

    private fun noticeSubscribeFinish() {
        listFinish.forEach {
            it.invoke(count)
        }
    }


    override fun subscribe(block: (Long) -> Unit): Counter {
        listReceiver.add(block)
        return this
    }

    override fun subscribeFinish(block: (Long) -> Unit): Counter {
        listFinish.add(block)
        return this
    }


    override fun start() {
        when (state) {
            CounterStatus.STATE_IDLE, CounterStatus.STATE_PAUSE -> {
                state = CounterStatus.STATE_ACTIVE
                launch()
            }
            else -> {

            }
        }
    }

    override fun resume() {
        when (state) {
            CounterStatus.STATE_IDLE, CounterStatus.STATE_PAUSE -> {
                state = CounterStatus.STATE_ACTIVE
                launch(delay)
            }
            else -> {

            }
        }
    }

    override fun pause() {
        if (state == CounterStatus.STATE_ACTIVE) {
            state = CounterStatus.STATE_PAUSE
            delay = System.currentTimeMillis() - countTime
            scope.cancel()
        }
    }

    override fun reset() {
        if (state == CounterStatus.STATE_IDLE) return
        count = start
        scope.cancel()
        delay = unit.toMillis(initDelay)
        if (state == CounterStatus.STATE_ACTIVE) launch()
    }

    override fun stop() {
        if (state == CounterStatus.STATE_IDLE) return
        state = CounterStatus.STATE_IDLE
        scope.cancel()
        count = start
    }
}


interface ICounter {

    /**
     * 订阅计数器,计数器改变以及完成时都会粗发此回调
     */
    fun subscribe(block: (Long) -> Unit): Counter

    /**
     * 订阅计数器,计数器完成时会触发此回调
     *
     */
    fun subscribeFinish(block: (Long) -> Unit): Counter


    /**
     * 启动
     */
    fun start()

    /**
     * 继续
     */
    fun resume()

    /**
     * 暂停
     */
    fun pause()

    /**
     * 重置
     */
    fun reset()

    /**
     * 停止
     */
    fun stop()
}

/**
 * 计时器的状态
 */
enum class CounterStatus {
    STATE_ACTIVE, STATE_IDLE, STATE_PAUSE
}