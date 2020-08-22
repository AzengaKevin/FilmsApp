package ke.co.propscout.filmsapp.data.network.response


import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName("_id")
    val id: String,
    val name: String,
    val rating: Int,
    @SerializedName("__v")
    val v: Int
)