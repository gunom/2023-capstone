package com.roomeasy.capstoneproject.service.user

import com.roomeasy.capstoneproject.domain.user.User
import com.roomeasy.capstoneproject.repository.user.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    override fun getUserById(id: Long): User {
        return userRepository.findById(id).orElseThrow { NoSuchElementException() }
    }

    override fun getUserByIds(ids: List<Long>): List<User> {
        return userRepository.findAllById(ids)
    }

    override fun registerInformation(user: User, name: String, registerNumber: String?) {
        val newUser = User(
            id = user.id,
            providerName = user.providerName,
            email = user.email,
            name = name,
            ssoId = user.ssoId,
            type = if (registerNumber == null) "user" else "broker",
            isVerified = user.isVerified,
            createdDate = user.createdDate,
            updatedDate = user.updatedDate,
        )

        userRepository.save(newUser)
    }
}