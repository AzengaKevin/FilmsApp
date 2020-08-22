package ke.co.propscout.filmsapp.ui.films

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ke.co.propscout.filmsapp.data.network.FilmsApiService
import ke.co.propscout.filmsapp.data.network.requests.AddFilmRequest
import ke.co.propscout.filmsapp.data.network.response.AddFilmResponse
import ke.co.propscout.filmsapp.data.network.response.Film
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "FilmsViewModel"

class FilmsViewModel(context: Context) : ViewModel() {

    private val _filmList: MutableLiveData<List<Film>> = MutableLiveData()
    val filmList: LiveData<List<Film>>
        get() = _filmList

    private val _filmAdded: MutableLiveData<Boolean> = MutableLiveData()
    val filmAdded: LiveData<Boolean>
        get() = _filmAdded

    private var apiService: FilmsApiService = FilmsApiService(context)

    init {
        apiService.getAllFilms().enqueue(object : Callback<List<Film>> {
            override fun onFailure(call: Call<List<Film>>, t: Throwable) {
                Log.e(TAG, "init : onFailure: error", t)
            }

            override fun onResponse(call: Call<List<Film>>, response: Response<List<Film>>) {
                if (response.isSuccessful) {
                    _filmList.postValue(response.body())
                } else {
                    Log.d(
                        ke.co.propscout.filmsapp.ui.users.TAG,
                        "onResponse: body: " + response.body()
                    )
                    Log.d(
                        ke.co.propscout.filmsapp.ui.users.TAG,
                        "login: onResponse: error-body: " + response.errorBody()
                    )
                    Log.d(
                        ke.co.propscout.filmsapp.ui.users.TAG,
                        "onResponse: message: " + response.message()
                    )

                    Log.d(
                        ke.co.propscout.filmsapp.ui.users.TAG,
                        "onResponse: code : " + response.code()
                    )

                    Log.d(
                        ke.co.propscout.filmsapp.ui.users.TAG,
                        "onResponse: raw: " + response.raw()
                    )
                }
            }

        })
    }


    fun addFilm(name: String, rating: Double) {
        apiService.addFilm(AddFilmRequest(name, rating))
            .enqueue(object : Callback<AddFilmResponse> {
                override fun onFailure(call: Call<AddFilmResponse>, t: Throwable) {
                    Log.e(TAG, "addFilm: onFailure:", t)
                }

                override fun onResponse(
                    call: Call<AddFilmResponse>,
                    response: Response<AddFilmResponse>
                ) {
                    if (response.isSuccessful) {
                        val flag = response.body()?.error ?: true

                        if (!flag) {
                            _filmAdded.postValue(true)
                        }

                    } else {
                        Log.d(
                            ke.co.propscout.filmsapp.ui.users.TAG,
                            "onResponse: body: " + response.body()
                        )
                        Log.d(
                            ke.co.propscout.filmsapp.ui.users.TAG,
                            "login: onResponse: error-body: " + response.errorBody()
                        )
                        Log.d(
                            ke.co.propscout.filmsapp.ui.users.TAG,
                            "onResponse: message: " + response.message()
                        )

                        Log.d(
                            ke.co.propscout.filmsapp.ui.users.TAG,
                            "onResponse: code : " + response.code()
                        )

                        Log.d(
                            ke.co.propscout.filmsapp.ui.users.TAG,
                            "onResponse: raw: " + response.raw()
                        )
                    }
                }

            })
    }


}