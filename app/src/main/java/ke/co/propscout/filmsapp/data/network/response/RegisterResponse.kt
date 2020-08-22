package ke.co.propscout.filmsapp.data.network.response


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    val error: Boolean,
    val message: String,
    val user: User
)