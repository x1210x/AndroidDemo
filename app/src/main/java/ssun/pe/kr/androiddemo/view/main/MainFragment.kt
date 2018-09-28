package ssun.pe.kr.androiddemo.view.main

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import ssun.pe.kr.androiddemo.R
import ssun.pe.kr.androiddemo.databinding.FragmentMainBinding
import ssun.pe.kr.androiddemo.view.detail.DetailActivity
import timber.log.Timber

class MainFragment : Fragment() {

    companion object {
        const val TAG = "MainFragment"
    }

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("[x1210x] onCreate()")
    }

    override fun onDestroy() {
        super.onDestroy()

        Timber.d("[x1210x] onDestroy()")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false).apply {
            setLifecycleOwner(this@MainFragment)
            viewModel = this@MainFragment.mainViewModel
        }

        mainViewModel.navigateToDetail.observe(this, Observer { url ->
            startActivity(DetailActivity.starterIntent(requireContext(), url))
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        mainViewModel.items.observe(this, Observer { items ->
            adapter.submitList(items)
        })
    }

    private fun initViews() {
        adapter = MainAdapter(mainViewModel, this)

        binding.rvItems.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MainFragment.adapter
            val deco = DividerItemDecoration(context, VERTICAL)
            deco.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.main_deco)!!)
            addItemDecoration(deco)
        }
    }
}