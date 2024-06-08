package com.cortez.gustavo.laboratoriocalificado03.network

import com.cortez.gustavo.laboratoriocalificado03.model.Teacher
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("list/teacher-b")
    fun getTeachers(): Call<TeacherResponse>
}

data class TeacherResponse(val teachers: List<Teacher>)