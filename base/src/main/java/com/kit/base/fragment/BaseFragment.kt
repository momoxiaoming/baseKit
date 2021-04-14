package com.kit.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.mm.kit.common.runable.RunOnce
import kotlinx.coroutines.CoroutineScope

/**
 * 自定义fragment的超类,不建议直接继承,建议通过[DataBindingFragment] 等间接继承
 *
 * @author mmxm
 * @date 2021/4/14 15:04
 */
abstract class BaseFragment : Fragment() {
    var firstCreated = false
    val onFirstVisibleRunner = FirstVisibleRunner()
    var root: View? = null

    /**
     * 绑定Fragment的lifecycle 的 Coroutine Scope
     */
    val scope: CoroutineScope by lazy {
        lifecycleScope
    }

    /**
     * 绑定Fragment 当前的view的lifecycle 的 Coroutine Scope
     */
    val viewScope: CoroutineScope by lazy {
        if (view != null) {
            viewLifecycleOwner.lifecycleScope
        } else {
            scope
        }
    }

    /**
     * 初始化布局
     */
    abstract fun getLayoutRes(): Int

    /**
     * 当[getLayoutRes]返回0时,会执行此方法
     */
    open fun onCreateRootView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View? {
        return null
    }

    /**
     * 第一次用户可见的时候才运行。
     * 可以配合 viewpager使用，实现可见时加载逻辑。
     */
    open fun onFirstVisible() {}

    /**
     * 在initLayout之前执行
     */
    protected open fun beforeInitLayout(firstCreated: Boolean) {}

    /**
     * 初始化布局
     */
    abstract fun initLayout()

    /**
     * 第一次生成view后，的生命周期启动。
     * 自己实现的一种机制, #onActivityCreated 的生命周期同级。
     * 主要在viewpager中，不会来会滑动多次执行。
     * 建议将一般性的 #onActivityCreated 的入口代码，移到这个里面.
     * 注, 依赖生成布局时rootView的非null状态.
     * @see .root
     */
    open fun onFirstActivityCreated(savedInstanceState: Bundle?) {

    }

    /**
     * 标识该页面
     * @return String
     */
    open fun getPageName(): String {
        return this::class.java.name
    }
    /**
     * 方法执行顺序1
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root?.also { root ->
            firstCreated = false
            val rootParent = root.parent as? ViewGroup
            rootParent?.removeView(root)
            return root
        }

        val layoutRes = getLayoutRes()
        firstCreated = true
        root = if (layoutRes > 0) {
            inflater.inflate(layoutRes, container, false)
        } else {
            onCreateRootView(inflater, container)
        }
        return root
    }

    /**
     * 方法执行顺序2
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        beforeInitLayout(firstCreated)
        if (firstCreated) {
            // 是第一次生成view,
            // 所以要初始化view
            initLayout()
        }
    }

    /**
     * 方法执行顺序3
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (firstCreated) {
            onFirstActivityCreated(savedInstanceState)
        }
    }

    override fun onResume() {
        super.onResume()
        onFirstVisibleRunner.run()
    }

    inner class FirstVisibleRunner : RunOnce() {
        override fun running() {
            this@BaseFragment.onFirstVisible()
        }
    }
}
