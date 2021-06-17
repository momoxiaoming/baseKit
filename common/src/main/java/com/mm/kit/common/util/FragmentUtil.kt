package com.mm.kit.common.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * FragmentUtil
 *
 * @author mmxm
 * @date 2021/6/17 12:24
 */
object FragmentUtil {

    /**
     * 装载一个fragment
     * @param fragmentManager FragmentManager
     * @param containerId Int
     * @param fragment Fragment
     */
    fun show(fragmentManager: FragmentManager,containerId:Int,fragment:Fragment){
        val ft = fragmentManager.beginTransaction()
        if (fragment.isAdded) {
            ft.show(fragment)
        } else {
            ft.replace(containerId, fragment)
        }
        ft.commitAllowingStateLoss()
    }
}