package com.roomeasy.capstoneproject.service.chat

import com.roomeasy.capstoneproject.domain.chat.ChatRoomUser

interface ChatRoomUserService {
    fun getChatRoomByUserId(userId: Long): List<ChatRoomUser>
    fun getChatRoomByRoomIds(roomIds: List<Long>): List<ChatRoomUser>
    fun addChatRoomUser(chatRoomId: Long, userId: Long)
}