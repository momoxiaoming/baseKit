package com.kit.base.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.kit.base.viewmodel.AbsViewModel

/**
 * DataBindingActivity
 *
 * @author mmxm
 * @date 2021/4/14 17:19
 */
abstract class DataBindingActivity<T : ViewDataBinding, VM : AbsViewModel> : BaseActivity() {

    lateinit var mBinding: T

    /**
     * ViewModel
     */
    val vm: VM by lazy { getViewModel() }

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

    /**
     * 注册一些
     */
    open fun initObserver() {
        vm.isPageFinish.observe(this) {
            if (it) {
                goBack()
            }
        }
        vm.loadingDialogState.observe(this) {
            if (it) {
                //显示加载窗
            }else{
                //隐藏加载创
            }
        }
    }

    override fun initLayout() {
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mBinding.lifecycleOwner = this
        initObserver()
    }


    override fun onDestroy() {
        super.onDestroy()
        mBinding.unbind()
    }
}