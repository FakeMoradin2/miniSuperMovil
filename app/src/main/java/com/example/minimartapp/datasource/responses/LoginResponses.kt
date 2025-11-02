package com.example.minimartapp.datasource.responses

data class LoginResponse(
    val status: Int,
    val codeError: Int? = null,
    val data: UserDataResponse
)

data class UserDataResponse(
    val userName: String,
    val phone: String,
    val  rol: String
)

/*

Success

{
  "status": 200,
  "codeError": null,
  "data": {
    "userName": "Ana López",
    "phone": "+52 5551234567",
    "rol": "Administrador"
  }
}

Error

{
  "status": 401,
  "codeError": 1001,
  "data": {
    "userName": "",
    "phone": "",
    "rol": ""
  }
}

 */