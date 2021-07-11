package com.music.search.ui.artists

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.music.search.R
import com.music.search.adapters.ArtistsAdapter
import com.music.search.models.Artist
import com.music.search.ui.BaseFragment
import com.music.search.ui.MusicDetailsActivity
import com.music.search.ui.artists.di.ArtistsModule
import com.music.search.ui.artists.di.DaggerArtistsComponent
import com.music.search.utils.Constants
import kotlinx.android.synthetic.main.fragment_artists.*
import javax.inject.Inject

class ArtistsFragment : BaseFragment(), ArtistsView {

    var mListener: OnFragmentInteractionListener? = null


    @Inject
    lateinit var mPresenter: ArtistsPresenter
    var mAdapter: ArtistsAdapter? = null
    override fun searchUserName(userName: String) {
        mAdapter?.clearDataset()
        mPresenter.getArtists(userName, Constants.API_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_artists, container, false)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerArtistsComponent.builder().artistsModule(ArtistsModule(this)).build()
            .inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPresenter.getArtists(Constants.DEFAULT_LASTFM_USER, Constants.API_KEY)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = if (context is OnFragmentInteractionListener) {
            context
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun showProgress() {
        progress_bar?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar?.visibility = View.GONE
    }

    override fun updateData(topArtists: MutableList<Artist>) {
        if (mAdapter == null) {
            mAdapter = context?.let { ArtistsAdapter(topArtists, it, onArtistclickedListener) }
            val linearLayoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rclr_artists?.layoutManager = linearLayoutManager
            rclr_artists?.adapter = mAdapter
            mAdapter?.notifyDataSetChanged()
        } else {
            mAdapter?.setDataset(topArtists)
        }
    }

    var onArtistclickedListener = View.OnClickListener { view ->
        val position = rclr_artists?.getChildLayoutPosition(view)
        val artist = mAdapter?.getItemByPosition(position!!)
        val intent = Intent(context, MusicDetailsActivity::class.java)
        intent.putExtra("name", artist?.name)
        intent.putExtra("image", artist?.imageUrl)
        intent.putExtra("playCount", artist?.playcount)
        intent.putExtra("artist", "")
        intent.putExtra("duration", "")
        startActivity(intent)
    }

    override fun showError() {
        Toast.makeText(context, R.string.error_message, Toast.LENGTH_SHORT).show()
    }

    override fun showEmpty() {
        empty_layout?.visibility = View.VISIBLE
    }

    override fun hidEmpty() {
        empty_layout?.visibility = View.GONE
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    interface OnFragmentInteractionListener {
        fun onArtistClicked(artist: Artist)
    }

    companion object {
        fun newInstance(): ArtistsFragment {
            return ArtistsFragment()
        }
    }
}