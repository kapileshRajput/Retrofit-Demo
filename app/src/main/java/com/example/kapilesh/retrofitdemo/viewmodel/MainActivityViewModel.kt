package com.example.kapilesh.retrofitdemo.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kapilesh.retrofitdemo.RecyclerList
import com.example.kapilesh.retrofitdemo.network.RetroInstance
import com.example.kapilesh.retrofitdemo.network.RetroService
import retrofit2.Call
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    lateinit var recyclerListData: MutableLiveData<RecyclerList>

    init {
        recyclerListData = MutableLiveData()
    }

    fun getRecyclerListDataObserver(): MutableLiveData<RecyclerList>{
        return recyclerListData
    }

    fun makeApiCall(){

        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromAPI("newyork")
        call.enqueue(object : retrofit2.Callback<RecyclerList> {
            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if (response.isSuccessful) {
//                    recyclerViewAdapter.setListData(response.body()?.items!!)
//                    recyclerViewAdapter.notifyDataSetChanged()
                    recyclerListData.postValue(response.body())
                }else{
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "something went wrong.", Toast.LENGTH_SHORT).show()
            }

        })
    }
}