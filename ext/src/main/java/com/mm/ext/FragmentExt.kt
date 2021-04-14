package com.mm.ext

import androidx.fragment.app.Fragment

/**
 * Fragment扩展
 *
 * @author mmxm
 * @date 2021/4/14 15:04
 */

/**
 * 获取当前activity，并尝试转换为[T]
 * @param withActivity 如果转换成功，则会在 [T]的作用域下执行[withActivity]
 */
inline fun <reified T> Fragment.activityAs(withActivity: T.() -> Unit): T? {
    return (activity as? T).also {
        it?.withActivity()
    }
}