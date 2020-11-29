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

class CardCreationFragment: Fragment(), CardManager.CardInformation {
    var realmList: RealmResults<Card>? = null
    val cardViewModel: CardViewModel by viewModels()
    lateinit var realm: Realm

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_card_creation,container,false)

        return root
    }



        private fun saveCardInformation(resultHandler: (realmList:RealmResults<Card>?,error:String?)->Unit) {


/*
        firstNameText.text.let{ newCard?.firstName = it.toString()}
        lastNameText.text.let{newCard?.lastName = it.toString()}
        jobPositionText.text.let{newCard?.jobPosition = it.toString()}
        companyNameText.text.let{newCard?.companyName = it.toString()}
        companyAddressText.text.let{newCard?.companyAddress = it.toString()}
        phoneNumberText.text.let{newCard?.phoneNumber = it.toString()}
        emailText.text.let{newCard?.email = it.toString()}*/


            val newCard:Card?= Card()


            if(firstNameText.text != null){ newCard?.firstName = firstNameText.text.toString()}
            if(lastNameText.text != null){newCard?.lastName = lastNameText.text.toString()}
            if(jobPositionText.text != null){newCard?.jobPosition= jobPositionText.text.toString()}
            if(companyNameText.text != null){newCard?.companyName = companyNameText.text.toString()}
            if(companyAddressText.text != null){newCard?.companyAddress = companyAddressText.text.toString()}
            if(phoneNumberText.text != null){newCard?.phoneNumber = phoneNumberText.text.toString()}
            if(emailText.text != null){ newCard?.email = emailText.text.toString()}



            realm.beginTransaction()
            if (newCard != null) {
                realm.copyToRealm(newCard)
                realmList = realm.where(Card::class.java).findAll()
                resultHandler(realmList, null)
                val creationMessage = Toast.makeText(this, "Card created!", Toast.LENGTH_SHORT)
                creationMessage.show()
            }
            realm.commitTransaction()



        }


        override fun cardInformationFiller(realmList: RealmResults<Card>?) {

        }

        private fun loadCardList (client: CardManager.CardInformation){
            saveCardInformation { realmList, error->
                client.cardInformationFiller(realmList)
            }
        }



    override fun onResume() {
        super.onResume()

        createCardButton
                .setOnClickListener { loadCardList(cardViewModel)
                        .also { endCardCreationActivity()}
                }

        discardCardButton
                .setOnClickListener {endCardCreationActivity()}
    }

    private fun endCardCreationActivity(){
        findNavController().navigate(R.id.action_cardCreationFragment_to_navigation_cards)
    }
}
