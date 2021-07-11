package com.music.search.ui.tracks

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.music.search.R
import com.music.search.adapters.TopTracksAdapter
import com.music.search.models.Track
import com.music.search.ui.BaseFragment
import com.music.search.ui.MusicDetailsActivity
import com.music.search.ui.tracks.di.DaggerTracksComponent
import com.music.search.ui.tracks.di.TracksModule
import com.music.search.utils.Constants
import kotlinx.android.synthetic.main.fragment_tracks.*
import javax.inject.Inject

class TracksFragment : BaseFragment(), TracksView {
    private var mListener: OnFragmentInteractionListener? = null

    @Inject
    lateinit var mPresenter: TracksPresenter

    private var mAdapter: TopTracksAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerTracksComponent.builder().tracksModule(TracksModule(this)).build()
            .inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPresenter.getTracks(Constants.DEFAULT_LASTFM_USER, Constants.API_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tracks, container, false)
        return view
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

    override fun showError() {
        Toast.makeText(context, R.string.error_message, Toast.LENGTH_SHORT).show()
    }

    override fun updateData(tracks: MutableList<Track>) {
        if (mAdapter == null) {
            mAdapter = context?.let { TopTracksAdapter(tracks, it, onTrackClickedListener) }
            val linearLayoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            list_tracks?.layoutManager = linearLayoutManager
            list_tracks?.adapter = mAdapter
            mAdapter?.notifyDataSetChanged()
        } else {
            mAdapter?.setDataset(tracks)
        }
    }

    override fun showEmpty() {
        empty_layout?.visibility = View.VISIBLE
    }

    override fun hidEmpty() {
        empty_layout?.visibility = View.GONE
    }

    override fun searchUserName(userName: String) {
        mAdapter?.clearDataset()
        mPresenter.getTracks(userName, Constants.API_KEY)
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    interface OnFragmentInteractionListener {
        fun onTrackClicked(track: Track)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }

    private var onTrackClickedListener = View.OnClickListener { view ->
        if (mListener != null) {
            val position = list_tracks?.getChildAdapterPosition(view)
            val track = position?.let { mAdapter?.getItemAt(it) }
            val intent = Intent(context, MusicDetailsActivity::class.java)
            intent.putExtra("name", track?.name)
            intent.putExtra("image", track?.imageUrl)
            intent.putExtra("playCount", track?.playcount)
            intent.putExtra("artist", track?.artist)
            intent.putExtra("duration", "")
            startActivity(intent)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        fun newInstance(): TracksFragment {
            return TracksFragment()
        }
    }
}