package com.kit.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope

/**
 * BaseActivity
 *
 * @author mmxm
 * @date 2021/4/14 17:10
 */
 abstract class BaseActivity : AppCompatActivity() {

    val scope by lazy {
        lifecycle.coroutineScope
    }
    var willSetLayout=true


    /**
     * 先执行initLayout后
     */
    abstract fun initLayout()

    /**
     * 内部的onCreate 基本等同于super.onCreate.
     * 优先于 beforeSetLayout
     * @param savedInstanceState
     */
    protected open fun onInternalCreate(savedInstanceState: Bundle?) {

    }

    /**
     * 正在结束。 一般扫尾操作 可以替换 掉 [onDestroy]
     *
     * 如果调用了[finish].
     * 则会在页面关闭时，也就是[onPause]的时候，调用 [onFinishing]。
     * 相比 [onDestroy]会更及时一些。
     *
     * 在有些后台任务时，activity finish后不会马上调用 [onDestroy]。
     */
    open fun onFinishing() {
    }

    /**
     * 标识该页面
     * @return String
     */
    open fun getPageName(): String {
        return this::class.java.name
    }

    /**
     * 在初始化layout前。控制流程。
     * 如果不用设置ui layout， 则返回 false.
     * 如果返回 false (如环境异常), 则会回调 [onNotSetLayout]。
     *
     * 同时将返回值设置到 [willSetLayout]
     */
    protected open fun beforeSetLayout(): Boolean {
        return true
    }


    /**
     * 无须设置layout的时候，会回调。
     * 默认 finish activity
     */
    protected open fun onNotSetLayout() {
        this.finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onInternalCreate(savedInstanceState)
        willSetLayout=beforeSetLayout()
        if (!willSetLayout) {
            onNotSetLayout()
            return
        }
        initLayout()
    }

    override fun onPause() {
        super.onPause()
        if (isFinishing) {
            performOnFinished()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        performOnFinished()
    }

    private var onFinished = false

    private fun performOnFinished() {
        if (!onFinished) {
            onFinished = true
            onFinishing()
        }
    }

    /**
     * 返回。
     * 默认就是 finish
     */
    open fun goBack() {
        finish()
    }
}