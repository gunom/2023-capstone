package com.roomeasy.capstoneproject.domain.room

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class BrokerRoom(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name="user_id", nullable = false)
    var userId: Long = 0,

    @Column(name="room_id", nullable = false)
    var roomId: Long = 0,

    @Column(name="created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name="updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now(),
)