package com.example.kapilesh.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kapilesh.retrofitdemo.network.RetroInstance
import com.example.kapilesh.retrofitdemo.network.RetroService
import com.example.kapilesh.retrofitdemo.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        createData()
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter


        }
    }

    fun createData(){
        val viewModel = MainActivityViewModel()
        viewModel.getRecyclerListDataObserver().observe(this, Observer<RecyclerList> {
            if (it != null) {
                recyclerViewAdapter.setListData(it.items)
                recyclerViewAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error getting data from api", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }
}