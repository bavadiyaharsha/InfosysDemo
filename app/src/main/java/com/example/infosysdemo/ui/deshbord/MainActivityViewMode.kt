package com.example.infosysdemo.ui.deshbord

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.infosysdemo.InfosysApplication
import com.example.infosysdemo.model.AppListResponse
import com.example.infosysdemo.model.Row
import com.example.infosysdemo.webservicecall.`interface`.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewMode : ViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    var errormsg = MutableLiveData<String>()
    //set list of row data
    var repolist = MutableLiveData<List<Row>>()
    var title = MutableLiveData<String>()


    //web service call
    fun getListOfData() {
        isLoading.value = true

        val userDataService = InfosysApplication.instance?.requestQueue?.create(
            ApiInterface::class.java
        )

        val call: Call<AppListResponse> = userDataService!!.getList()
        call.enqueue(object : Callback<AppListResponse> {
            override fun onResponse(
                call: Call<AppListResponse?>?,
                response: Response<AppListResponse?>
            ) {
                try {
                    if (response.isSuccessful) {
                        val listResponse = response.body()
                        if (listResponse != null) {
                            repolist.value = listResponse.rows
                            title.value=listResponse.title
                        }

                    } else {
                        val errorbody = response.errorBody()

                    }
                    isLoading.value = false
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<AppListResponse>, t: Throwable) {
                errormsg.value = t.message
                isLoading.value = false
            }
        })
    }
}