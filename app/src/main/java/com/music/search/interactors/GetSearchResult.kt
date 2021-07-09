package com.music.search.interactors

import io.reactivex.Single

class GetSearchResult(
    private val breedsRepository: IBreedsRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<DogBreed, Int, String>(threadExecutor, postExecutionThread) {
    public override fun buildUseCaseObservable(params: Int?, arg2: String?): Single<DogBreed> {
        return breedsRepository.getBreeds()
    }
}