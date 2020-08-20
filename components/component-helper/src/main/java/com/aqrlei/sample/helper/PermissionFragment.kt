package com.aqrlei.sample.helper

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aqrlei.sample.base.SimplePermissionDialogFragment
import com.aqrlei.litepermission.*
import com.aqrlei.sample.base.BaseFragment
import com.aqrlei.utilcollection.toast.ToastHelper
import kotlinx.android.synthetic.main.frag_permission.*

/**
 * created by AqrLei on 2020/3/16
 */
class PermissionFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        R.layout.support_simple_spinner_dropdown_item
        return inflater.inflate(R.layout.frag_permission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var test: Int = 0
        tvRequestPermission.setOnClickListener {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                if (!PermissionHelper.checkPermission(
                        context!!,
                        arrayListOf(Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE)
                    )
                ) {
                    SimplePermissionDialogFragment()
                        .apply {
                        arguments = PermissionProxyDialogFragment.bundleNeedPermissions(
                            PermissionNeeded(Manifest.permission.CAMERA, "相机权限"),
                            PermissionNeeded(Manifest.permission.CALL_PHONE, "电话权限"),
                            summaryDesc = "",
                            desc = ""
                        )
                        setPermissionCallback(object : IPermissionCallback {
                            override fun onGranted(permissionsNeededModule: PermissionsNeededModule?) {
                                ToastHelper.shortShow("Granted")
                            }

                            override fun onDenied(permissionsNeededModule: PermissionsNeededModule) {
                                ToastHelper.shortShow("Denied")
                            }

                            override fun onDeniedCannotShowPermissionRationale(
                                permissionsNeededModule: PermissionsNeededModule
                            ) {
                                ToastHelper.shortShow("deniedCannotShowPermissionRationale")
                            }
                        })
                    }.also {
                        it.show(this.childFragmentManager, "PermissionHelper")
                    }
                } else {
                    ToastHelper.shortShow("The Permission had already granted - ${test++}")
                }
            }
        }
    }
}