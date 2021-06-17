package com.kit.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * AbsViewModel
 *
 * @author mmxm
 * @date 2021/4/14 15:57
 */
abstract class AbsViewModel : ViewModel() {

    /**
     * 是否结束，true时，界面退出
     */
    val isPageFinish = MutableLiveData<Boolean>()

    /**
     * 是否显示加载窗
     */
    val loadingDialogState = MutableLiveData<Boolean>()

//    val scope by lazy {
//        viewModelScope
//    }
//
//    fun showLoading(msg:String){
//        mLoadingDialog.value= LoadingItem(true,msg)
//    }
//
//    fun hideLoading(){
//        mLoadingDialog.value= LoadingItem(false)
//    }
}