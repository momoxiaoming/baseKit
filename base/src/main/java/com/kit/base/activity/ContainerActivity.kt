package com.kit.base.activity

import android.util.Log
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.kit.base.ARouterPath
import com.mm.ext.arouter
import com.mm.ext.fragment
import com.mm.kit.common.util.FragmentUtil

/**
 * ContainerActivity
 *
 * @author mmxm
 * @date 2021/6/17 11:52
 */
@Route(path = ARouterPath.Base.ACTIVITY_CONTAINER)
open class ContainerActivity : BaseActivity() {

    companion object {
        const val TAG = "ContainerActivity"
    }

    override fun initLayout() {
        var fragment: Fragment? = null
        do {
            val bundle = intent.extras
            if (bundle == null) {
                Log.e(TAG, "initLayout error: bundle is null")
                break
            }
            val fragmentPath = bundle.getString(ARouterPath.EXTRA_FRAGMENT_PATH)
            if (fragmentPath == null) {
                Log.e(TAG, "initData error: fragmentPath is null")
                break
            }
            Log.i(TAG, "initData: fragment Path:$fragmentPath")
            bundle.remove(ARouterPath.EXTRA_FRAGMENT_PATH)
            fragment = arouter().fragment(fragmentPath)
        } while (false)
        Log.i(TAG, "initData: fragment$fragment")
        if (fragment != null) {
            FragmentUtil.show(supportFragmentManager, getContainerId(), fragment)
        } else {
            goBack()
        }
    }

    open fun getContainerId(): Int {
        return android.R.id.content
    }
}