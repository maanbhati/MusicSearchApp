package com.music.search.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.music.search.R

class SearchResultAdapter(private val searchResult: ArrayList<String>) :
    RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

    fun updateBreed(newResult: List<String>) {
        searchResult.clear()
        searchResult.addAll(newResult)
        notifyDataSetChanged()
    }

    inner class SearchResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindResult(albumName: String) = with(itemView) {
            //
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout_search_result, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val albumName = searchResult[position]
        holder.bindResult(albumName)
        holder.itemView.setOnClickListener {
            // clickListener.onClick(albumName)
        }
    }

    override fun getItemCount(): Int = searchResult.size
}