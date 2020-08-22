package ke.co.propscout.filmsapp.ui.films.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ke.co.propscout.filmsapp.R
import ke.co.propscout.filmsapp.data.network.response.Film
import ke.co.propscout.filmsapp.databinding.SingleFilmLayoutBinding

class FilmsAdapter : RecyclerView.Adapter<FilmsAdapter.FilmViewHolder>() {

    var filmsList: List<Film>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class FilmViewHolder(val singleFilmLayoutBinding: SingleFilmLayoutBinding) :
        RecyclerView.ViewHolder(singleFilmLayoutBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FilmViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.single_film_layout,
            parent,
            false
        )
    )

    override fun getItemCount() = filmsList?.size ?: 0

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.singleFilmLayoutBinding.film = filmsList!![position]
    }
}