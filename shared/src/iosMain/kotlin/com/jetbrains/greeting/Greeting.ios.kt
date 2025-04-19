package com.jetbrains.greeting

object GreetingFactory {
    fun provideGreeting(): Greeting {
        return Greeting()
    }
}
