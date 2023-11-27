package com.allacsta.composesubmission.model

data class Coffee(
    val id: Long,
    val image: Int,
    val title: String,
    val requiredPoint: Int,
    val description: String,
)