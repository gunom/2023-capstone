package com.roomeasy.capstoneproject.repository.user

import com.roomeasy.capstoneproject.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
    fun findBySsoId(ssoId: String): User?
}