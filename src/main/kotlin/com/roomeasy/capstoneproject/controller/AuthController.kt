package com.roomeasy.capstoneproject.controller

import com.roomeasy.capstoneproject.controller.dto.*
import com.roomeasy.capstoneproject.facade.UserFacade
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userFacade: UserFacade,
) {
    @PostMapping("/login")
    fun login(@RequestBody loginRequestDto: LoginRequestDto): ResponseEntity<LoginResponse> {
        val (accessToken, refreshToken) = userFacade.loginWithToken(loginRequestDto.provider, loginRequestDto.ssoToken)
        return ResponseEntity.ok().body(LoginResponse(HttpStatus.OK.value(),true,"로그인 성공", accessToken, refreshToken))
    }

    @PostMapping("/register")
    fun register(@RequestBody registerDto: RegisterDto): ResponseEntity<ResponseWithData<Nothing?>> {
        userFacade.registerInformation(registerDto.name, registerDto.registerNumber)
        return ResponseEntity.ok().body(ResponseWithData(HttpStatus.OK.value(),true,"회원가입 성공", null))
    }

    @PostMapping("/refresh")
    fun refresh(@RequestBody refreshRequestDto: RefreshRequestDto): ResponseEntity<LoginResponse> {
        val (accessToken, newRefreshToken) = userFacade.refreshToken(refreshRequestDto.refreshToken)
        return ResponseEntity.ok().body(LoginResponse(HttpStatus.OK.value(),true,"토큰 재발급 성공", accessToken, newRefreshToken))
    }
}