package com.test.uts

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainAdapter.OnClickListener {

    private val mainAdapter = MainAdapter(this)

    private val dummyData = listOf(
            MyModel(1, "https://cdn0-production-images-kly.akamaized.net/Ky2M_8fM_YIA3CLytmDzGFPuJ8g=/640x360/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/791738/original/033433000_1420690296-Veneno.jpg", "Tesla", "Mobil mantap", 300, 4, 2, "mobil mantap mantap dkjfl ksjdlf jsalkdjflk ajsdlkj flakjsdlkfj alksdjflk jasdlk;jf lkajsdlkf jalsk;djfl kajsdlkfj alk;sdjflka jd"),
            MyModel(2, "https://cdns.klimg.com/otosia.com/resized/650x325/p/headline/650x325/0000477421.jpg", "Kece", "Mobil mantap", 300, 4, 2, "mobil mantap mantap dkjfl ksjdlf jsalkdjflk ajsdlkj flakjsdlkfj alksdjflk jasdlk;jf lkajsdlkf jalsk;djfl kajsdlkfj alk;sdjflka jd"),
            MyModel(3, "https://asset.kompas.com/crops/cLfv7e7kJdxdjJxqIeLGqZZMWBM=/0x0:780x390/490x326/data/photo/2015/05/05/1517162plat-aventador3-topcarrating780x390.jpg", "Honda", "Mobil mantap", 300, 4, 2, "mobil mantap mantap dkjfl ksjdlf jsalkdjflk ajsdlkj flakjsdlkfj alksdjflk jasdlk;jf lkajsdlkf jalsk;djfl kajsdlkfj alk;sdjflka jd")

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_main.adapter = mainAdapter

        mainAdapter.submitList(dummyData)
    }

    override fun onClick(item: MyModel) {
        val i = Intent(this, SecondActivity::class.java)
        i.putExtra(SecondActivity.KEY, item)
        startActivity(i)
    }
}

@Parcelize
data class MyModel(
        val id: Int,
        val gambarMobil: String,
        val namaMobil: String,
        val jenisMobil: String,
        val hargaHarian: Int,
        val jumlahTempatDuduk: Int,
        val jumlahBagasi: Int,
        val deskripsiMobil: String
) : Parcelable