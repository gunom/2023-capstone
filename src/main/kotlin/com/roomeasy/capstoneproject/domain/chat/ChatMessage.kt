package com.roomeasy.capstoneproject.domain.chat

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class ChatMessage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var sender: String,

    var userId: Long,

    var content: String,

    var timestamp: LocalDateTime = LocalDateTime.now(),

    var chatRoomId: Long = 0
)
