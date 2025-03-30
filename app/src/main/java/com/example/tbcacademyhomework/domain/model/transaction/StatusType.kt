package com.example.tbcacademyhomework.domain.model.transaction

enum class StatusType {
    Success,
    Failure;
    companion object{
        fun getStatus(status:String):StatusType{
            return when(status){
                "Success" ->Success
                else ->Failure
            }
        }
    }
}