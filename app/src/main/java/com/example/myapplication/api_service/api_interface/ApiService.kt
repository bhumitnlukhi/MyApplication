package com.example.myapplication.api_service.api_interface
import com.example.myapplication.api_service.data.LoginResponse
import com.example.myapplication.api_service.data.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}