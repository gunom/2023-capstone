package com.roomeasy.capstoneproject.controller.dto

data class ResponseWithData<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T,
){
    companion object {
        fun <T> ok(statusCode: Int, message: String, data: T): ResponseWithData<T> {
            return ResponseWithData(
                status = statusCode,
                success = true,
                message = message,
                data = data,
            )
        }
    }
}

data class ResponseWithoutData(
    val status: Int,
    val success: Boolean,
    val message: String,
) {
    companion object {
        fun ok(statusCode: Int, message: String): ResponseWithoutData {
            return ResponseWithoutData(
                status = statusCode,
                success = true,
                message = message,
            )
        }
    }
}
