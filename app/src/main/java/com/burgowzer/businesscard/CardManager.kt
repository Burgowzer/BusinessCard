package com.burgowzer.businesscard

import com.burgowzer.businesscard.ui.card.CardCreationActivity
import com.burgowzer.businesscard.ui.card.CardFragment
import io.realm.RealmList
import io.realm.RealmResults

open class CardManager {


    interface CardInformation {
        fun cardInformationFiller(realmList: RealmResults<Card>?)
    }



}