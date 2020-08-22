package ke.co.propscout.filmsapp.data.network.response

import com.google.gson.annotations.SerializedName

data class User(
    val email: String,
    @SerializedName("_id")
    val id: String,
    val name: String,
    val password: String,
    @SerializedName("__v")
    val v: Int
)