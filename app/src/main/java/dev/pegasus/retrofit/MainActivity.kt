package dev.pegasus.retrofit

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import dev.pegasus.retrofit.databinding.ActivityMainBinding
import dev.pegasus.retrofit.models.Post
import dev.pegasus.retrofit.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

private const val TAG = "MyTag"

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getRequestFirstWay()
        getRequestSecondWay()
    }

    private fun getRequestFirstWay() {
        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getAllPosts()
            } catch (ex: IOException) {
                Log.d(TAG, "getRequest: IOException: $ex")
                return@launchWhenCreated
            } catch (ex: HttpException) {
                Log.d(TAG, "getRequest: HttpException: $ex")
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null) {
                Log.d(TAG, "getRequest: Success: ${response.body()}")
            } else {
                Log.d(TAG, "getRequest: Failure: ${response.code()}")
                Log.d(TAG, "getRequest: Failure: ${response.message()}")
                Log.d(TAG, "getRequest: Failure: ${response.errorBody()}")
            }
        }
    }

    private fun getRequestSecondWay() {
        val call = RetrofitInstance.api.getAllPostsCall()
        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d(TAG, "getRequest: Success: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {

            }
        })
    }
}