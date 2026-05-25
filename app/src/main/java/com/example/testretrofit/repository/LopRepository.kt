package com.example.testretrofit.repository

import com.example.testretrofit.model.Lop
import com.example.testretrofit.model.ThongBao
import com.example.testretrofit.rest.RetrofitClient

class LopRepository {
    suspend fun fetchLops(): List<Lop> {
        return try {
            val response = RetrofitClient.apiService.getLops()
            if (response.isSuccessful) response.body() ?: emptyList() else emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun insertLop(tenlop: String): ThongBao? {
        return try {
            val response = RetrofitClient.apiService.insertLop(tenlop)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            null
        }
    }
    
    suspend fun updateLop(malop: Int, tenlop: String): ThongBao? {
        return try {
            val response = RetrofitClient.apiService.updateLop(malop, tenlop)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            null
        }
    }
    
    suspend fun deleteLop(malop: Int): ThongBao? {
        return try {
            val response = RetrofitClient.apiService.deleteLop(malop)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            null
        }
    }
}
