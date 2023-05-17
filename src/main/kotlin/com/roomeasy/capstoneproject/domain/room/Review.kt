package com.roomeasy.capstoneproject.domain.room

import javax.persistence.*

@Entity
@Table(name = "review")
class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "room_id", nullable = false)
    val roomId: Long,

    @Column(name = "time_of_residence", nullable = false)
    val timeOfResidence: Int,

    @Column(name = "age_group", nullable = false)
    val ageGroup: String,

    @Column(name = "gender", nullable = false)
    val gender: String,

    @Column(name = "transportation_rating", nullable = false)
    val transportationRating: Int,

    @Column(name = "neighborhood_rating", nullable = false)
    val neighborhoodRating: Int,

    @Column(name = "living_conditions_rating", nullable = false)
    val livingConditionsRating: Int,

    @Lob
    @Column(name = "free_comments")
    val freeComments: String? = null
)