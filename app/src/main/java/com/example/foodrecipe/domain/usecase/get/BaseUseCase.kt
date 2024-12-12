package com.example.foodrecipe.domain.usecase.get

import com.bumptech.glide.load.HttpException
import com.example.foodrecipe.common.Resource
import com.example.foodrecipe.domain.repository.MealsRepository
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseUseCase<in P, out R>(private val repository: MealsRepository) {

    operator fun invoke(params: P): Flow<Resource<@UnsafeVariance R>> = flow {
        try {
            emit(Resource.Loading())
            val result = execute(params)
            emit(Resource.Success(result))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    protected abstract suspend fun execute(params: P): R

}