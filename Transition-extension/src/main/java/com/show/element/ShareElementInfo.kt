package com.show.element

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.parcelize.Parcelize

@Parcelize
class ShareElementInfo(
    var snapShot: Parcelable,
    var backgroundColor: Int? = null,
    var textColor: Int? = null,
    var cardRadius: Float? = null,
    var colorStateList: ColorStateList? = null
) : Parcelable {

    companion object {

        @JvmStatic
        fun captureViewInfo(snapShot: Parcelable, view: View?): Parcelable {
            var color: Int? = null
            if (view != null && view.background is ColorDrawable?) {
                color = (view.background as ColorDrawable?)?.color
            }
            var textColor: Int? = null
            if (view is TextView) {
                textColor = view.currentTextColor
            }
            var radius: Float? = when (view) {
                is CardView -> view.radius
                is MaterialButton -> view.cornerRadius.toFloat()
                is RadiusImageView -> view.radius.toFloat()
                is RoundImageView -> view.radius.toFloat()

                else -> null
            }


            return ShareElementInfo(snapShot, color, textColor, radius, view?.backgroundTintList)
        }
    }


}