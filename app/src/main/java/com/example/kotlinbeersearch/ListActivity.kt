package com.example.kotlinbeersearch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    var isLoading = false
    var pageSize = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        viewManager = LinearLayoutManager(this)

        val call = RetrofitInitializer().BeerService().list()
        call.enqueue(object: Callback<List<Beer>?> {
            override fun onResponse(call: Call<List<Beer>?>?,
                                    response: Response<List<Beer>?>?) {

                response?.body()?.let {
                    val beers: List<Beer> = it
                    configureList(beers)
                }
            }

            override fun onFailure(call: Call<List<Beer>?>?,
                                   t: Throwable?) {
                Log.e("onFailure error", t?.message.toString())
            }
        })

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

//                if (dy > 0) {
                val visibleItemCount = viewManager.childCount
                pageSize = visibleItemCount
                val pastVisibleItem = (viewManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                val total = viewAdapter.itemCount

                if (!isLoading) {

                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        (viewAdapter as BeerListAdapter).nextPage()
                        isLoading = true
                    }

                }
                else {
                    isLoading = false
                }
//                }

                super.onScrolled(recyclerView, dx, dy)
            }
        })

        RetrofitInitializer()


    }


    private fun configureList(beers: List<Beer>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {

            setHasFixedSize(true)

            viewAdapter = BeerListAdapter(beers, pageSize)

            layoutManager = viewManager

            adapter = viewAdapter

        }
        recyclerView.adapter = viewAdapter
        val layoutManager = StaggeredGridLayoutManager(
            2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

}