package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val BASE_URL = "https://jsonplaceholder.typicode.com/";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.getData();
    }

    fun getData(){
        val retroBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(APIInterface::class.java);
        val retrofitData = retroBuilder.getPosts();
        retrofitData.enqueue(object: Callback<List<PostItem>?> {
            override fun onResponse(call: Call<List<PostItem>?>, response: Response<List<PostItem>?>) {
                Log.d("data here",response.body().toString());
            }

            override fun onFailure(call: Call<List<PostItem>?>, t: Throwable) {
            }
        })
    }

}