package com.jetbrains.greeting

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()
actual val num: Int = 43

fun accessCommonMainGreetingFunction(){
    val greeting = Greeting()
    greeting.greet()
}