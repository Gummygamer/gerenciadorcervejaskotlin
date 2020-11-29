package com.example.kotlinbeersearch

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BeerListAdapter(private val myDataset: List<Beer>, val pageSize: Int):
    RecyclerView.Adapter<BeerListAdapter.BeerViewHolder>() {

    var page = 0

    class BeerViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): BeerListAdapter.BeerViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.beer_text_view, parent, false) as TextView

        return BeerViewHolder(textView)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        val currentBeer = myDataset[position + page*pageSize]
        holder.textView.text = """Name: ${currentBeer.name} Tagline: ${currentBeer.tagline} Description: ${currentBeer.description}"""
    }

    override fun getItemCount() = myDataset.size

    fun nextPage(){
        page++
    }
}