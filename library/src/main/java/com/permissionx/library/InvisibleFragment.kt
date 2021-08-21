package com.permissionx.library

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
//优化一下代码
typealias PermissionCallback = (Boolean, List<String>) -> Unit
class InvisibleFragment : Fragment(){
        private var callback : PermissionCallback ?= null//函数类型变量call，它没有返回值，Unit表示没有返回值

    //vararg关键字表示permissions参数长度可变。
    fun requestNow(cb: PermissionCallback, vararg permissions: String) {
        callback = cb
        //Fragment中的requestPermissions方法申请运行时权限
        requestPermissions(permissions,1)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 1) {
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {

                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()
            callback?.let { it(allGranted, deniedList) }

        }
    }
}