package ke.co.propscout.filmsapp.data.network

import android.content.Context
import ke.co.propscout.filmsapp.data.network.interceptors.AuthInterceptor
import ke.co.propscout.filmsapp.data.network.interceptors.ConnectivityInterceptor
import ke.co.propscout.filmsapp.data.network.requests.LoginRequest
import ke.co.propscout.filmsapp.data.network.response.AddFilmResponse
import ke.co.propscout.filmsapp.data.network.response.Film
import ke.co.propscout.filmsapp.data.network.response.LoginResponse
import ke.co.propscout.filmsapp.data.network.response.RegisterResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface FilmsApiService {

    @GET(FilmConstants.FILM_PATH)
    fun getAllFilms(): Call<List<Film>>

    @POST(FilmConstants.REGISTER_PATH)
    @Headers("Accept: application/json", "Content-Type: application/json")
    fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @POST(FilmConstants.LOGIN_PATH)
    @Headers("Accept: application/json", "Content-Type: application/json")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST(FilmConstants.FILM_PATH)
    @Headers("Accept: application/json", "Content-Type: application/json")
    fun addFilm(
        @Field("email") email: String,
        @Field("rating") rating: Int
    ): Call<AddFilmResponse>

    companion object {
        operator fun invoke(context: Context): FilmsApiService {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(ConnectivityInterceptor(context))
                .addInterceptor(AuthInterceptor(context))
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(FilmConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FilmsApiService::class.java)
        }
    }
}