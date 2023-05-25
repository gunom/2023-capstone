package com.roomeasy.capstoneproject.controller

import com.roomeasy.capstoneproject.controller.dto.ChatMessageDto
import com.roomeasy.capstoneproject.facade.ChatFacade
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.security.Principal

@Controller
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class ChatController(
    private val chatFacade: ChatFacade,
    private val messagingTemplate: SimpMessageSendingOperations,
) {

    @MessageMapping("/chat/msg")
    fun sendMessage(
        @Payload chatMessage: ChatMessageDto,
        principal: Principal,
    ) {
        val savedMessage = chatFacade.saveMessage(chatMessage.chatRoomId, chatMessage.content, principal, chatMessage.accessToken)
        messagingTemplate.convertAndSend("/sub/channel/${chatMessage.chatRoomId}", savedMessage)
    }
}