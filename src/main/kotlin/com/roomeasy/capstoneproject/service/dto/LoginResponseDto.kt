package com.roomeasy.capstoneproject.service.dto

data class LoginResponseDto(
    val accessToken: String,
    val refreshToken: String,
)