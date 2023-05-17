package com.roomeasy.capstoneproject.common.config

import com.roomeasy.capstoneproject.common.auth.JwtTokenProvider
import com.roomeasy.capstoneproject.domain.user.SecurityUser
import com.roomeasy.capstoneproject.service.user.CustomUserDetailsService
import io.jsonwebtoken.JwtException
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.ChannelInterceptor
import org.springframework.messaging.support.MessageHeaderAccessor
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import java.security.Principal
import javax.security.auth.message.AuthException

@Configuration
class WebSocketInterceptor(
    private val jwtProvider: JwtTokenProvider,
    private val customUserDetailsService: CustomUserDetailsService,
    ) : ChannelInterceptor {
    object SecurityContextHolder {
        private val contextHolder = ThreadLocal<Authentication>()

        fun setAuthentication(authentication: Authentication?) {
            contextHolder.set(authentication)
        }

        fun getAuthentication(): Authentication? {
            return contextHolder.get()
        }

        fun clear() {
            contextHolder.remove()
        }
    }
    override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>? {
        val accessor: StompHeaderAccessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor::class.java)!!
        println(accessor.command)
        println(StompCommand.SEND)
        if (StompCommand.CONNECT == accessor.command || StompCommand.SEND == accessor.command) {
            val jwtToken = accessor.getNativeHeader("Authorization")?.get(0)
            print(jwtToken)
            val token = jwtToken?.substring(7)
            if (!token.isNullOrEmpty()) {
                if(!jwtProvider.validateToken(token)) {
                    throw JwtException("Failed to validate token")
                }
                val claims = jwtProvider.getUserIdFromToken(token).getOrThrow()
                val userDetails: SecurityUser = claims["id"]?.let {
                    customUserDetailsService.loadUserByUserId(
                        it.toString().toLong()
                    )
                } ?: throw JwtException("Failed to retrieve user details from JWT token")
                val authentication =
                    UsernamePasswordAuthenticationToken(
                        userDetails.username,
                        token,
                        userDetails.authorities
                    )
                authentication.details = userDetails
                SecurityContextHolder.setAuthentication(authentication)
                accessor.user = Principal {
                    userDetails.getUserId().toString()
                }
            } else {
                throw AuthException("Missing token")
            }
        }
        return message
    }

    override fun afterSendCompletion(
        message: Message<*>,
        channel: MessageChannel,
        sent: Boolean,
        exception: Exception?
    ) {
        SecurityContextHolder.clear()
        super.afterSendCompletion(message, channel, sent, exception)
    }
}