package com.example.kanbagisi

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import retrofit2.Call
import retrofit2.Callback
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activit_kan_ilani_ver.*

class KanilaniVerActivity: AppCompatActivity(){

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelId = "com.example.kanbagisi"
    private val description = "Test notification"



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activit_kan_ilani_ver)

        auth= FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        notificationManager= getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val customList = listOf("İl Seçiniz","Adana", "Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin",
            "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale",
            "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir",
            "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir", "Kars",
            "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa",
            "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya",
            "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak", "Van",
            "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak", "Bartın", "Ardahan",
            "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce")

       val adapter= ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, customList)
       il_spinner.adapter= adapter


        val customList2 = listOf("Kan Grubu Seçiniz","A Rh+", "A Rh-", "B Rh+", "B Rh- ", "AB Rh+", "AB Rh-","0 Rh-", "0 Rh+")
        val adapter2= ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, customList)
        il_spinner.adapter= adapter



        kan_ilani_kaydet_button.setOnClickListener {


            val il = il_spinner.selectedItem.toString()
            val adsoyad = adsoyad_kanilani_edittext.text.toString()
            val hastaneadi = hastaneadi_kanilani_edittext.text.toString()
            val kangrubu = kangrubu_spinner.selectedItem.toString()
            val telno=telefon_no_edittext.text.toString()


            if (il.isEmpty() || adsoyad.isEmpty() || hastaneadi.isEmpty() || kangrubu.isEmpty()) {
                Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
            } else {
                KanilaniKayitOl(il, adsoyad, hastaneadi, kangrubu,telno)
                val tokenReference: DatabaseReference = database.getReference("Tokens")
                tokenReference.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {

                        for(snap in snapshot.children){

                            val token = snap.value as String

                            sendNotification(token,adsoyad,"ilan paylaştı")

                        }

                    }

                })

            }

          /*  val intent =Intent (this, LauncherActivity:: class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT )

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel= NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH )
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChannel)

                builder= Notification.Builder(this, channelId)
                    .setContentTitle("CodeAndroid")
                    .setContentText("Test Notification")
                    .setSmallIcon(R.drawable.ic_launcher_round)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher))
                    .setContentIntent(pendingIntent)

            } else {
                builder = Notification.Builder(this)
                    .setContentTitle("CodeAndroid")
                    .setContentText("Test Notification")
                    .setSmallIcon(R.drawable.ic_launcher_round)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher))
                    .setContentIntent(pendingIntent)

            }

            notificationManager.notify(1234, builder.build()) */

        }
       }

            private fun KanilaniKayitOl(il: String, adsoyad: String, hastaneadi: String, kangrubu: String, telno: String) {


                            val firebaseUser = auth.currentUser
                            val userId = firebaseUser!!.uid
                            val random = (0..1000).shuffled().last()

                            reference = database.getReference("ilanlar").child(random.toString())

                            val hashMap: HashMap<String, String> = HashMap()

                            hashMap["userId"] = userId
                            hashMap["il"]=il
                            hashMap["adsoyad"] = adsoyad
                            hashMap["hastaneadi"] = hastaneadi
                            hashMap["kangrubu"] = kangrubu
                            hashMap["telno"]= telno

                            reference.setValue(hashMap)

                            val intent = Intent(this, KanilanlariActivity::class.java)
                            startActivity(intent)
                            KanilaniVerActivity().finish()

                        }


    private fun sendNotification(receiverUserID: String?, name: String?, userComment: String) {

        val tokenReference: DatabaseReference = database.getReference("Tokens")
        val query = tokenReference.orderByKey().equalTo(receiverUserID)
        query.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapShot in dataSnapshot.children) {

                    val token = snapShot.value as String

                    val data = Data(
                        auth.currentUser!!.uid,
                        userComment,
                        "İLAN GÖNDERİLDİ",
                        receiverUserID
                    )
                    val sender = Sender(data, token)

                    RetrofitAPI.api.sendNotification(sender).enqueue(object : Callback<Response> {
                        override fun onFailure(call: Call<Response>, t: Throwable) {
                        }

                        override fun onResponse(
                            call: Call<Response>,
                            response: retrofit2.Response<Response>
                        ) {
                            Log.d("VIEW_MODEL", response.message())
                        }

                    })

                }
            }

        })

    }


}











