package com.roomeasy.capstoneproject.service.user

import com.roomeasy.capstoneproject.domain.user.User

interface UserService {
    fun getUserById(id: Long): User
    fun getUserByIds(ids: List<Long>): List<User>
    fun registerInformation(user: User, name: String, registerNumber: String?)
}