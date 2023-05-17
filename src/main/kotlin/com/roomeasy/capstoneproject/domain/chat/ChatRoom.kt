package com.roomeasy.capstoneproject.domain.chat

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()
}