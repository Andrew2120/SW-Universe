package com.andrew.starwars.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.andrew.starwars.databinding.FragmentHomeBinding
import com.andrew.starwars.presentation.MainViewModel
import com.andrew.starwars.presentation.adapter.MoviesHomeAdapter
import com.andrew.starwars.presentation.adapter.PeopleHomeAdapter
import com.andrew.starwars.presentation.adapter.PlanetsHomeAdapter
import com.andrew.starwars.presentation.adapter.SpeciesHomeAdapter
import com.andrew.starwars.presentation.ui.home.states.*
import com.andrew.starwars.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val moviesHomeAdapter = MoviesHomeAdapter()
    private val planetsHomeAdapter = PlanetsHomeAdapter()
    private val peopleHomeAdapter = PeopleHomeAdapter()
    private val speciesHomeAdapter = SpeciesHomeAdapter()
    private val viewModel: HomeViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.moviesHomeLayout.viewPager2.adapter = moviesHomeAdapter
        binding.planetsHomeLayout.RecyclerView.adapter = planetsHomeAdapter
        binding.peopleHomeLayout.RecyclerView.adapter = peopleHomeAdapter
        binding.speciesHomeLayout.RecyclerView.adapter = speciesHomeAdapter


        listenToEvents()

        binding.circleImageView2.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationProfile())
        }

        peopleHomeAdapter.onItemClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionNavigationHomeToPersonFragment(
                    it
                )
            )
        }
        planetsHomeAdapter.onItemClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionNavigationHomeToPlanetFragment(
                    it
                )
            )
        }
        speciesHomeAdapter.onItemClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionNavigationHomeToSpeciesFragment(
                    it
                )
            )
        }
        moviesHomeAdapter.onItemClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionNavigationHomeToMovieFragment(
                    it
                )
            )
        }
        binding.search.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToSearchFragment())
        }


    }


    private fun listenToEvents() {
        lifecycleScope.launchWhenCreated {
            viewModel.peopleFlow.collectLatest {
                try {
                    when (it) {
                        is PeopleState.ShowError -> {
                            binding.homeShimmer?.groupPeopleShimmer?.visibility = View.INVISIBLE

                        }
                        is PeopleState.Loading -> {
                            binding.homeShimmer?.groupPeopleShimmer?.visibility = View.VISIBLE

                        }
                        is PeopleState.Success -> {
                            binding.homeShimmer?.groupPeopleShimmer?.visibility = View.INVISIBLE
                            binding.peopleHomeLayout.root.visibility = View.VISIBLE
                            peopleHomeAdapter.differ.submitList(it.data)
                        }
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.planetFlow.collectLatest {
                try {
                    when (it) {
                        is PlanetsState.ShowError -> {
                            binding.homeShimmer?.groupPlanetShimmer?.visibility = View.INVISIBLE

                        }
                        is PlanetsState.Loading -> {

                            binding.homeShimmer?.groupPlanetShimmer?.visibility = View.VISIBLE
                        }
                        is PlanetsState.Success -> {
                            binding.homeShimmer?.groupPlanetShimmer?.visibility = View.INVISIBLE

                            binding.planetsHomeLayout.root.visibility = View.VISIBLE
                            planetsHomeAdapter.differ.submitList(it.data)
                        }
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            mainViewModel.userFlow.collectLatest {
                when (it) {
                    MainViewModel.UserState.Loading -> {}
                    is MainViewModel.UserState.ShowError -> {}
                    is MainViewModel.UserState.Success -> {
                        if (it.data != null)
                            binding.user = it.data


                    }
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.moviesFlow.collectLatest {
                try {
                    when (it) {
                        is MoviesState.ShowError -> {
                            binding.homeShimmer?.view2?.visibility = View.INVISIBLE
                        }
                        is MoviesState.Loading -> {
                            binding.homeShimmer?.view2?.visibility = View.VISIBLE
                        }
                        is MoviesState.Success -> {
                            binding.homeShimmer?.view2?.visibility = View.INVISIBLE
                            binding.moviesHomeLayout.root.visibility = View.VISIBLE
                            moviesHomeAdapter.differ.submitList(it.data)
                        }
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            try {
                viewModel.speciesFlow.collectLatest {
                    when (it) {
                        is SpeciesState.ShowError -> {
                            binding.homeShimmer?.groupSpeciesShimmer?.visibility = View.INVISIBLE

                        }
                        is SpeciesState.Loading -> {
                            binding.homeShimmer?.groupSpeciesShimmer?.visibility = View.VISIBLE
                        }
                        is SpeciesState.Success -> {
                            binding.homeShimmer?.groupSpeciesShimmer?.visibility = View.INVISIBLE
                            binding.speciesHomeLayout.root.visibility = View.VISIBLE
                            speciesHomeAdapter.differ.submitList(it.data)
                        }
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }



    }

}