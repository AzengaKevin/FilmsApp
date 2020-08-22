package ke.co.propscout.filmsapp.ui.films

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ke.co.propscout.filmsapp.data.network.FilmsApiService
import ke.co.propscout.filmsapp.data.network.response.Film
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "FilmsViewModel"

class FilmsViewModel(context: Context) : ViewModel() {

    private val _filmList: MutableLiveData<List<Film>> = MutableLiveData()

    init {
        val apiService = FilmsApiService(context)

        apiService.getAllFilms().enqueue(object : Callback<List<Film>> {
            override fun onFailure(call: Call<List<Film>>, t: Throwable) {
                Log.e(TAG, "init : onFailure: error", t)
            }

            override fun onResponse(call: Call<List<Film>>, response: Response<List<Film>>) {
                _filmList.postValue(response.body())
            }

        })
    }

    val filmList: LiveData<List<Film>>
        get() = _filmList


}