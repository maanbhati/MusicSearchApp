package com.music.search.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.music.search.databinding.FragmentSearchBinding

private const val TAG = "SearchFragment"

open class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
    }

    private fun initViews() {
        binding.searchAlbumButton.setOnClickListener {
            val searchKeyword = binding.searchBar.text.toString()
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToSearchResultFragment()
                    .setSearchKeyword(searchKeyword)
            )
        }
    }
}