package com.andrew.starwars.presentation.ui.profile.theme_customization

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.andrew.starwars.MainActivity
import com.andrew.starwars.R
import com.andrew.starwars.databinding.FragmentThemeCustomizationBinding
import com.andrew.starwars.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThemeCustomizationFragment : BaseFragment<FragmentThemeCustomizationBinding>(
    FragmentThemeCustomizationBinding::inflate
) {

    private val viewModel: ThemeCustomizationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.currentThemeId.observe(viewLifecycleOwner) {
            binding.apply {
                when (it) {
                    1 -> {
                        animatedCheckMark1.visibility = View.VISIBLE
                        animatedCheckMark1.speed = 4f
                        animatedCheckMark1.playAnimation()
                        animatedCheckMark2.visibility = View.GONE
                        animatedCheckMark3.visibility = View.GONE
                        animatedCheckMark4.visibility = View.GONE
                        animatedCheckMark5.visibility = View.GONE
                        animatedCheckMark6.visibility = View.GONE
                    }
                    2 -> {
                        animatedCheckMark1.visibility = View.GONE
                        animatedCheckMark2.visibility = View.VISIBLE
                        animatedCheckMark2.speed = 4f
                        animatedCheckMark2.playAnimation()
                        animatedCheckMark3.visibility = View.GONE
                        animatedCheckMark4.visibility = View.GONE
                        animatedCheckMark5.visibility = View.GONE
                        animatedCheckMark6.visibility = View.GONE
                    }
                    3 -> {
                        animatedCheckMark1.visibility = View.GONE
                        animatedCheckMark2.visibility = View.GONE
                        animatedCheckMark3.visibility = View.VISIBLE
                        animatedCheckMark3.speed = 4f
                        animatedCheckMark3.playAnimation()
                        animatedCheckMark4.visibility = View.GONE
                        animatedCheckMark5.visibility = View.GONE
                        animatedCheckMark6.visibility = View.GONE
                    }
                    4 -> {
                        animatedCheckMark1.visibility = View.GONE
                        animatedCheckMark2.visibility = View.GONE
                        animatedCheckMark3.visibility = View.GONE
                        animatedCheckMark4.visibility = View.VISIBLE
                        animatedCheckMark4.speed = 4f
                        animatedCheckMark4.playAnimation()
                        animatedCheckMark5.visibility = View.GONE
                        animatedCheckMark6.visibility = View.GONE
                    }
                    5 -> {
                        animatedCheckMark1.visibility = View.GONE
                        animatedCheckMark2.visibility = View.GONE
                        animatedCheckMark3.visibility = View.GONE
                        animatedCheckMark4.visibility = View.GONE
                        animatedCheckMark5.visibility = View.VISIBLE
                        animatedCheckMark5.speed = 4f
                        animatedCheckMark5.playAnimation()
                        animatedCheckMark6.visibility = View.GONE
                    }
                    6 -> {
                        animatedCheckMark1.visibility = View.GONE
                        animatedCheckMark2.visibility = View.GONE
                        animatedCheckMark3.visibility = View.GONE
                        animatedCheckMark4.visibility = View.GONE
                        animatedCheckMark5.visibility = View.GONE
                        animatedCheckMark6.visibility = View.VISIBLE
                        animatedCheckMark6.speed = 4f
                        animatedCheckMark6.playAnimation()
                    }
                }
            }
        }

        binding.applyButton.setOnClickListener {

            viewModel.currentThemeId.observe(viewLifecycleOwner) {
                viewModel.saveTheme(it)
            }.apply {
                Intent(requireContext(), MainActivity::class.java).also {
                    startActivity(it)
                    requireActivity().finish()
                }
            }

        }

        binding.firstThemeCv.setOnClickListener {
            viewModel.setCurrentTheme(1)
            setFirstTheme()
        }

        binding.secondThemeCv.setOnClickListener {
            viewModel.setCurrentTheme(2)
            setSecondTheme()
        }

        binding.thirdThemeCv.setOnClickListener {
            viewModel.setCurrentTheme(3)
            setThirdTheme()
        }

        binding.fourthThemeCv.setOnClickListener {
            viewModel.setCurrentTheme(4)
            setFourthTheme()
        }

        binding.fifthThemeCv.setOnClickListener {
            viewModel.setCurrentTheme(5)
            setFifthTheme()
        }

        binding.sixthThemeCv.setOnClickListener {
            viewModel.setCurrentTheme(6)
            setSixthTheme()
        }
    }


    private fun setFirstTheme() {
        binding.apply {
            applyButton.setCardViewBgColor(R.color.theme_one_main)
        }
    }

    private fun setSecondTheme() {
        binding.apply {
            applyButton.setCardViewBgColor(R.color.theme_two_main)
        }
    }

    private fun setThirdTheme() {
        binding.apply {
            applyButton.setCardViewBgColor(R.color.theme_three_main)
        }
    }

    private fun setFourthTheme() {
        binding.apply {
            applyButton.setCardViewBgColor(R.color.theme_four_main)
        }
    }

    private fun setFifthTheme() {
        binding.apply {
            applyButton.setCardViewBgColor(R.color.theme_five_main)
        }
    }

    private fun setSixthTheme() {
        binding.apply {
            applyButton.setCardViewBgColor(R.color.theme_six_main)
        }
    }

    private fun CardView.setCardViewBgColor(@ColorRes color: Int) {
        this.setCardBackgroundColor(getContextCompactColor(color))
    }

    private fun getContextCompactColor(@ColorRes color: Int): Int =
        ContextCompat.getColor(requireContext(), color)
}
