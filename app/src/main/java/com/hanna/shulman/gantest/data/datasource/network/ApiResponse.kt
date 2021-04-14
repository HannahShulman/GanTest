package com.hanna.shulman.gantest.data.datasource.network

import retrofit2.Response

/**
 * Common class used by API responses.
 * Based on the class provided by android architecture-components sample.
 * @param <T> the type of the response object
</T> */
@Suppress("unused") // T is used in extending classes
sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(error.message ?: "unknown error")
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                //since I have no contract with BE, I may get a response with no body,
                // although the response is successful.
                body?.let {
                    ApiSuccessResponse(it)
                } ?: ApiEmptyResponse()
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) response.message() else msg

                ApiErrorResponse(errorMsg ?: "unknown error")
            }
        }
    }
}

//response types
class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()