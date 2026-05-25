package com.example.testretrofit.rest

import com.example.testretrofit.model.Lop
import com.example.testretrofit.model.ThongBao
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @GET("apiserver")
    suspend fun getLops(): Response<List<Lop>>
    
    @Headers("Accept: application/json; charset=utf-8")
    @FormUrlEncoded
    @POST("/apiserver/insert.php")
    suspend fun insertLop(
        @Field("tenlop") tenlop: String
    ): Response<ThongBao>
    
    @Headers("Accept: application/json; charset=utf-8")
    @FormUrlEncoded
    @POST("/apiserver/update.php")
    suspend fun updateLop(
        @Field("malop") malop: Int,
        @Field("tenlop") tenlop: String
    ): Response<ThongBao>
    
    @Headers("Accept: application/json; charset=utf-8")
    @FormUrlEncoded
    @POST("/apiserver/delete.php")
    suspend fun deleteLop(
        @Field("malop") malop: Int
    ): Response<ThongBao>
}
