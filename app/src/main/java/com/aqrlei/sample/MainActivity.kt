package com.aqrlei.sample

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.aqrlei.sample.base.BaseActivity
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_activity_content.*

class MainActivity : BaseActivity() {

    private lateinit var fragPageAdapter: FragPageAdapter

    override fun getLayoutId(): Int = R.layout.main_activity


    override fun onDestroy() {
        super.onDestroy()
        Log.d("AqrLei","test")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        drawerLayout.run {
            val toggle = ActionBarDrawerToggle(
                this@MainActivity,
                this,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            addDrawerListener(toggle)
            toggle.syncState()
        }

        fragPageAdapter = CommonHelper.getFragPageAdapter(supportFragmentManager)
        viewPager.adapter = fragPageAdapter
        tab.setupWithViewPager(viewPager, true)

        navView.setNavigationItemSelectedListener {
            true
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }


}
