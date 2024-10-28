package com.example.gmail.Models

data class GmailModel(var name: String, var content: String, var time:String, var avatarResId: Int){
    var selected: Boolean = false
    fun toggleSelected() {
        selected = !selected
    }
}