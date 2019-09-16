package com.ymnd.android.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.ymnd.android.adapter.DebugMenuAdapter
import com.ymnd.android.databinding.FragmentDebugMenuPageBinding
import com.ymnd.android.debug.DebugMenuCategory
import com.ymnd.android.debug.DebugMenuListItem
import com.ymnd.android.debug.RemoteConfigManager
import com.ymnd.android.presenter.DebugContract
import com.ymnd.android.presenter.DebugPresenter
import kotlinx.coroutines.launch

class DebugMenuFragment : Fragment(), DebugContract.View {

    companion object {
        private const val ARG_DEBUG_CATEGORY = "DEBUG_CATEGORY"

        fun newInstance(category: DebugMenuCategory) = DebugMenuFragment().apply {
            this.arguments = Bundle().apply {
                this.putSerializable(ARG_DEBUG_CATEGORY, category)
            }
        }
    }

    private lateinit var binding: FragmentDebugMenuPageBinding
    private lateinit var adapter: DebugMenuAdapter
    // DI
    private lateinit var presenter: DebugContract.Presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = DebugPresenter(requireContext(), RemoteConfigManager())
        presenter.setUp(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val debugCategory = arguments?.getSerializable(ARG_DEBUG_CATEGORY) as DebugMenuCategory
        presenter.setArguments(debugCategory)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDebugMenuPageBinding.inflate(inflater, null, false)
        adapter = DebugMenuAdapter(
            context = requireContext(),
            buttonClickListener = {
                //NOP
            }
        )
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                RecyclerView.VERTICAL
            )
        )
        binding.recyclerView.itemAnimator = null
        binding.swipeRefreshLayout.setOnRefreshListener {
            lifecycleScope.launch {
                presenter.onRefresh()
            }
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launch {
            presenter.onActivityCreated()
        }
    }

    override fun updateDebugList(debugList: List<DebugMenuListItem>) {
        adapter.reset(debugList)
    }

    override fun showLoading() {
        binding.swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        binding.swipeRefreshLayout.isRefreshing = false
    }
}