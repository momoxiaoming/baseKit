package com.mm.kit.common.color

import android.graphics.Color
import java.util.*

/**
 * 颜色相关工具类
 *
 * @author mmxm
 * @date 2021/4/14 16:55
 */
object ColorUtils {
    fun randomColor(): Int {
        val random = Random()
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}