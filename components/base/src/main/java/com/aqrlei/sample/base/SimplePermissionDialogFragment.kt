package com.aqrlei.sample.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aqrlei.litepermission.PermissionProxyDialogFragment
import com.aqrlei.litepermission.PermissionsNeededModule
import kotlinx.android.synthetic.main.permission_item_recycler.view.*
import kotlinx.android.synthetic.main.permission_simple_dialog.*

/**
 * created by AqrLei on 2020/3/2
 */
class SimplePermissionDialogFragment : PermissionProxyDialogFragment() {

    override fun getContentLayout(): Int = R.layout.permission_simple_dialog

    override fun bindView(v: View, permissionsNeededModule: PermissionsNeededModule?) {
        v.findViewById<RecyclerView>(R.id.rvPermission).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = SimplePermissionAdapter()
        }

        v.findViewById<View>(R.id.tvRequest).setOnClickListener {
            dispatchPermissions()
        }
        v.findViewById<View>(R.id.ivClose).setOnClickListener {
            dismiss()
        }
    }

    override fun onGranted(permissionsNeededModule: PermissionsNeededModule?) {
        super.onGranted(permissionsNeededModule)
        tvRequest.visibility = View.GONE
        ivClose.visibility = View.VISIBLE
    }

    override fun onDenied(permissionsNeededModule: PermissionsNeededModule?) {
        super.onDenied(permissionsNeededModule)
        ivClose.visibility = View.VISIBLE
    }

    override fun onDeniedCannotShowPermissionRationale(permissionsNeededModule: PermissionsNeededModule?) {
        super.onDeniedCannotShowPermissionRationale(permissionsNeededModule)
        ivClose.visibility = View.VISIBLE
    }

    override fun updatePermissionStatus(permissionsNeededModule: PermissionsNeededModule?) {
        view?.findViewById<RecyclerView>(R.id.rvPermission)?.adapter?.notifyDataSetChanged()
    }


    inner class SimplePermissionAdapter : RecyclerView.Adapter<PermissionHolder>() {

        override fun getItemCount(): Int = permissionsNeededModule?.permissionNeeded?.size ?: 0
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PermissionHolder {
            return PermissionHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.permission_item_recycler,
                    parent,
                    false))
        }

        override fun onBindViewHolder(holder: PermissionHolder, position: Int) {
            permissionsNeededModule?.permissionNeeded?.get(position)?.let {
                holder.itemView.tvPermission.let { tv ->
                    tv.text = it.desc
                    tv.isSelected = it.isGranted
                }
            }
        }
    }

    inner class PermissionHolder(v: View) : RecyclerView.ViewHolder(v)
}