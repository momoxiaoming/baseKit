package com.kit.base.data

/**
 * 数据状态密封类,配合liveData使用
 *
 * @author mmxm
 * @date 2021/6/28 14:51
 */

sealed class Resource<T>(val data: T?, val msg: String? = null) {

    abstract val isSuccess: Boolean
    abstract val isLoading: Boolean
    abstract val isError: Boolean

    fun hasData():Boolean{
        return data!=null
    }

    class Success<T>(data: T,msg: String?=null) : Resource<T>(data,msg) {
        override val isSuccess: Boolean = true
        override val isLoading: Boolean = false
        override val isError: Boolean = false
    }

    class Loading<T>(data: T?,msg: String?=null) : Resource<T>(data,msg) {
        override val isSuccess: Boolean = false
        override val isLoading: Boolean = true
        override val isError: Boolean = false
    }

    class Error<T>(msg: String?=null,data: T?=null,) : Resource<T>(data,msg) {
        override val isSuccess: Boolean = false
        override val isLoading: Boolean = false
        override val isError: Boolean = true
    }
}