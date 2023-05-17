package com.roomeasy.capstoneproject.domain.user

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false)
    var providerName: String = "",

    @Column(nullable = false)
    var email: String = "",

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var ssoId: String = "",

    @Column(nullable = false)
    var type: String = "",

    @Column(nullable = false)
    var isVerified: Boolean = false,

    @Column(nullable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    var updatedDate: LocalDateTime = LocalDateTime.now(),
)