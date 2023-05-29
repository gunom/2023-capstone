package com.roomeasy.capstoneproject.domain.chat

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "reminder")
class Reminder(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "chat_room_id", nullable = false)
    val chatRoomId: Long,

    @Column(name = "date", nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
    val date: LocalDateTime,

    @Column(name = "place")
    val place: String? = null,

    @Column(name = "longitude", nullable = false)
    val longitude: Double,

    @Column(name = "latitude", nullable = false)
    val latitude: Double
)