package com.tutorial.mvi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.tutorial.mvi.utils.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class NetworkBoundResource<ResponseObject, ViewStateType> {


    protected val result = MediatorLiveData<DataState<ViewStateType>>()

    init {
        result.value = DataState.loading(true)

        GlobalScope.launch(IO) {
            delay(1000)
            withContext(Main) {
                val apiResponse = createCall()
                result.addSource(apiResponse) { response ->
                    result.removeSource(apiResponse)
                    handleNetworkCall(response)
                }
            }
        }
    }

    private fun handleNetworkCall(response: GenericApiResponse<ResponseObject>) {
        when (response) {
            is ApiSuccessResponse -> {
                handleApiSuccessResponse(response)
            }

            is ApiErrorResponse -> {
                println("DEBUG: NetworkBoundResource ${response.error}")
                onReturnError(response.error)
            }

            is ApiEmptyResponse -> {
                println("DEBUG: NetworkBoundResource: Request returned NOTHING!")
                onReturnError("HTTP 204. Returned NOTHING")
            }
        }
    }

    abstract fun handleApiSuccessResponse(response: ApiSuccessResponse<ResponseObject>)

    private fun onReturnError(message: String) {
        result.value = DataState.error(message)
    }

    fun asLiveData() = result as LiveData<DataState<ViewStateType>>

    abstract fun createCall(): LiveData<GenericApiResponse<ResponseObject>>

}
