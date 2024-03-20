package com.muhammadfakharabbas.i210448

import com.google.firebase.Timestamp

data class ChatMessageModel(var senderId:String = "", var receiverId:String = "", var message:String = "", var timestamp: Timestamp?)
{
    constructor():this("","", "", null)
}

