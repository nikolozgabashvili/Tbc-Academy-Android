package com.example.tbcacademyhomework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.reflect.Type

class MessagesViewModel : ViewModel() {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val type: Type = Types.newParameterizedType(List::class.java, MessageDto::class.java)
    private val jsonAdapter: JsonAdapter<List<MessageDto>> = moshi.adapter(type)
    private val messages = jsonAdapter.fromJson(JSON_OBJECT)?.map { it.toMessageUi() }

    private val _messagesFlow: MutableStateFlow<List<MessagesUi>?> =
        MutableStateFlow(messages)

    val messagesFlow = _messagesFlow.asStateFlow()


    fun search(query: String) {
        viewModelScope.launch {
            val newMessages = messages?.filter { it.owner.contains(query) }
            _messagesFlow.emit(newMessages?.toList())
        }
    }


    companion object {
        private const val JSON_OBJECT = """
            
            [
{
"id":1,
"image":"https://www.alia.ge/wp-content/uploads/2022/09/grisha.jpg",
"owner":"გრიშა ონიანი",
"last_message":"თავის ტერიტორიას ბომბავდა",
"last_active":"4:20 PM",
"unread_messages":3,
"is_typing":false,
"laste_message_type":"text"
},
{
"id":2,
"image":null,
"owner":"ჯემალ კაკაურიძე",
"last_message":"შემოგევლე",
"last_active":"3:00 AM",
"unread_messages":0,
"is_typing":true,
"laste_message_type":"voice"
},
{
"id":3,
"image":"https://i.ytimg.com/vi/KYY0TBqTfQg/hqdefault.jpg",
"owner":"გურამ ჯინორია",
"last_message":"ცოცხალი ვარ მა რა ვარ შე.. როდის იყო კვტარი ტელეფონზე ლაპარაკობდა",
"last_active":"1:00 ",
"unread_messages":0,
"is_typing":false,
"laste_message_type":"file"
},
{
"id":4,
"image":"",
"owner":"კაკო წენგუაშვილი",
"last_message":"ადამიანი რო მოსაკლავად გაგიმეტებს თანაც ქალი ის დასანდობი არ არი",
"last_active":"1:00 PM",
"unread_messages":0,
"is_typing":false,
"laste_message_type":"text"
}
]
        """
    }
}