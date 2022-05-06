package com.show.element.transition

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Outline
import android.transition.Transition
import android.transition.TransitionValues
import android.util.AttributeSet
import android.util.Log
import android.util.Property
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.cardview.widget.CardView
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import com.show.element.*

class ReRadius : Transition {

    constructor()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    private val radius = "ReRadius:radius"

    init {
        addTarget(CardView::class.java)
        addTarget(MaterialButton::class.java)
        addTarget(RadiusImageView::class.java)
        addTarget(ShapeableImageView::class.java)
    }

    override fun getTransitionProperties(): Array<String> {
        return arrayOf(radius)
    }

    override fun captureStartValues(transitionValues: TransitionValues?) {
        if (transitionValues == null) return
        val view = transitionValues.view
        val info = view.getTag(R.id.tag_transition_extra_properties) as ShareElementInfo?
        if (info != null) {
            captureInfoValues(info, transitionValues)
        } else {
            captureValues(transitionValues)
        }
    }

    override fun captureEndValues(transitionValues: TransitionValues?) {
        if (transitionValues == null) return
        val view = transitionValues.view
        val info = view.getTag(R.id.tag_transition_extra_properties) as ShareElementInfo?
        if (info != null) {
            captureInfoValues(info, transitionValues)
        } else {
            captureValues(transitionValues)
        }
    }

    private fun captureInfoValues(info: ShareElementInfo, transitionValues: TransitionValues) {
        transitionValues.values[radius] = info.cardRadius
    }

    private fun captureValues(transitionValues: TransitionValues?) {
        if (transitionValues == null) return
        Log.w("transitionValues.view", transitionValues.view.transitionName)
        when (transitionValues.view) {
            is CardView -> {
                transitionValues.values[radius] = (transitionValues.view as CardView).radius
            }
            is MaterialButton -> {
                transitionValues.values[radius] = (transitionValues.view as MaterialButton).cornerRadius
            }
            is RadiusImageView -> {
                Log.w("-----", "+++++")
                transitionValues.values[radius] = (transitionValues.view as RadiusImageView).radius
            }

            is ShapeableImageView -> {
                transitionValues.values[radius] = (transitionValues.view as ShapeableImageView).shapeAppearanceModel.bottomLeftCorner

            }
            is RoundImageView -> {
                Log.w(" RoundImageView.radius", "--" + (transitionValues.view as RoundImageView).radius)
                transitionValues.values[radius] = (transitionValues.view as RoundImageView).radius

            }

            else -> return
        }

    }

    private fun Any?.toFloat(): Float? {
        if (this is Int?) {
            return this?.toFloat()
        } else if (this is Float?) {
            return this
        }
        return null
    }


    override fun createAnimator(
        sceneRoot: ViewGroup?,
        startValues: TransitionValues,
        endValues: TransitionValues
    ): Animator? {

        val startRadius = startValues.values[radius].toFloat() ?: 0f
        val endRadius = endValues.values[radius].toFloat() ?: 0f
        Log.w("startRadius", "--" + startRadius)
        Log.w("endRadius", "--" + endRadius)
        var animator1: Animator? = null
        if (startRadius != null && endRadius != null && startRadius != endRadius) {
            animator1 = ObjectAnimator.ofFloat(endValues.view, RadiusProperty(), startRadius, endRadius)
        }
        return animator1
//        var animator2: Animator? = null
//        var animator3: Animator? = null
//        if (startRadius ?: 0f > 0f) {
//            animator2 = ObjectAnimator.ofFloat(startValues.view, "scaleX", 1f, 1f)
//            animator3 = ObjectAnimator.ofFloat(startValues.view, "scaleY", 1f, 1f)
//        } else if (endRadius ?: 0f > 0f) {
//            animator2 = ObjectAnimator.ofFloat(startValues.view, "scaleX", 1f, 1f)
//            animator3 = ObjectAnimator.ofFloat(startValues.view, "scaleY", 1f, 1f)
//        }
//        return AnimatorUtil.merge(animator1, animator2, animator3)
    }

    private class RadiusProperty(
        type: Class<Float>? = Float::class.java,
        name: String? = "radius"
    ) :
        Property<View, Float>(type, name) {

        override fun set(view: View?, value: Float) {

            when (view) {
                is CardView -> {

                    view.radius = value
                }

                is MaterialButton -> {
                    view.cornerRadius = value.toInt()

                }

                is RadiusImageView -> {
                    view.radius = value.toInt()

                }

                is ShapeableImageView -> {
                    view.outlineProvider = object : ViewOutlineProvider() {
                        override fun getOutline(view: View, outline: Outline) {
                            outline.setRoundRect(0, 0, view.width, view.height, value)
                        }
                    }
                    view.clipToOutline = true
                }
                is RoundImageView -> {
                    view.radius = value.toInt()

                }

            }

        }

        override fun get(view: View?): Float {
            return 0f
        }

    }


}