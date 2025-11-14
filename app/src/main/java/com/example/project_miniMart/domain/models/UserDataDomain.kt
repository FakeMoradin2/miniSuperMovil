package com.example.project_miniMart.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDataDomain(
    val userName: String,
    val workstation: String,
    val email: String,
    val age: Int,
    val isPrincipal: Boolean,
    val phone: String
) : Parcelable