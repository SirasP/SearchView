package com.example.rview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.widget.*


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.rview.retrofityrecycl2.PastModel
import com.example.rview.retrofityrecycl2.ApiService
import com.example.rview.retrofityrecycl2.PastAdapter

import com.example.rview.retrofityrecycl2.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.Locale.filter
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {


     var itemArrayList = ArrayList<PastModel>()

    //private lateinit var searchView: SearchView
    lateinit var recAdapter: PastAdapter
    val TAG = "my tag"
    var tags: String = "main course"
    //private lateinit var listener: RandomRecipeResponseListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val call = serviceGenerator.getPosts()
        recAdapter = PastAdapter(itemArrayList)

        val searchView = findViewById<SearchView>(R.id.searchView)

        itemArrayList = arrayListOf()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {


                if (query != null) {
                    tags = query
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {


                val searchList = ArrayList<PastModel>()

                if (newText != null) {
                    for (i in itemArrayList) {
                        if (i.direccion.lowercase(Locale.ROOT).contains(newText)) {
                            searchList.add(i)
                        }
                    }
                    if (searchList.isEmpty()) {
                        Toast.makeText(this@MainActivity, "No data", Toast.LENGTH_SHORT).show()
                    } else {

                        recAdapter.onApplySearch(searchList)
                    }
                }
                return true
            }

        })


        call.enqueue(object : Callback<MutableList<PastModel>> {
            override fun onResponse(
                call: Call<MutableList<PastModel>>, response: Response<MutableList<PastModel>>
            ) {

                if (response.isSuccessful) {
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = PastAdapter(response.body()!!)
                    }

                    //Log.e("OKKKKKK")
                }
            }

            override fun onFailure(call: Call<MutableList<PastModel>>, t: Throwable) {
                t.printStackTrace()
                Log.e("Error", t.message.toString())
            }

        })
    }


}

