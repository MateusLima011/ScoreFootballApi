package com.mgt.scorefootballapi.leagues

import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mgt.scorefootballapi.databinding.FragmentLeaguesBinding
import com.mgt.scorefootballapi.players.PlayersAdapter
import com.mgt.scorefootballapi.utils.Failure
import com.mgt.scorefootballapi.utils.Loading
import com.mgt.scorefootballapi.utils.NetworkStatusChecker
import com.mgt.scorefootballapi.utils.Success

@RequiresApi(Build.VERSION_CODES.M)
class LeaguesFragment : Fragment() {

    private var _binding: FragmentLeaguesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LeaguesViewModel by viewModels()

    private val networkStatusChecker by lazy {
        NetworkStatusChecker(activity?.getSystemService(ConnectivityManager::class.java))
    }
    private lateinit var recyclerViewLeagues: RecyclerView
    private lateinit var leaguesAdapter: LeaguesAdapter
    private lateinit var layoutManager: GridLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLeagueList()
        observerLeagueList()
        recyclerViewLeagues = binding.recyclerViewLeague
        layoutManager = GridLayoutManager(
            context, 2,
            GridLayoutManager.VERTICAL, false
        )
        recyclerViewLeagues.layoutManager = layoutManager
    }

    private fun observerLeagueList() {
        networkStatusChecker.performIfConnectedToInternet {
            viewModel.leagueLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    is Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Success -> {
                        binding.progressBar.visibility = View.GONE
                        leaguesAdapter = LeaguesAdapter(it.data)
                        recyclerViewLeagues.adapter = leaguesAdapter
                    }
                    is Failure -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLeaguesBinding.inflate(inflater, container, false)
        return binding.root
    }

}