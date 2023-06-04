package com.roomeasy.capstoneproject.facade

import com.roomeasy.capstoneproject.domain.chat.ChatMessage
import com.roomeasy.capstoneproject.domain.chat.Reminder
import com.roomeasy.capstoneproject.service.chat.ChatMessageService
import com.roomeasy.capstoneproject.service.chat.ChatRoomService
import com.roomeasy.capstoneproject.service.chat.ChatRoomUserService
import com.roomeasy.capstoneproject.service.chat.ReminderService
import com.roomeasy.capstoneproject.service.dto.ChatMessageDto
import com.roomeasy.capstoneproject.service.user.AuthService
import com.roomeasy.capstoneproject.service.user.UserService
import com.roomeasy.capstoneproject.service.dto.ChatRoomDto
import org.springframework.stereotype.Service
import java.security.Principal
import java.time.LocalDateTime
import javax.persistence.EntityNotFoundException

@Service
class ChatFacade(
    private val authService: AuthService,
    private val chatRoomService: ChatRoomService,
    private val chatMessageService: ChatMessageService,
    private val chatRoomUserService: ChatRoomUserService,
    private val userService: UserService,
    private val reminderService: ReminderService,
) {
    fun saveMessage(chatRoomId: Long, content: String, principal: Principal, accessToken: String): ChatMessage {
        val userId = principal.name.toLong()
        val user = userService.getUserById(userId)
        val message = ChatMessage(
            userId = userId,
            accessToken = accessToken,
            sender = user.name,
            content = content,
            chatRoomId = chatRoomId,
            timestamp = LocalDateTime.now()
        )
        return chatMessageService.saveChatMessage(message)
    }

    fun getChatRoomMessages(chatRoomId: Long): List<ChatMessageDto> {
        val userId = authService.getUserId()
        return chatMessageService.getChatMessagesByChatRoomId(chatRoomId).map {
            ChatMessageDto(
                id = it.id,
                sender = it.sender,
                userId = it.userId,
                myMessage = it.userId == userId,
                content = it.content,
                timestamp = it.timestamp,
                chatRoomId = it.chatRoomId
            )
        }
    }

    fun getChatRoomsForUser(): MutableList<ChatRoomDto> {
        val userId = authService.getUserId()
        val chatRoomIds = chatRoomUserService.getChatRoomByUserId(userId).map {
            it.chatRoomId
        }

        val chatRoomUsers = chatRoomUserService.getChatRoomByRoomIds(chatRoomIds).filter {
            it.userId != userId
        }

        val brokers = userService.getUserByIds(chatRoomUsers.map { it.userId })
        val lastMessages = chatMessageService.getLastChatMessageByChatRoomIds(chatRoomIds)

        return chatRoomUsers.fold(mutableListOf()) {acc, chatRoomUser ->
            val lastMessage = lastMessages.find {
                it.chatRoomId == chatRoomUser.chatRoomId
            }

            val broker = brokers.find {
                it.id == chatRoomUser.userId
            } ?: throw EntityNotFoundException("Broker not found")

            val chatRoomDto = ChatRoomDto(
                id = chatRoomUser.chatRoomId,
                opponent = broker.name,
                lastMessage = lastMessage?.content ?: "",
                lastMessageTimestamp = chatRoomUser.createdAt
            )
            acc.add(chatRoomDto)
            acc
        }
    }

    fun createChatRoom(brokerId: Long): ChatRoomDto {
        val userId = authService.getUserId()
        val chatRoom = chatRoomService.createChatRoom()
        chatRoomUserService.addChatRoomUser(chatRoom.id, userId)
        chatRoomUserService.addChatRoomUser(chatRoom.id, brokerId)
        return ChatRoomDto(
            id = chatRoom.id,
            opponent = userService.getUserById(brokerId).name,
            lastMessage = "",
            lastMessageTimestamp = LocalDateTime.now()
        )
    }

    fun getReminder(chatRoomId: Long): Reminder? {
        return reminderService.getReminderByChatRoomId(chatRoomId)
    }

    fun addReminder(chatRoomId: Long, date: String, time: String, place: String, longitude: String, latitude: String) {
        val userId = authService.getUserId()
        return reminderService.addReminder(userId, chatRoomId, date, time, place, longitude, latitude)
    }
}