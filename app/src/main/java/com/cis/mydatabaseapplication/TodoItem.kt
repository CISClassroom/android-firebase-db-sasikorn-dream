package com.cis.mydatabaseapplication

class ToDo {
    companion object Factory {
        fun create(): ToDo = ToDo()
    }

    var objectId: String? = null
    var todoText: String? = null
    var done: Boolean? = false
}