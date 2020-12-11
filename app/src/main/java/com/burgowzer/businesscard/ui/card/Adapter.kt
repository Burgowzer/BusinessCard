package com.burgowzer.businesscard.ui.card

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.burgowzer.businesscard.Card
import com.burgowzer.businesscard.R
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_single_card.view.*

class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>() {


    var adapterList:List<Card>? = listOf()

    fun loadCardList(list: List<Card>) {
        adapterList = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_single_card,parent,false)

        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (adapterList != null) {
            val card = adapterList!![position]
            card.let { holder.cardDataFiller(it) }
        }

    }

    override fun getItemCount():Int {
        if (adapterList != null) {
            return adapterList!!.size
        }
        
        return 0
    }

    inner class ViewHolder(root:View): RecyclerView.ViewHolder(root){


        private val firstNameValue = root.firstName
        private val lastNameValue = root.lastName
        private val jobPositionValue = root.jobPosition
        private val companyAddressValue = root.companyAddress
        private val phoneNumberValue = root.phoneNumber
        private val emailValue = root.eMail

        fun cardDataFiller(card:Card){

            firstNameValue.text = card.firstName
            lastNameValue.text = card.lastName
            jobPositionValue.text = card.jobPosition
            companyAddressValue.text = card.companyAddress
            phoneNumberValue.text = card.phoneNumber
            emailValue.text = card.email

        }
    }

}