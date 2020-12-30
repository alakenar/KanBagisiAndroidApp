package com.example.kanbagisi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class Veriler ( var adsoyad: String="",
                var hastaneadi: String="",
                var kangrubu: String= "",
                var il: String="",
                var telno: String="" ,
                var userId: String=""):Parcelable {

}

