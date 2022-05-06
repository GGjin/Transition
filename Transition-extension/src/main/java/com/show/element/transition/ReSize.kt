package com.show.element.transition

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.transition.Transition
import androidx.transition.TransitionValues

/**
 * @description:
 * @author: Jinyu.Guo3
 * @createDate: 2022 5.5 0005 15:18
 * @updateUser:
 * @updateDate: 2022 5.5 0005 15:18
 */
class ReSize :Transition {

    constructor()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)




    override fun captureStartValues(transitionValues: TransitionValues) {

    }

    override fun captureEndValues(transitionValues: TransitionValues) {

    }


    override fun createAnimator(sceneRoot: ViewGroup, startValues: TransitionValues?, endValues: TransitionValues?): Animator? {

        return super.createAnimator(sceneRoot, startValues, endValues)
    }
}