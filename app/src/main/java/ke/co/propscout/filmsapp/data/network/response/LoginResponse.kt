package ke.co.propscout.filmsapp.data.network.response

data class LoginResponse(
    val error: Boolean,
    val message: String,
    val user: User,
    val token: String
)