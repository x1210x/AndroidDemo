package ssun.pe.kr.androiddemo.domain

import io.reactivex.Single

abstract class SingleUseCase<in P, R> : UseCase<P, Single<R>>()