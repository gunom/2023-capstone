package com.roomeasy.capstoneproject.service.chat

import com.roomeasy.capstoneproject.domain.chat.ChatRoom

interface ChatRoomService {
    fun getChatRoomsByIds(ids: List<Long>): MutableList<ChatRoom>
    fun createChatRoom(): ChatRoom
}