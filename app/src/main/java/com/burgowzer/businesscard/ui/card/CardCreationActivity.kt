package com.burgowzer.businesscard.ui.card

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.burgowzer.businesscard.Card
import com.burgowzer.businesscard.CardManager
import com.burgowzer.businesscard.MainActivity
import com.burgowzer.businesscard.R
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_card_creation.*
import kotlinx.android.synthetic.main.card_adapter.*

class CardCreationActivity : AppCompatActivity(),CardManager.CardInformation {

    var realmList:RealmResults<Card>? = null
    val cardViewModel: CardViewModel by viewModels()
    lateinit var realm:Realm


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_creation)
        realm  = Realm.getDefaultInstance()
    }


    private fun saveCardInformation(resultHandler: (realmList:RealmResults<Card>?,error:String?)->Unit) {

        val newCard:Card?= Card()

/*
        firstNameText.text.let{ newCard?.firstName = it.toString()}
        lastNameText.text.let{newCard?.lastName = it.toString()}
        jobPositionText.text.let{newCard?.jobPosition = it.toString()}
        companyNameText.text.let{newCard?.companyName = it.toString()}
        companyAddressText.text.let{newCard?.companyAddress = it.toString()}
        phoneNumberText.text.let{newCard?.phoneNumber = it.toString()}
        emailText.text.let{newCard?.email = it.toString()}*/




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

    private fun loadCardList (client: CardManager.CardInformation){
        saveCardInformation { realmList, error->
            client.cardInformationFiller(realmList)
        }
    }
/*
    private fun startCardFragmentActivity(){
        val intent:Intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }*/

    override fun cardInformationFiller(realmList: RealmResults<Card>?) {

    }

    /*fun createButtonClicked(button:View){
        loadCardList(cardViewModel)
                .also { finish() }

    }*/

    override fun onResume() {
        super.onResume()
        createCardButton
                .setOnClickListener { loadCardList(cardViewModel)
                        .also { val lastCard = realmList?.last()
                        textWindow.text = lastCard?.firstName}
        }

        discardCardButton
                .setOnClickListener {
                    val intent:Intent = Intent(this, MainActivity::class.java)
                    startActivity(intent) }
    }


}