package com.muhammadfakharabbas.i210448

import com.google.firebase.Timestamp

data class MentorModel(val mid:String, val name:String, val description:String, val status: String,  var dpUrl:String, val fee:String  ,  var timestamp: Timestamp?)
{ // later include about, rate and review
    constructor():this("","","", "", "", "",null)
}
