package com.example.kotlin_compose_dev

data class UserProfile constructor (val name: String, val status: Boolean, val drawableId: Int)

val userProfileList = arrayListOf<UserProfile>(
    UserProfile("John Doe", true, R.drawable.profile1),
    UserProfile("Anna Joans", false, R.drawable.profile2),
)