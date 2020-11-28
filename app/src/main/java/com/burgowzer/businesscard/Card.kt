package com.burgowzer.businesscard

import io.realm.RealmObject
import io.realm.annotations.RealmClass
import java.io.Serializable

@RealmClass

open class Card(var firstName:String?= null,
                var lastName:String?= null,
                var jobPosition:String?= null,
                var companyName:String?= null,
                var companyAddress:String?=null ,
                var phoneNumber:String?=null,
                var email:String?=null): RealmObject() {



}