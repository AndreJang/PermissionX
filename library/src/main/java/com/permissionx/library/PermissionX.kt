package com.permissionx.library

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

object PermissionX {
    private const val TAG = "InvisibleFragment"

    //FragmentActivity是AppCompatActivity的父类
    fun request(activity: FragmentActivity, vararg permissions: String, callback: PermissionCallback){
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)

        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment//相当于val fragment = invisibleFragment这一行代码
        }

        fragment.requestNow(callback,*permissions)

    }
}