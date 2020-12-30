package com.example.kanbagisi

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIClient {

    @Headers(
        "Content-Type:application/json",
        "Authorization:key=AAAA2PtSZ7k:APA91bGik8vwQ4zNgVVpkXXoT2O_p_-ro6m2fQR8hZoPOBdfNmlKq-ft9UtgXq2hn3W7zKvshP9xUkHdH1_VJyLan2oCJF5YUAV3x5l1Mett8_dDwnr95PiuBoOq1CmxiIEHUFYgcKDX"
    )

    @POST("fcm/send")
    fun sendNotification(@Body body: Sender): Call<Response>

}

data class Data(
    val user: String? = "",
    val body: String? = "",
    val title: String? = "",
    val sent: String? = ""
)

data class Response(
    val success: String
)

data class Sender(
    val data: Data? = null,
    val to: String? = ""
)