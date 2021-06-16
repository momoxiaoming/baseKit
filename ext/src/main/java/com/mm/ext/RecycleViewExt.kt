package com.mm.ext

import android.view.View.OVER_SCROLL_NEVER
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mm.kit.common.drawable.DividerDrawable


/**
 * 创建[LinearLayoutManager]  线性列表
 * @param orientation 列表方向
 * @param reverseLayout 是否反转列表
 */
fun RecyclerView.linear(
    @RecyclerView.Orientation orientation: Int = RecyclerView.VERTICAL,
    reverseLayout: Boolean = false,
): RecyclerView {
    overScrollMode=OVER_SCROLL_NEVER
    layoutManager = LinearLayoutManager(context,orientation,reverseLayout)
    return this
}

/**
 * 创建[GridLayoutManager] 网格列表
 * @param spanCount 网格跨度数量
 * @param orientation 列表方向
 * @param reverseLayout 是否反转
 * @param scrollEnabled 是否允许滚动
 */
fun RecyclerView.grid(
    spanCount: Int = 1,
    @RecyclerView.Orientation orientation: Int = RecyclerView.VERTICAL,
    reverseLayout: Boolean = false,
): RecyclerView {
    overScrollMode=OVER_SCROLL_NEVER
    layoutManager =
        GridLayoutManager(context, spanCount, orientation, reverseLayout)
    return this
}


/**
 * 添加一个默认的 vertical方向上的，横分割线
 *  DividerDrawable(1).also { d ->
    d.color = 0xffC8C8C8.toInt()
    }
 *
 */
fun RecyclerView.dividerLine( block:(()-> DividerDrawable)) {
    this.addItemDecoration(
        DividerItemDecoration(
            this.context,
            (layoutManager as LinearLayoutManager).orientation
        ).also { decor ->
            decor.setDrawable(block())
        })
}
