package com.music.search.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.music.search.databinding.FragmentSearchResultBinding
import com.music.search.ui.adapter.SearchResultAdapter
import com.music.search.ui.viewmodel.SearchResultViewModel
import com.music.search.utils.Resource
import com.music.search.utils.ResponseListener
import kotlinx.android.synthetic.main.fragment_search_result.*

//import org.koin.android.viewmodel.ext.android.viewModel

private const val TAG = "SearchResultFragment"

class SearchResultFragment : Fragment(), ResponseListener {

    private lateinit var searchResultAdapter: SearchResultAdapter
    private val viewModel: SearchResultViewModel by viewModels()
    private lateinit var binding: FragmentSearchResultBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchResultBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindView()
        initViews()
        observeData()
    }

    private fun initViews() {
        searchResultAdapter = SearchResultAdapter(arrayListOf())
        binding.listSearchResults.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.listSearchResults.addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
            adapter = searchResultAdapter
        }
    }

    private fun observeData() {
        viewModel.totalResult.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    searchResultAdapter.updateBreed(result.result)
                    hideProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                }
            }
        })
    }

    private fun bindView() {
        binding.searchResultViewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.responseListener = this
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onLoading() {
        showProgressBar()
    }

    override fun onSuccess() {
        hideProgressBar()
    }

    override fun onFailure(errorMessage: String) {
        hideProgressBar()
    }
}