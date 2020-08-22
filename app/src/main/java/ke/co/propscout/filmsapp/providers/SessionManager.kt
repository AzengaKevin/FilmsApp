package ke.co.propscout.filmsapp.providers

import android.content.Context
import ke.co.propscout.filmsapp.R

class SessionManager(context: Context) {
    companion object {
        const val TOKEN = "token"
    }

    private val sharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = sharedPreferences.edit()

        editor.putString(TOKEN, token)

        editor.apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN, null)
    }
}