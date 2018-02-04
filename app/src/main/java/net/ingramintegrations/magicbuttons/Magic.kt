package net.ingramintegrations.magicbuttons

import android.view.View

/**
 * Kotlin file with a method to expand or collapse the MagicButton
 * Author : Rajanikant
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
fun doWith(view : View) {
    var animation : MagicAnimation
animation = MagicAnimation(view)
animation.duration = 200
view.startAnimation(animation)
}