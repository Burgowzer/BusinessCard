package com.burgowzer.businesscard.ui.card

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burgowzer.businesscard.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_card.*

open class CardFragment : Fragment() {

    var adapter: Adapter? = Adapter()
    val cardViewModel: CardViewModel by viewModels()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ):View? {



        val root = inflater.inflate(R.layout.fragment_card, container, false)


        cardViewModel.cardList?.observe(viewLifecycleOwner, Observer {
            adapter?.loadCardList(it)
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(adapter?.adapterList != null){
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL ,false)
            //recyclerView.addItemDecoration(DividerItemDecoration(this.context,DividerItemDecoration.HORIZONTAL))
        }
        else empty_list.text = " No card registered"
    }




    override fun onResume() {
        super.onResume()


        button_add_card.setOnClickListener { startCardCreationActivity()}
        this.clearFindViewByIdCache()

    }

    private fun startCardCreationActivity(){
        findNavController().navigate(R.id.action_navigation_cards_to_cardCreationFragment)

    }
}