package com.music.search.interactors

import com.music.search.domain.data.SearchResult
import com.music.search.domain.executer.PostExecutionThread
import com.music.search.domain.executer.ThreadExecutor
import com.music.search.domain.repository.ISearchRepository
import io.reactivex.Single

class GetSearchResult(
    private val breedsRepository: ISearchRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<SearchResult, Int, String>(threadExecutor, postExecutionThread) {
    public override fun buildUseCaseObservable(params: Int?, arg2: String?): Single<SearchResult> {
        return breedsRepository.getSearchResult()
    }
}