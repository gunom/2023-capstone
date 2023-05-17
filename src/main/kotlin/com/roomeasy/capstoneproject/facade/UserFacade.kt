package com.roomeasy.capstoneproject.facade

import com.roomeasy.capstoneproject.service.user.AuthService
import com.roomeasy.capstoneproject.service.user.UserService
import com.roomeasy.capstoneproject.service.dto.LoginResponseDto
import org.springframework.stereotype.Service

@Service
class UserFacade(
    private val authService: AuthService,
    private val userService: UserService,
){
    fun loginWithToken(provider: String, token: String): LoginResponseDto{
        return authService.loginWithToken(provider, token)
    }

    fun registerInformation(name: String, registerNumber: String?) {
        val userId = authService.getUserId()
        val user = userService.getUserById(userId)
        userService.registerInformation(user, name, registerNumber)
    }

    fun refreshToken(refreshToken: String): LoginResponseDto {
        return authService.refreshWithToken(refreshToken).let {
            LoginResponseDto(
                it.first,
                it.second
            )
        }
    }
}