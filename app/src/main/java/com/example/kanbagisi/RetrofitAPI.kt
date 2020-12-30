package com.example.kanbagisi

    import okhttp3.OkHttpClient
    import retrofit2.Retrofit
    import retrofit2.converter.gson.GsonConverterFactory

    class RetrofitAPI {
        companion object{

            private val retrofit by lazy {

                val client = OkHttpClient.Builder()
                    .build()

                Retrofit.Builder()
                    .baseUrl("https://fcm.googleapis.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            }

            val api by lazy {
                retrofit.create(APIClient::class.java)
            }
        }
    }
