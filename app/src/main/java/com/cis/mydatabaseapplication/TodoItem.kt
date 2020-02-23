package com.cis.mydatabaseapplication

class TodoItem{

    companion object Factory{
        fun create():TodoItem=TodoItem()
    }
    var objectID:String?=null
    var todoName:String?=null
    var status:Boolean?=null
}