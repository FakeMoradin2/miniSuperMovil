package com.example.project_miniMart.datasource.network.responses

/**
-> Success
{
userName : "Juan",
workstation: "vendedor",
email: "juan@gmail.com",
age: 25,
isPrincipal: true/false
phone: "3328025556"
}

-> Error
{
code: 2005
}




 */

data class LoginResponse(
    val status: Int,
    val codeError: Int? = null,
    val data: UserDataResponse
)

