package com.music.search.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.music.search.R
import com.music.search.models.Track
import com.music.search.utils.ImageLoader.loadImage
import kotlinx.android.synthetic.main.track_item.view.*

open class TopTracksAdapter(
    var mDataset: MutableList<Track>,
    var mContext: Context,
    var mOnItemClickListener: View.OnClickListener?
) : RecyclerView.Adapter<TopTracksAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.track_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mDataset[position]
        holder.trackImageView.let {
            loadImage(
                mContext,
                item.imageUrl,
                R.drawable.ic_music,
                it
            )
        }
        holder.nameTextView.text = item.name
        holder.artistTextView.text = item.artist
        holder.playCountTextView.text = item.playcount
        holder.durationTextView.text = ""
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    fun setDataset(items: MutableList<Track>) {
        mDataset = items
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int): Track {
        return mDataset[position]
    }

    fun clearDataset() {
        mDataset.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { view ->
                onTrackClicked(view)
            }
        }

        var trackImageView: ImageView = itemView.icon_track

        var nameTextView: TextView = itemView.txt_track_name

        var playCountTextView: TextView = itemView.txt_plays

        var artistTextView: TextView = itemView.txt_track_artist

        var durationTextView: TextView = itemView.txt_duration

        private fun onTrackClicked(view: View) {
            mOnItemClickListener?.onClick(view)
        }
    }
}