package com.example.kriptoparaapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kriptoparaapp.R
import com.example.kriptoparaapp.adapter.RecyclerViewAdapter
import com.example.kriptoparaapp.model.CryptoModel
import com.example.kriptoparaapp.service.CryptoAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale.filter

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.Listener {


    private val BASE_URL="https://raw.githubusercontent.com/"
    private var crtptoModels:ArrayList<CryptoModel>? = null

    private  var recyclerViewAdapter:RecyclerViewAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        val layoutManager : RecyclerView.LayoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        button.setOnClickListener {
            loadData()
        }



        // c3c21f2c30e4062bbf997ec521bf3ee9
        // http://api.nomics.com/v1/markets?key=c3c21f2c30e4062bbf997ec521bf3ee9




}




    private fun loadData(){

        val retrofit= Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()
        call.enqueue(object : Callback<List<CryptoModel>>{
            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {

                t.printStackTrace()
            }
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful){
                 response.body()?.let {

                     crtptoModels = ArrayList(it)

                     crtptoModels?.let {
                         recyclerViewAdapter = RecyclerViewAdapter(crtptoModels!!,this@MainActivity)
                         recyclerView.adapter = recyclerViewAdapter
                     }



                 }
                }
            }

        })

    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this,"Clicked: ${cryptoModel.currency}",Toast.LENGTH_LONG).show()
    }
}




