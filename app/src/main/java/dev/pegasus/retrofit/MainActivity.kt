package dev.pegasus.retrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dev.pegasus.retrofit.databinding.ActivityMainBinding
import dev.pegasus.retrofit.retrofit.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException

private const val TAG = "MyTag"

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getRequest()
    }

    private fun getRequest() {
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
}