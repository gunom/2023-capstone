package com.roomeasy.capstoneproject.service.user;


import com.roomeasy.capstoneproject.common.auth.JwtTokenProvider
import com.roomeasy.capstoneproject.domain.user.SecurityUser
import com.roomeasy.capstoneproject.domain.user.User
import com.roomeasy.capstoneproject.repository.user.UserRepository
import com.roomeasy.capstoneproject.service.dto.KakaoProfile
import com.roomeasy.capstoneproject.service.dto.LoginResponseDto
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
class AuthServiceImpl(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userRepository: UserRepository,
    private val kakaoClient: KakaoClient,
) : AuthService {
    override fun getUserId(): Long {
        return (SecurityContextHolder.getContext().authentication.details as SecurityUser).getUserId()
    }

    override fun refreshWithToken(userToken: String): Pair<String, String> {
        if(!jwtTokenProvider.validateRefreshToken(userToken)){
            throw JwtException("Invalid refresh token")
        }
        val claims: Claims = jwtTokenProvider.getUserIdFromToken(userToken).getOrThrow()
        val userId: Long = claims["id"]?.let {
            userRepository.findById(it as Long).get().id
        } ?: throw JwtException("Failed to retrieve user details from JWT token")

        return jwtTokenProvider.generateTokenByUserId(userId)
    }

    @Transactional
    override fun loginWithToken(providerName: String, userToken: String): LoginResponseDto {

//        val user: User = getUserProfileByToken(providerName, userToken);
        val user = userRepository.findById(1).get()
        val loginResponse: LoginResponseDto = jwtTokenProvider.generateTokenByUserId(user.id).let {
            LoginResponseDto(it.first, it.second)
        }

        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(
            SecurityUser(user),
            loginResponse.accessToken,
            SecurityUser(user).authorities
        )

        return loginResponse
    }


    private fun getKakaoUserInfoByToken(userToken: String): KakaoProfile {
        val kakaoProfile: KakaoProfile = kakaoClient.getUserInfo("Bearer $userToken")
        return kakaoProfile

    }

    private fun getUserProfileByToken(providerName: String, userToken: String): User {
        var ssoId = ""
        var email = ""
        when (providerName) {
            "kakao" -> {
                val userInfo = getKakaoUserInfoByToken(userToken)
                if (userInfo.id != null && userInfo.kakao_account?.email != null) {
                    ssoId = userInfo.id as String
                    email = userInfo.kakao_account?.email as String
                }
            }
            else -> throw RuntimeException("Not supported provider")
        }

        val user = userRepository.findBySsoId(ssoId)
        return if (user == null) {
            val newUser = User(
                    email = email,
                    name = "",
                    providerName = providerName,
                    ssoId = ssoId,
                    type = "USER",
                    isVerified = true,
            )
            userRepository.save(newUser)
        } else {
            user
        }
    }
}
