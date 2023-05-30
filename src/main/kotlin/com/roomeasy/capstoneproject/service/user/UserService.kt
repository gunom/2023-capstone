package com.roomeasy.capstoneproject.service.user

import com.roomeasy.capstoneproject.domain.user.User
import com.roomeasy.capstoneproject.service.dto.RegisterInformationDto

interface UserService {
    fun getUserById(id: Long): User
    fun getUserByIds(ids: List<Long>): List<User>
    fun registerInformation(user: User, name: String, registerNumber: String?): RegisterInformationDto
}