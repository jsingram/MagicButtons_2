package net.ingramintegrations.magicbuttons

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

/**
 * Created by bloder on 20/09/16.
 */

/**
 * Kotlin class to create an animation to expand or collapse the MagicButton
 *
 * To learn more about Kotlin, check out the websites below:
 * Kotlin Info From developer.android.com
 * https://developer.android.com/kotlin/index.html
 *
 * Try Kotlin online at:
 * https://try.kotlinlang.org/#/Examples/Hello,%20world!/Simplest%20version/Simplest%20version.kt
 *
 * Info From Wikipedia
 * https://en.wikipedia.org/wiki/Kotlin_(programming_language)
 *
 */


class MagicAnimation(view : View) : Animation() {

    private var view : View? = view
    private val startWidth : Int = view.width
    private val toWidth : Int = if (startWidth == view.height) startWidth * 4 else view.height

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        val newWidth : Int = startWidth + ((toWidth - startWidth) * interpolatedTime).toInt()
        view?.layoutParams?.width = newWidth
        view?.requestLayout()
    }
}