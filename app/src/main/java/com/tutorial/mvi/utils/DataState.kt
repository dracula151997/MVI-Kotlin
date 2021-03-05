package com.tutorial.mvi.utils

data class DataState<T>(
    val message: Event<String>? = null,
    val loading: Boolean = false,
    val data: Event<T>? = null
) {

    companion object {
        fun <T> error(message: String): DataState<T> {
            return DataState(
                message = Event(message),
                loading = false,
                data = null
            )
        }

        fun <T> success(message: String? = null, data: T? = null): DataState<T> {
            return DataState(
                message = Event.messageEvent(message),
                loading = false,
                data = Event.dataEvent(data)
            )
        }

        fun <T> loading(isLoading: Boolean): DataState<T> {
            return DataState(
                message = null,
                loading = isLoading,
                data = null
            )
        }
    }

    override fun toString(): String {
        return "DataState(message=$message, loading=$loading, data=$data)"
    }


}