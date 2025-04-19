package com.jetbrains.greeting



class Greeting {
    private val platform = getPlatform()
    val language: String = "English"

    fun greet(): String {
        return "Hello India !!${platform.name} ${language} Yeaah boii!"
    }
}


data class User(val name: String, val age: Int){

    fun copyUser(name: String): User {
        return User(name, this.age)
    }

    fun copyUser(age: Int): User {
        return User(this.name, age)
    }

    fun copyUser(name: String, age: Int): User {
        return User(name, age)
    }
}
data class UserFactory2(val name: Int)
class UserFactory {
    companion object {
        fun createUser(): User {
            return User(name = "Cal2", age = 22)
        }
    }
}
fun main(){
    User("efd",1)
}

class FunctionWithDefaultArgumentsClass(val arg1: Int = 1) {

    fun functionWithDefaultArgument(arg2: Int = 2) {
        println(arg2)
    }

}

class MyDummy(){

}
fun myFunction(arg: String="refds",arg2: Int = 10){

}

enum class Turn {
    Left, Right
}