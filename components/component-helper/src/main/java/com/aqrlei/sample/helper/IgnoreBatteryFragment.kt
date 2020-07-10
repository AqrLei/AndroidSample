package com.aqrlei.sample.helper

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.aqrlei.helper.AppHelper
import com.aqrlei.helper.BrandHelper
import com.aqrlei.sample.base.BaseFragment
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
               if ( !AppHelper.isIgnoringBatteryOptimizations(this.context)){
                   AppHelper.requestIgnoreBatteryOptimizations(this.context)
               }else {
                   Toast.makeText(this.context,"已在电池优化白名单中",Toast.LENGTH_SHORT).show()
               }
            }
        }

        tvRequestBrand.setOnClickListener {
          when{
              BrandHelper.isHuawei() -> BrandHelper.goHuaweiSetting(this.context)
              BrandHelper.isXiaomi() -> BrandHelper.toXiaomiSetting(this.context)
              BrandHelper.isMeizu() -> BrandHelper.toMeizuSetting(this.context)
              BrandHelper.isOppo() -> BrandHelper.toOppoSetting(this.context)
              BrandHelper.isSamsung() -> BrandHelper.toSamsungSetting(this.context)
              BrandHelper.isVivo() -> BrandHelper.toVivoSetting(this.context)
          }
        }
    }

}