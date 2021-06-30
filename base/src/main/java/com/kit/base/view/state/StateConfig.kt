package com.kit.base.view.state

import android.view.View
import androidx.annotation.LayoutRes
import com.kit.base.R

/**
 * 缺省图全局配置
 *
 * @author mmxm
 * @date 2021/6/28 16:04
 */
object StateConfig {

    fun default(){
        StateConfig.apply {
            emptyLayout= R.layout.layout_empty
            errorLayout= R.layout.layout_error
            loadingLayout=R.layout.layout_loading

            emptyRetryIds= intArrayOf(R.id.emptyIv,R.id.emptyIv)
            errorRetryIds= intArrayOf(R.id.errorIv,R.id.errorTv)
            onEmpty {
                    data ->
                data?.let {
                    setText(R.id.emptyIv,data.toString())
                }
            }
            onLoading {
                    data ->
            }
            onError {
                    data->
                data?.let {
                    setText(R.id.errorTv,data.toString())
                }
            }
        }
    }

    @LayoutRes
    var errorLayout: Int = View.NO_ID

    @LayoutRes
    var emptyLayout: Int = View.NO_ID

    @LayoutRes
    var loadingLayout: Int = View.NO_ID

    /**
     * 错误视图可重试id集合
     */
    var errorRetryIds: IntArray = intArrayOf()

    /**
     * 空视图可重试id集合
     */
    var emptyRetryIds: IntArray = intArrayOf()


    internal var onErrorBlock: (View.(data: Any?) -> Unit)? = null
    internal var onEmptyBlock: (View.(data: Any?) -> Unit)? = null
    internal var onLoadingBlock: (View.(data: Any?) -> Unit)? = null


    /**
     * 空回调
     *
     *
     * @param block Function0<Unit>
     * @return StateLayout
     */
    fun onEmpty(block: View.(data: Any?) -> Unit) {
        onEmptyBlock = block
    }

    /**
     * 加载中回调
     * @param block Function0<Unit>
     * @return StateLayout
     */
    fun onLoading(block: View.(data: Any?) -> Unit) {
        onLoadingBlock = block
    }

    /**
     * 错误回调
     * @param block Function0<Unit>
     * @return StateLayout
     */
    fun onError(block: View.(data: Any?) -> Unit) {
        onErrorBlock = block
    }


}