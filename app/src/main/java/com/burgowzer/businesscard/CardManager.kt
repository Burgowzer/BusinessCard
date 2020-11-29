package com.burgowzer.businesscard

import io.realm.RealmResults

open class CardManager {


    interface CardInformation {
        fun cardInformationFiller(realmList: RealmResults<Card>?)
    }



}