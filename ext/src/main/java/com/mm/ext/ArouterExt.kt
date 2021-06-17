@file:Suppress("NOTHING_TO_INLINE")
package com.mm.ext

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter

/**
 * ArouterExt
 *
 * @author mmxm
 * @date 2021/3/24 20:30
 */
inline fun arouter(): ARouter = ARouter.getInstance()

/**
 * 通过 ARouter拿 Fragment
 */
inline fun <reified T : Fragment> ARouter.fragment(path: String): T? =
    arouter().build(path).navigation() as? T

