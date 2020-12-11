package com.burgowzer.businesscard.ui.card

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.burgowzer.businesscard.Card
import com.burgowzer.businesscard.CardManager
import com.burgowzer.businesscard.MainActivity
import com.burgowzer.businesscard.R
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_card_creation.*

class CardCreationFragment: Fragment() {
    var realmList: RealmResults<Card>? = null
    var realm: Realm = Realm.getDefaultInstance()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_card_creation,container,false)

        return root
    }


    override fun onResume() {
        super.onResume()

        createCardButton
                .setOnClickListener { saveCardInformation()
                        .also { endCardCreationActivity()}
                }

        discardCardButton
                .setOnClickListener {endCardCreationActivity()}
    }

    private fun endCardCreationActivity(){
        findNavController().navigate(R.id.action_cardCreationFragment_to_navigation_cards)
    }

    private fun saveCardInformation() {


        val newCard:Card?= Card()


        if(firstNameText.text != null){ newCard?.firstName = firstNameText.text.toString()}
        if(lastNameText.text != null){newCard?.lastName = lastNameText.text.toString()}
        if(jobPositionText.text != null){newCard?.jobPosition= jobPositionText.text.toString()}
        if(companyAddressText.text != null){newCard?.companyAddress = companyAddressText.text.toString()}
        if(phoneNumberText.text != null){newCard?.phoneNumber = phoneNumberText.text.toString()}
        if(emailText.text != null){ newCard?.email = emailText.text.toString()}


        realm.beginTransaction()
        if (newCard != null) {
            realm.copyToRealm(newCard)
            realmList = realm.where(Card::class.java).findAll()
        }
        realm.commitTransaction()

    }

}


