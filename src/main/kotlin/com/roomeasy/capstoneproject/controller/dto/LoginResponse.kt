package com.roomeasy.capstoneproject.controller.dto

data class LoginResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val accessToken: String,
    val refreshToken: String,
)
