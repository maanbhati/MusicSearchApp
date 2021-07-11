package com.music.search.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.music.search.R
import com.music.search.models.Artist
import com.music.search.utils.ImageLoader.loadImage
import kotlinx.android.synthetic.main.item_artist.view.*

open class ArtistsAdapter(
    private var mDataset: MutableList<Artist>,
    private val mContext: Context,
    private val mOnClickListener: View.OnClickListener?
) : RecyclerView.Adapter<ArtistsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_artist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mDataset[position]
        loadImage(mContext, item.imageUrl, R.drawable.ic_music, holder.artistImageView)
        holder.artistTextView.text = item.name
        holder.numberOfPlaysTextView.text = item.playcount
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    fun setDataset(items: MutableList<Artist>) {
        mDataset = items
        notifyDataSetChanged()
    }

    fun getItemByPosition(position: Int): Artist {
        return mDataset[position]
    }

    fun clearDataset() {
        mDataset.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { view ->
                onItemClicked(view)
            }
        }

        var artistImageView: ImageView = itemView.img_artist

        var artistTextView: TextView = itemView.txt_artist_name

        var numberOfPlaysTextView: TextView = itemView.txt_plays

        private fun onItemClicked(view: View) {
            mOnClickListener?.onClick(view)
        }
    }

}