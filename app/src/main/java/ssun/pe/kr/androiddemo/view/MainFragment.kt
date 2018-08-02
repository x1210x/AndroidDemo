package ssun.pe.kr.androiddemo.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView.VERTICAL
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.main_frag.*
import ssun.pe.kr.androiddemo.R
import ssun.pe.kr.androiddemo.data.model.Item
import ssun.pe.kr.androiddemo.databinding.MainFragBinding
import java.util.*

class MainFragment : Fragment() {

    companion object {
        const val TAG = "MainFragment"
    }

    var viewModel: MainViewModel? = null

    private var binding: MainFragBinding? = null

    private val mItems: MutableList<Item> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding =  MainFragBinding.inflate(inflater, container, false)
        binding!!.viewmodel = viewModel
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSearch.setOnClickListener {
            etSearch.text.toString().let { text ->
                if (text.isNotBlank()) {
                    viewModel?.searchBlog(text)
                }
            }
        }

        rvItems.layoutManager = LinearLayoutManager(context)
        rvItems.adapter = MainAdapter(mItems)
        val deco = DividerItemDecoration(context, VERTICAL)
        deco.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.main_deco)!!)
        rvItems.addItemDecoration(deco)
    }
}