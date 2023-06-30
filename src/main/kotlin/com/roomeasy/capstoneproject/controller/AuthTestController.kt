package com.roomeasy.capstoneproject.controller

import com.roomeasy.capstoneproject.common.auth.JwtTokenProvider
import com.roomeasy.capstoneproject.common.enums.JWTExceptionCode
import com.roomeasy.capstoneproject.controller.dto.AuthTestRequestDto
import com.roomeasy.capstoneproject.controller.dto.LoginResponse
import com.roomeasy.capstoneproject.controller.dto.RefreshTestRequestDto
import com.roomeasy.capstoneproject.repository.user.UserRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.MalformedJwtException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.SignatureException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/test/auth")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class AuthTestController(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userRepository: UserRepository,
) {
    @PostMapping("/login")
    fun test(@RequestBody requestBody: AuthTestRequestDto): ResponseEntity<LoginResponse> {
        val token = jwtTokenProvider.generateTokenForTest(
            requestBody.accessTokenExpiredTime,
            requestBody.refreshTokenExpiredTime
        )
        return ResponseEntity.ok().body(
            LoginResponse(
                HttpStatus.OK.value(), true, "로그인 성공", token.first, token.second
            )
        )
    }

    @PostMapping("/refresh")
    fun refreshTest(
        @RequestBody requestBody: RefreshTestRequestDto,
        request: HttpServletRequest,
        response: HttpServletResponse,
    ): ResponseEntity<LoginResponse> {
            jwtTokenProvider.validateRefreshToken(requestBody.refreshToken)
        val claims: Claims =
            jwtTokenProvider.getUserIdFromRefreshToken(requestBody.refreshToken).getOrThrow()
        val userId: Long = claims["id"]?.let {
            userRepository.findById((it as Int).toLong()).get().id
        } ?: throw JwtException("Failed to retrieve user details from JWT token")

        val token = jwtTokenProvider.generateTokenForTest(
            requestBody.accessTokenExpiredTime,
            requestBody.refreshTokenExpiredTime
        )
        return ResponseEntity.ok().body(
            LoginResponse(
                HttpStatus.OK.value(), true, "토큰 재발급 성공", token.first, token.second
            )
        )
    }

    @ExceptionHandler(ExpiredJwtException::class)
    fun handleExpiredJwtException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        ex: ExpiredJwtException
    ): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Expired JWT token")
    }

    @ExceptionHandler(MalformedJwtException::class)
    fun handleMalformedJwtException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        ex: MalformedJwtException
    ): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Malformed JWT token")
    }

    @ExceptionHandler(SignatureException::class)
    fun handleSignatureException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        ex: SignatureException
    ): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid signature in JWT token")
    }

    @ExceptionHandler(JwtException::class)
    fun handleJwtException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        ex: JwtException
    ): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unknown JWT token error")
    }
}