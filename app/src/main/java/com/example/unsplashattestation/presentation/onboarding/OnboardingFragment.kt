package com.example.unsplashattestation.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.unsplashattestation.R
import com.example.unsplashattestation.data.api.ONBOARDING_IS_SHOWN
import com.example.unsplashattestation.data.api.TOKEN_SHARED_NAME
import com.example.unsplashattestation.databinding.FragmentOnboardingBinding
import com.example.unsplashattestation.tools.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : BaseFragment<FragmentOnboardingBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentOnboardingBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPager()
        setTabs()
        setAuthorizeButton()
        saveOnbordingShown()
    }

    private fun setViewPager() {
        binding.viewPager.adapter =
            ViewPagerAdapter(resources.getStringArray(R.array.onboarding_texts_array))
        binding.viewPager.registerOnPageChangeCallback(AnimateImageOnPageChange(binding.ellipseImage))
    }

    private fun setTabs() =
        TabLayoutMediator(binding.tabs, binding.viewPager) { _, _ -> }.attach()


    private fun setAuthorizeButton() {
        binding.authorizeButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_onboarding_to_navigation_auth)
        }
    }

    private fun saveOnbordingShown() {
        val prefs = createSharedPreference(TOKEN_SHARED_NAME)
        prefs.edit().putBoolean(ONBOARDING_IS_SHOWN, true).apply()
    }
}