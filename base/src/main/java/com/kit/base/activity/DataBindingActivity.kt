package com.kit.base.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.kit.base.viewmodel.BaseViewModel

/**
 * DataBindingActivity
 *
 * @author mmxm
 * @date 2021/4/14 17:19
 */
abstract class DataBindingActivity<T : ViewDataBinding, VM : BaseViewModel> : BaseActivity() {

    lateinit var mBinding: T

    /**
     * ViewModel
     */
    val mModel: VM by lazy { getViewModel() }

    /**
     * 资源layout
     * @return Int
     */
    abstract fun getLayoutId(): Int

    /**
     * 获取viewmodel
     * @return VM
     */
    abstract fun getViewModel(): VM


    abstract fun initView()
    abstract fun initViewData()
    /**
     * 注册一些liveData
     */
    open fun initObserver() {
    }

    override fun initLayout() {
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mBinding.lifecycleOwner = this
        initObserver()
        initView()
        initViewData()
    }


    override fun onDestroy() {
        super.onDestroy()
        mBinding.unbind()
    }
}