package com.music.search.ui.album

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.music.search.R
import com.music.search.adapters.AlbumsAdapter
import com.music.search.models.Album
import com.music.search.ui.BaseFragment
import com.music.search.ui.MusicDetailsActivity
import com.music.search.ui.album.di.AlbumsModule
import com.music.search.ui.album.di.DaggerAlbumsComponent
import com.music.search.utils.Constants
import kotlinx.android.synthetic.main.fragment_albums.*
import javax.inject.Inject

open class AlbumsFragment : BaseFragment(), AlbumsView {

    @Inject
    lateinit var mPresenter: AlbumsPresenter

    var mAdapter: AlbumsAdapter? = null
    private var mListener: OnFragmentInteractionListener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_albums, container, false)

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAlbumsComponent.builder().albumsModule(AlbumsModule(this)).build()
            .inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPresenter.getTopAlbums(Constants.DEFAULT_LASTFM_USER, Constants.API_KEY)
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

    override fun searchUserName(userName: String) {
        mAdapter?.clearDataset()
        mPresenter.getTopAlbums(userName, Constants.API_KEY)
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

    override fun updateData(topAlbums: MutableList<Album>) {
        if (mAdapter == null) {
            mAdapter = context?.let { AlbumsAdapter(topAlbums, it, mOnAlbumClickedListener) }
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            list_albums?.layoutManager = layoutManager
            list_albums?.adapter = mAdapter
            mAdapter?.notifyDataSetChanged()
        } else {
            mAdapter?.setDataset(topAlbums)
        }
    }

    override fun showEmpty() {
        empty_layout?.visibility = View.VISIBLE
    }

    override fun hidEmpty() {
        empty_layout?.visibility = View.GONE
    }

    var mOnAlbumClickedListener = View.OnClickListener { view ->
        if (mListener != null) {
            val position = list_albums?.getChildAdapterPosition(view)
            val album = position?.let { mAdapter?.getItemByPosition(it) }
            val intent = Intent(context, MusicDetailsActivity::class.java)
            intent.putExtra("name", album?.name)
            intent.putExtra("image", album?.imageUrl)
            intent.putExtra("playCount", "")
            intent.putExtra("artist", album?.artist)
            intent.putExtra("duration", "")
            startActivity(intent)
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    interface OnFragmentInteractionListener {
        fun onAlbumClicked(album: Album)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }

    companion object {
        fun newInstance(): AlbumsFragment {
            return AlbumsFragment()
        }
    }
}