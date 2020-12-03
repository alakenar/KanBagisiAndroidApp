package com.example.kanbagisi

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activit_kan_ilani_ver.*

class KanilaniVerActivity: AppCompatActivity(){

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activit_kan_ilani_ver)

        auth= FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

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

            }
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


            }











