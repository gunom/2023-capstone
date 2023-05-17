package com.roomeasy.capstoneproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.socket.config.annotation.EnableWebSocket

@SpringBootApplication
@EnableFeignClients
@EnableWebSocket
class CapstoneProjectApplication

fun main(args: Array<String>) {
    runApplication<CapstoneProjectApplication>(*args)
}
