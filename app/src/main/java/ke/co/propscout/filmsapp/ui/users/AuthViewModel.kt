package ke.co.propscout.filmsapp.ui.users

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ke.co.propscout.filmsapp.data.network.FilmsApiService
import ke.co.propscout.filmsapp.data.network.requests.LoginRequest
import ke.co.propscout.filmsapp.data.network.requests.RegisterRequest
import ke.co.propscout.filmsapp.data.network.response.LoginResponse
import ke.co.propscout.filmsapp.data.network.response.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response

const val TAG = "AuthViewModel"

class AuthViewModel(context: Context) : ViewModel() {

    private val apiService by lazy {
        FilmsApiService(context)
    }

    private val _loginResponse: MutableLiveData<LoginResponse> = MutableLiveData()

    val loginResponse: LiveData<LoginResponse>
        get() = _loginResponse


    private val _registerResponse: MutableLiveData<RegisterResponse> = MutableLiveData()

    val registerResponse: LiveData<RegisterResponse>
        get() = _registerResponse

    fun login(email: String, password: String) {

        apiService.loginUser(LoginRequest(email, password))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e(TAG, "login: onFailure: error", t)
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        _loginResponse.postValue(response.body())
                    } else {
                        Log.d(TAG, "onResponse: body" + response.body())
                        Log.d(
                            TAG,
                            "login: onResponse: error-body: " + response.errorBody()
                        )
                        Log.d(TAG, "onResponse: message" + response.message())

                        Log.d(TAG, "onResponse: code" + response.code())

                        Log.d(TAG, "onResponse: raw" + response.raw())
                    }
                }

            })

    }

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            apiService.registerUser(RegisterRequest(name, email, password))
                .enqueue(object : Callback<RegisterResponse> {
                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        Log.e(TAG, "register: onFailure: error", t)
                    }

                    override fun onResponse(
                        call: Call<RegisterResponse>,
                        response: Response<RegisterResponse>
                    ) {
                        if (response.isSuccessful) {
                            _registerResponse.postValue(response.body())
                        } else {
                            Log.d(TAG, "onResponse: body" + response.body())
                            Log.d(
                                TAG,
                                "login: onResponse: error-body: " + response.errorBody()
                            )
                            Log.d(TAG, "onResponse: message" + response.message())

                            Log.d(TAG, "onResponse: code" + response.code())

                            Log.d(TAG, "onResponse: raw" + response.raw())
                        }
                    }

                })
        }
    }

}