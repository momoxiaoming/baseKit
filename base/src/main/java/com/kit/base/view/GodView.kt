package com.kit.base.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout
import com.mm.kit.common.R

/**
 * BackgroundView
 *
 * @author mmxm
 * @date 2021/5/31 10:43
 */
@SuppressLint("CustomViewStyleable", "Recycle", "ResourceAsColor")
class GodView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val TAG = "GodView"
        private val COLOR_BACKGROUND_ATTR = intArrayOf(android.R.attr.colorBackground)
        fun log(msg: String) {
            Log.d(TAG, msg)
        }
    }



    private var viewBgColor = 0

    private var selBgColor: Int = 0
    private var norBgColor: Int = 0

    /**
     * 圆角,分别是上左,上右,下左,下右
     *
     */
    private var cornerRadiusArry: FloatArray = FloatArray(4)


    init {
        //获取属性
        val typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.GodView) //名称必须和类名相同

        // There isn't one set, so we'll compute one based on the theme



        if (typedArray.hasValue(R.styleable.GodView_godCornerRadius)) {
            //全部圆角
            val radius=typedArray.getDimension(R.styleable.GodView_godCornerRadius,0f)
            cornerRadiusArry[0]=radius
            cornerRadiusArry[1]=radius
            cornerRadiusArry[2]=radius
            cornerRadiusArry[3]=radius
        }else{

            cornerRadiusArry[0]=typedArray.getDimension(R.styleable.GodView_godTopAndLeftCornerRadius,0f)
            cornerRadiusArry[1]=typedArray.getDimension(R.styleable.GodView_godTopAndRightCornerRadius,0f)
            cornerRadiusArry[2]=typedArray.getDimension(R.styleable.GodView_godBottomAndLeftCornerRadius,0f)
            cornerRadiusArry[3]=typedArray.getDimension(R.styleable.GodView_godBottomAndRightCornerRadius,0f)
        }

        if(typedArray.hasValue(R.styleable.GodView_godBackgroundColor)){
            val bgColor=typedArray.getColor(R.styleable.GodView_godBackgroundColor,android.R.color.transparent)
            setBackgroundColor(bgColor)
        }else{
             val aa = getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR)
             val themeColorBackground = aa.getColor(0, 0)
             aa.recycle() //回收系统BackgroundColor
             selBgColor=typedArray.getColor(R.styleable.GodView_godSelBackgroundColor,android.R.color.transparent)
             norBgColor=typedArray.getColor(R.styleable.GodView_godNorBackgroundColor,android.R.color.transparent)
            initListener()
        }



    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.let {

        }

    }

    @SuppressLint("ClickableViewAccessibility")
    fun initListener() {
        setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    changBgColor(selBgColor)
                }
                MotionEvent.ACTION_UP -> {
                    changBgColor(norBgColor)
                }
            }
            false
        }
    }

    private fun changBgColor(color: Int) {
        viewBgColor = color
        setBackgroundColor(color)
    }
}