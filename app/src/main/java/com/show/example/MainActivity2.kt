package com.show.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.show.element.transition.callback.setExtraShareElementCallBack

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)



        setExtraShareElementCallBack()


        findViewById<View>(R.id.btn2).setOnClickListener {
            finishAfterTransition()
        }

    }


}