package com.cortez.gustavo.laboratoriocalificado03

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cortez.gustavo.laboratoriocalificado03.adapter.TeacherAdapter
import com.cortez.gustavo.laboratoriocalificado03.databinding.ActivityEjercicio01Binding
import com.cortez.gustavo.laboratoriocalificado03.model.Teacher
import com.cortez.gustavo.laboratoriocalificado03.network.RetrofitClient
import com.cortez.gustavo.laboratoriocalificado03.network.TeacherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Ejercicio01 : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio01Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        fetchTeachers()
    }

    private fun fetchTeachers() {
        RetrofitClient.apiService.getTeachers().enqueue(object : Callback<TeacherResponse> {
            override fun onResponse(call: Call<TeacherResponse>, response: Response<TeacherResponse>) {
                if (response.isSuccessful) {
                    val teacherList = response.body()?.teachers
                    if (teacherList != null) {
                        binding.recyclerView.adapter = TeacherAdapter(this@Ejercicio01, teacherList)
                    }
                }
            }

            override fun onFailure(call: Call<TeacherResponse>, t: Throwable) {
                // Manejar el error
            }
        })
    }
}