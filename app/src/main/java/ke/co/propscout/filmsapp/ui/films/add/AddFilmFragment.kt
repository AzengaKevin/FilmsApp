package ke.co.propscout.filmsapp.ui.films.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ke.co.propscout.filmsapp.R
import ke.co.propscout.filmsapp.databinding.AddFilmFragmentBinding
import ke.co.propscout.filmsapp.ui.films.FilmsViewModel
import ke.co.propscout.filmsapp.ui.films.FilmsViewModelFactory

const val TAG = "AddFilmFragment"

class AddFilmFragment : Fragment() {

    private lateinit var binding: AddFilmFragmentBinding
    private lateinit var viewModel: FilmsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_film_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addFilmButton.setOnClickListener {
            val name = binding.nameField.text.toString()
            val rating = binding.ratingBar.rating.toDouble()

            if (name.isEmpty() || name.length < 4) {
                binding.nameField.error = "At least a 4 char name is required"
                binding.nameField.requestFocus()
                return@setOnClickListener
            }

            if (::viewModel.isInitialized) {
                viewModel.addFilm(name, rating)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = FilmsViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this, factory).get(FilmsViewModel::class.java)

        viewModel.filmAdded.observe(viewLifecycleOwner, Observer {

            if (it) {
                requireActivity().onBackPressed()
            }
        })
    }
}