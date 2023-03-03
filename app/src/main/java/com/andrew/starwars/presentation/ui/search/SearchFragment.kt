package com.andrew.starwars.presentation.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.andrew.starwars.databinding.FragmentSearchBinding
import com.andrew.starwars.presentation.adapter.MoviesAdapter
import com.andrew.starwars.presentation.adapter.PeopleAdapter
import com.andrew.starwars.presentation.adapter.PlanetsAdapter
import com.andrew.starwars.presentation.adapter.SpeciesAdapter
import com.andrew.starwars.presentation.ui.search.states.MoviesSearchState
import com.andrew.starwars.presentation.ui.search.states.PeopleSearchState
import com.andrew.starwars.presentation.ui.search.states.PlanetsSearchState
import com.andrew.starwars.presentation.ui.search.states.SpeciesSearchState
import com.andrew.starwars.utils.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val viewModel: SearchViewModel by viewModels()
    private val peopleAdapter = PeopleAdapter()
    private val moviesAdapter = MoviesAdapter()
    private val planetsAdapter = PlanetsAdapter()
    private val speciesAdapter = SpeciesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)


        binding.editTextSearch.addTextChangedListener {
            viewModel.searchQuery.value = it.toString()
            if (it?.isNotBlank() == true) {
                when (viewModel.searchType.value) {
                    0 -> {
                        viewModel.searchPeople()
                    }
                    1 -> {
                        viewModel.searchPlanets()
                    }
                    2 -> {
                        viewModel.searchMovies()
                    }
                    3 -> {
                        viewModel.searchSpecies()

                    }
                }

            }

        }

        planetsAdapter.onItemClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToPlanetFragment(it))
        }
        speciesAdapter.onItemClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSpeciesFragment(it))
        }
        moviesAdapter.onItemClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToMovieFragment(it))
        }
        peopleAdapter.onItemClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToPersonFragment(it))
        }

        binding.tableLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewModel.searchType.value = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        viewModel.searchType.observe(viewLifecycleOwner) {
            viewModel.searchQuery.value = null
            binding.editTextSearch.text = null

            when (it) {
                0 -> {
                    viewModel.searchPeople()
                    binding.RecyclerView.adapter = peopleAdapter
                }
                1 -> {
                    viewModel.searchPlanets()
                    binding.RecyclerView.adapter = planetsAdapter

                }
                2 -> {
                    viewModel.searchMovies()
                    binding.RecyclerView.adapter = moviesAdapter

                }
                3 -> {
                    viewModel.searchSpecies()
                    binding.RecyclerView.adapter = speciesAdapter

                }
            }
        }

        listenToChanges()

    }

    private fun listenToChanges() {

        lifecycleScope.launch {
            viewModel.peopleFlow.collectLatest {
                when (it) {
                    is PeopleSearchState.Loading -> {
                    }
                    is PeopleSearchState.Success -> {
                        peopleAdapter.submitData(it.data)
                    }
                    is PeopleSearchState.ShowError -> {
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.moviesFlow.collectLatest {
                when (it) {
                    is MoviesSearchState.Loading -> {
                    }
                    is MoviesSearchState.Success -> {
                        moviesAdapter.submitData(it.data)
                    }
                    is MoviesSearchState.ShowError -> {
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.planetsFlow.collectLatest {
                when (it) {
                    is PlanetsSearchState.Loading -> {
                    }
                    is PlanetsSearchState.Success -> {
                        planetsAdapter.submitData(it.data)
                    }
                    is PlanetsSearchState.ShowError -> {
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.speciesFlow.collectLatest {
                when (it) {
                    is SpeciesSearchState.Loading -> {
                    }
                    is SpeciesSearchState.Success -> {
                        speciesAdapter.submitData(it.data)
                    }
                    is SpeciesSearchState.ShowError -> {
                    }
                }
            }
        }
    }
}