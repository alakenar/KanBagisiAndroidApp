package com.example.kanbagisi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_hakkimizda.*

class HakkimizdaActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_hakkimizda)


        textHakkimizda.text= "Kanat Ol uygulamasının yaratıcısı olarak amacımız insanlara daha hızlı kan ihtiyaçlarına ulaşımını sağlamak. " +
                "Kan bağışının can kurtardığınjn bilincindeyiz, bu bilinçle çağımıza uygun bir uygulama oluşturduk." +
                " Her kan bağışının bir can kurtarabileceğini unutma! Yeni bir yaşam şansı için KANAT OL!"
        textHakkimizda2.text= "Fikir ve görüşleriniz için kanatolapp@gmail.com adresinden bizlere ulaşabilirsiniz."

    }
}