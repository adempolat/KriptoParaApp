package com.example.kriptoparaapp.service

import android.telecom.Call
import com.example.kriptoparaapp.model.CryptoModel
import retrofit2.http.GET

interface CryptoAPI {

    // c3c21f2c30e4062bbf997ec521bf3ee9
    // http://api.nomics.com/v1/markets?key=c3c21f2c30e4062bbf997ec521bf3ee9

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")

    fun getData(): retrofit2.Call<List<CryptoModel>>


}