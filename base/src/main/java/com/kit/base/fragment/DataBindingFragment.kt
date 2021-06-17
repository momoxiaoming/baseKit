package com.kit.base.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.kit.base.viewmodel.AbsViewModel

/**
 * DataBingFragment
 *
 * @author mmxm
 * @date 2021/4/14 15:00
 */
abstract class DataBindingFragment<T : ViewDataBinding, VM : AbsViewModel> : BaseFragment() {

    lateinit var mBinding:T
    /**
     * ViewModel
     */
    protected val vm: VM by lazy { getViewModel() }

    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): VM
    /**
     * 首先屏蔽baseFragment 获取资源的方法
     * @return Int
     */
    override fun getLayoutRes(): Int {
        return 0
    }
    /**
     * 注册一些
     */
    abstract fun initObserver()

    override fun onCreateRootView(inflater: LayoutInflater, container: ViewGroup?): View? {
        mBinding=DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        initObserver()
        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (this::mBinding.isInitialized) {
            mBinding.unbind()
            mBinding.lifecycleOwner = null
        }
    }
}