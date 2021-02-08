package com.example.infosysdemo.ui.deshbord

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.infosysdemo.R
import com.example.infosysdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewMode: MainActivityViewMode
    lateinit var databinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
       viewMode= ViewModelProvider(this).get(MainActivityViewMode::class.java)
       initRecycleview()

        //call method for fetch data from server
        viewMode.getListOfData()
    }

    private fun initRecycleview() {

        databinding.recyclerRow.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        databinding.recyclerRow.addItemDecoration(
            DividerItemDecoration(
                databinding.recyclerRow.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }


}