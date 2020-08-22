package ke.co.propscout.filmsapp.data.network.response

data class AddFilmResponse(
    val error: Boolean,
    val film: Film,
    val message: String
)