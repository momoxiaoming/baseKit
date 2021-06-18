package com.kit.base.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.kit.base.viewmodel.BaseViewModel

/**
 * DataBingFragment
 *
 * @author mmxm
 * @date 2021/4/14 15:00
 */
 abstract class DataBindingFragment<T : ViewDataBinding, VM : BaseViewModel> : BaseFragment() {

    lateinit var mBinding:T
    /**
     * ViewModel
     */
    protected val mModel: VM by lazy { getViewModel() }

    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): VM
    abstract fun initView()
    abstract fun initViewData()

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

    override fun initLayout() {
        initView()
        initViewData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (this::mBinding.isInitialized) {
            mBinding.unbind()
            mBinding.lifecycleOwner = null
        }
    }
}