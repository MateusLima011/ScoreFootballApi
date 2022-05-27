package com.mgt.scorefootballapi.players

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
import com.mgt.scorefootballapi.databinding.FragmentPlayersBinding
import com.mgt.scorefootballapi.utils.Failure
import com.mgt.scorefootballapi.utils.Loading
import com.mgt.scorefootballapi.utils.NetworkStatusChecker
import com.mgt.scorefootballapi.utils.Success

@RequiresApi(Build.VERSION_CODES.M)
class PlayersFragment : Fragment() {

    private var _binding: FragmentPlayersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PlayersViewModel by viewModels()

    private val networkStatusChecker by lazy {
        NetworkStatusChecker(activity?.getSystemService(ConnectivityManager::class.java))
    }
    private lateinit var recyclerViewPlayers: RecyclerView
    private lateinit var playersAdapter: PlayersAdapter
    private lateinit var layoutManager: GridLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPlayerList()
        observerPlayersList()
        recyclerViewPlayers = binding.recyclerViewPlayer
        layoutManager = GridLayoutManager(context, 2,
            GridLayoutManager.VERTICAL, false)
        recyclerViewPlayers.layoutManager = layoutManager
    }

    private fun observerPlayersList() {
        networkStatusChecker.performIfConnectedToInternet {
            viewModel.playerLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    is Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Success -> {
                        binding.progressBar.visibility = View.GONE
                        playersAdapter = PlayersAdapter(it.data)
                        recyclerViewPlayers.adapter = playersAdapter
                    }
                    is Failure -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }
}

