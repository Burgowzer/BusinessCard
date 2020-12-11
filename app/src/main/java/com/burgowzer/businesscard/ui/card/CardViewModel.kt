package com.burgowzer.businesscard.ui.card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.burgowzer.businesscard.Card
import com.burgowzer.businesscard.CardManager
import io.realm.Realm
import io.realm.RealmResults


class CardViewModel : ViewModel() {


    val realm: Realm = Realm.getDefaultInstance()


    val realmQuery:RealmResults<Card>? = realm.where(Card::class.java).findAll()

    private var savedCardList: MutableLiveData<List<Card>>? = MutableLiveData(realmQuery?.toList())

    val cardList: LiveData<List<Card>>? = savedCardList


}