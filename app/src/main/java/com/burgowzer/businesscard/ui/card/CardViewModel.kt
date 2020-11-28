package com.burgowzer.businesscard.ui.card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.burgowzer.businesscard.Card
import com.burgowzer.businesscard.CardManager
import io.realm.RealmList
import io.realm.RealmResults

class CardViewModel : ViewModel(), CardManager.CardInformation {



    private var savedCardList: MutableLiveData<List<Card>>? = MutableLiveData()
    private var _text: MutableLiveData<String> = MutableLiveData()


    val cardList: LiveData<List<Card>>? = savedCardList
    val text: LiveData<String> = _text



    override fun cardInformationFiller(realmList: RealmResults<Card>?) {
        savedCardList = MutableLiveData(realmList?.toList())

        _text = MutableLiveData<String>().apply {
            value = realmList?.toList()?.last()?.firstName
        }

    }








}