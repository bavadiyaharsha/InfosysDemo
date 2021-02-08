package com.example.infosysdemo.ui.deshbord

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.infosysdemo.R
import com.example.infosysdemo.databinding.ActivityMainBinding
import com.example.infosysdemo.model.Row
import com.example.infosysdemo.ui.deshbord.adepter.ListOfRowAdepter

class MainActivity : AppCompatActivity() {
    val Tag = "MainActivity"
    private lateinit var viewMode: MainActivityViewMode
    lateinit var databinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewMode = ViewModelProvider(this).get(MainActivityViewMode::class.java)
        initRecycleview()
        try {
            //call method for fetch data from server
            viewMode.getListOfData()

            //set all observer

            viewMode.title.observe(this, Observer {
                supportActionBar?.setTitle("" + it)
            })

            viewMode.isLoading.observe(this, Observer {
                if(it==false){
                    databinding.process.visibility=View.GONE
                    databinding.recyclerRow.visibility=View.VISIBLE

                }
            })

            viewMode.repolist.observe(this, Observer {
                if(!it.isNullOrEmpty()){
                    //set adepter
                    databinding.recyclerRow.adapter =
                        ListOfRowAdepter(
                            it,
                            { selsectItem: Row -> clickOnListItem(selsectItem) })
                }

            })

        } catch (e: Exception) {
            Log.e(Tag, "Error:" + e.message)
        }
    }

    //user when click on specific item
    private fun clickOnListItem(selsectItem: Row) {

    }

    private fun initRecycleview() {

        databinding.recyclerRow.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }


}