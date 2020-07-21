package com.aqrlei.sample.helper

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.aqrlei.sample.base.BaseFragment
import com.aqrlei.util.AppUtil
import com.aqrlei.util.BrandUtil
import kotlinx.android.synthetic.main.frag_ignore_battery.*

/**
 * Created by AqrLei on 2019-06-06
 */
class IgnoreBatteryFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_ignore_battery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvRequestSystem.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
               if ( !AppUtil.isIgnoringBatteryOptimizations(this.context)){
                   AppUtil.requestIgnoreBatteryOptimizations(this.context)
               }else {
                   Toast.makeText(this.context,"已在电池优化白名单中",Toast.LENGTH_SHORT).show()
               }
            }
        }

        tvRequestBrand.setOnClickListener {
          when{
              BrandUtil.isHuawei() -> BrandUtil.goHuaweiSetting(this.context)
              BrandUtil.isXiaomi() -> BrandUtil.toXiaomiSetting(this.context)
              BrandUtil.isMeizu() -> BrandUtil.toMeizuSetting(this.context)
              BrandUtil.isOppo() -> BrandUtil.toOppoSetting(this.context)
              BrandUtil.isSamsung() -> BrandUtil.toSamsungSetting(this.context)
              BrandUtil.isVivo() -> BrandUtil.toVivoSetting(this.context)
          }
        }
    }

}