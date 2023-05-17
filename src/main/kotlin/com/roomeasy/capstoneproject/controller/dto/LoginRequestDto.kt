package com.roomeasy.capstoneproject.controller.dto

data class LoginRequestDto(
        val provider: String,
        val ssoToken: String,
)
