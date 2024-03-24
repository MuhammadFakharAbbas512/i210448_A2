package com.muhammadfakharabbas.i210448
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import de.hdodenhof.circleimageview.CircleImageView

class ChatAdapter(private val context: Context, private val chatList: ArrayList<ChatMessageModel>) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    private val MESSAGE_TYPE_LEFT = 0
    private val MESSAGE_TYPE_RIGHT = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutId = if (viewType == MESSAGE_TYPE_RIGHT) R.layout.item_right else R.layout.item_left
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chat = chatList[position]
        holder.txtUserName.text = chat.message
        val user = FirebaseAuth.getInstance().currentUser!!

        // Load user's profile image
        if (chatList[position].senderId == FirebaseAuth.getInstance().currentUser?.uid) {
            val currentUser = FirebaseAuth.getInstance().currentUser
            val databaseReference = FirebaseDatabase.getInstance().getReference("users").child(
                currentUser?.uid.toString()
            )
            databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userProfile = snapshot.getValue(User::class.java)
                    val profileImageUrl = userProfile?.profileImage
                    if (!profileImageUrl.isNullOrEmpty()) {
                        Glide.with(context).load(profileImageUrl).placeholder(R.drawable.profile_image)
                            .into(holder.imgUser)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle database error
                }
            })
        }

        // Load images for image messages
        if (chat.messagetype == "image") {
            Glide.with(context).load(chat.imageURL).placeholder(R.drawable.profile_image).into(holder.imgMessage)
            holder.imgMessage.visibility = View.VISIBLE
        } else {
            holder.imgMessage.visibility = View.GONE
        }

        // Handle long click events for deleting messages
        holder.itemView.setOnLongClickListener {
            val timer = System.currentTimeMillis() - chat.timestamp.toLong()
            val validTime = timer < (5 * 60 * 1000)
            val popup = PopupMenu(context,holder.itemView)
            popup.inflate(R.menu.item_menu)
/*            popup.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.delete -> {
                        val ref = FirebaseDatabase.getInstance().getReference("Chat").child(chatList[position].msgId)
                        ref.removeValue()
                            .addOnSuccessListener {
                                // deleted successfully
                            }
                            .addOnFailureListener {
                                //failed to delete
                            }
                    }

                    else -> {

                    }
                }
            }
            chatList.removeAt(position)
            notifyDataSetChanged()
            true*/
            R.id.edit
            val ref = FirebaseDatabase.getInstance().getReference("Chat").child(chatList[position].msgId).child("message")
            //showEditMessagePopup()

            chatList.removeAt(position)
            notifyDataSetChanged()
            true
        }
        holder.audioMessage.setOnClickListener{

            val databaseReference = FirebaseDatabase.getInstance().getReference("Chat").child("audio")
            var uri = ""
            databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val chat = snapshot.getValue(ChatMessageModel::class.java)
                    if (chat != null) {
                        uri = chat.audioURL.toString()
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    //
                    Toast.makeText(context, "database error: error.message", Toast.LENGTH_SHORT).show()
                }})


           /* val mediaPlayer = MediaPlayer.create(this, uri)
                if (mediaPlayer!!.isPlaying){
                    mediaPlayer!!.pause()
                }               else {

                }*/
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtUserName: TextView = view.findViewById(R.id.tvMessage)
        val imgUser: CircleImageView = view.findViewById(R.id.sendImage)
        val imgMessage: ImageView = view.findViewById(R.id.imgMsg)
        val audioMessage: Button = view.findViewById(R.id.audio)

        val right = view.findViewById<LinearLayout>(R.id.layoutUser)
        val left = view.findViewById<LinearLayout>(R.id.layoutRecv)
    }

    override fun getItemViewType(position: Int): Int {
        if (chatList[position].senderId == FirebaseAuth.getInstance().currentUser?.uid) {
            return MESSAGE_TYPE_RIGHT
        } else {
            return MESSAGE_TYPE_LEFT
        }
    }
}
