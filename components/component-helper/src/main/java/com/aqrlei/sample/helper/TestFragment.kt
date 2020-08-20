package com.aqrlei.sample.helper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aqrlei.litelog.LogHelper
import com.aqrlei.sample.base.BaseFragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.frag_test.*

/**
 * created by AqrLei on 2020/3/16
 */
class TestFragment : BaseFragment() {


    private val testData = arrayOf(1, 2, 3, 4, 5)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = TestAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.addItemDecoration(TestItemDecoration { position: Int ->

            val element = testData.firstOrNull { it >= 3 }
            if (element != null) {
                position == testData.indexOf(element)
            } else false
        })
        tvTestButton.setOnClickListener {
            showSnackBar()
        }

    }

    private fun showSnackBar(){
        Snackbar.make(llTestFrag,"这是一个测试",Snackbar.LENGTH_INDEFINITE)
            .addCallback(object: Snackbar.Callback(){
                override fun onShown(sb: Snackbar?) {
                    super.onShown(sb)
                    LogHelper.d("Snackbar onShow")
                }

                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    super.onDismissed(transientBottomBar, event)
                    LogHelper.d("Snackbar onDismiss")
                }
            })
            .setAction("Action") {
                LogHelper.d("Snackbar onAction")
            }
            .show()
    }

    inner class TestAdapter : RecyclerView.Adapter<TestHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestHolder {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.test_recycler_item, parent, false)
            return TestHolder(view)
        }

        override fun getItemCount(): Int = testData.size

        override fun onBindViewHolder(holder: TestHolder, position: Int) {
            holder.itemView.findViewById<TextView>(R.id.tvItem).text = testData[position].toString()
        }
    }

    class TestHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}