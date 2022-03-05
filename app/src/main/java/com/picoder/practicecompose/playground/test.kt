package com.picoder.practicecompose.playground

fun main() {
    initName("Michael") { name ->
        this.name = name
        println("setFun just done")
    }
}

data class Item(var name: String = "")

fun initName(name: String, setFunExt: Item.(String) -> Unit) {
    val item = Item()
    item.setFunExt(name)
    println("Name just set is ${item.name}")
}