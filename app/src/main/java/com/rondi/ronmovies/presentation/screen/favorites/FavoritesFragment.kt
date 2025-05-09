package com.rondi.ronmovies.presentation.screen.favorites

import android.os.Bundle
import android.view.View
import com.rondi.ronmovies.R
import com.rondi.ronmovies.databinding.FragmentFavoritesBinding
import com.rondi.ronmovies.presentation.adapter.FragmentAdapter
import com.rondi.ronmovies.presentation.base.BaseFragment
import com.rondi.ronmovies.presentation.base.BaseViewModel
import com.google.android.material.tabs.TabLayoutMediator

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>(R.layout.fragment_favorites) {

    override val viewModel: BaseViewModel?
        get() = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = FragmentAdapter(this)
        manageViewPagerAdapterLifecycle(binding.viewPager, false)

        mediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val tabTitles = listOf(getString(R.string.tab_title_1), getString(R.string.tab_title_2))
            tab.text = tabTitles[position]
        }

        mediator?.attach()
    }
}