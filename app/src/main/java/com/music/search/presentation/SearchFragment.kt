package com.music.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.music.search.databinding.FragmentSearchBinding
import org.koin.android.viewmodel.ext.android.viewModel

private const val TAG = "SearchFragment"

open class SearchFragment : Fragment(), View.OnClickListener {

    private val viewModel: SearchViewModel by viewModel()
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
        bindView()
    }

    private fun bindView() {
        binding.searchViewModel = viewModel
        binding.lifecycleOwner = this
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}