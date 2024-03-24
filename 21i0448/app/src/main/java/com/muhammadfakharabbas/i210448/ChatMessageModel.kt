package com.muhammadfakharabbas.i210448

data class ChatMessageModel(var msgId:String,var senderId:String = "", var receiverId:String = "", var message:String = "",var messagetype:String = "", var imageURL:String = "",var audioURL:String = "",var videoURL:String = "",var timestamp: Int)//Timestamp?)
{
    constructor():this("","","", "","","","", "",0)
}

