package com.example.coworking_admin.model

data class AccountModel(
    val id: Int,
    val name: String = "empty",
    val pictureUri: String = "https://i.pravatar.cc/150?u=${name}",
    val debt: Int = 0
)
