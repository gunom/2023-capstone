package com.roomeasy.capstoneproject.service.user

import com.roomeasy.capstoneproject.service.dto.LoginResponseDto
import javax.transaction.Transactional

interface AuthService {
    fun getUserId(): Long

    @Transactional
    fun loginWithToken(providerName: String, userToken: String): LoginResponseDto

    @Transactional
    fun refreshWithToken(userToken: String): Pair<String, String>
}