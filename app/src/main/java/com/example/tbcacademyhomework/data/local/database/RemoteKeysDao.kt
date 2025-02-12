package com.example.tbcacademyhomework.data.local.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface RemoteKeysDao {

    @Query("SELECT * FROM userkeys WHERE id =:id")
    suspend fun getRemoteKeys(id: Int): UserRemoteKeysEntity

    @Upsert
    suspend fun addAllRemoteKeys(remoteKeys: List<UserRemoteKeysEntity>)

    @Query("DELETE FROM userKeys")
    suspend fun deleteAllRemoteKeys()
}