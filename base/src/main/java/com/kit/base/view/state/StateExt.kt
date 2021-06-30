package com.kit.base.view.state

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.kit.base.R

/**
 * State 扩展函数
 *
 * @author mmxm
 * @date 2021/6/29 11:11
 */

/**
 * 将通过代码方式注入[StateLayout]
 */
fun View.state(): StateLayout {
    if (parent is StateLayout) {
        return parent as StateLayout
    }
    if (parent is ViewPager || parent is RecyclerView) {
        throw UnsupportedOperationException("You should using StateLayout wrap [ $this ] in layout when parent is ViewPager or RecyclerView")
    }
    val parentView = parent as ViewGroup
    val index = parentView.indexOfChild(this)
    val targetPrams = layoutParams
    val stateLayout = StateLayout(context)
    stateLayout.id = id
    parentView.removeView(this)
    parentView.addView(stateLayout, index, targetPrams)
    stateLayout.addView(
        this,
        ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    )
    stateLayout.setContent(this)
    return stateLayout
}


fun Activity.state(): StateLayout {
    val v = (findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)
    return v.state()
}

fun Fragment.state(): StateLayout {
    val stateLayout = view!!.state()
    lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun removeState() {
            val parent = stateLayout.parent as ViewGroup
            parent.removeView(stateLayout)
            lifecycle.removeObserver(this)
        }
    })
    return stateLayout
}

fun View.setText(@IdRes id: Int, msg: String) {
    try {
        findViewById<TextView>(id)?.let {
            it.text = msg
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Activity.setText(@IdRes id: Int, msg: String) {
    try {
        findViewById<TextView>(id)?.let {
            it.text = msg
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun <T : View> View.findView(@IdRes id: Int): T? {
    return try {
        findViewById<T>(id)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}


