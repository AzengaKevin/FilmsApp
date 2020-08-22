package ke.co.propscout.filmsapp.ui.films.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ke.co.propscout.filmsapp.R
import ke.co.propscout.filmsapp.databinding.FilmsFragmentBinding
import ke.co.propscout.filmsapp.ui.films.FilmsViewModel
import ke.co.propscout.filmsapp.ui.films.FilmsViewModelFactory

class FilmsFragment : Fragment() {

    private lateinit var binding: FilmsFragmentBinding
    private val filmsAdapter by lazy { FilmsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.films_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Setup the recycler view
        binding.filmsRecyclerView.setHasFixedSize(true)
        binding.filmsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.filmsRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.filmsRecyclerView.adapter = filmsAdapter

        binding.addFilmFab.setOnClickListener {
            findNavController().navigate(R.id.action_add_film)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = FilmsViewModelFactory(requireContext())
        val viewModel = ViewModelProvider(this, factory).get(FilmsViewModel::class.java)

        viewModel.filmList.observe(viewLifecycleOwner, Observer { filmList ->
            filmsAdapter.filmsList
        })
    }
}