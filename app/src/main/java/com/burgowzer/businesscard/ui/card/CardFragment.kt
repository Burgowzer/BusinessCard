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
import androidx.recyclerview.widget.LinearLayoutManager
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

        val textView: TextView = root.findViewById(R.id.textView)
/*
        if (cardViewModel.cardList == null){
            textView.text = getString(R.string.no_card_available) }
         else{*/

            cardViewModel.cardList?.observe(viewLifecycleOwner, {
                adapter?.loadCardList(it)
            })

        cardViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })


        return root
    }

    private fun startCardCreationActivity(){
        val intent:Intent = Intent(this.context,CardCreationActivity::class.java)
        startActivity(intent)
    }


    override fun onResume() {
        super.onResume()

        if(adapter?.adapterList != null){
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this.context)
        }

        button_add_card.setOnClickListener { startCardCreationActivity()}
        this.clearFindViewByIdCache()

    }
}