package com.cis.mydatabaseapplication

interface ItemRowListener {

    fun modifyItemState(itemID: String,index: Int,status: Boolean)
    fun  onItemDelete(itemID: String,index: Int)

    //fun modifyItemState(itemObjectId: String, index: Int, isDone: Boolean) //edit
    //fun onItemDelete(itemObjectId: String, index: Int)
}