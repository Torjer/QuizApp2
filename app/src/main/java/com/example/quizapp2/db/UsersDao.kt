package com.example.quizapp2.db

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM users ORDER BY id")
    fun getUsers() : List<User>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUsers(id : Int) : User

    @Query("SELECT * FROM users WHERE username LIKE :text ORDER BY username")
    fun getUsers(text : String) : List<User>

    @Query("SELECT * FROM users WHERE id >= :min AND id <= :max")
    fun getUsersByIdRange(min : Int, max : Int) : List<User>

    @Query("SELECT * FROM users WHERE id IN (:idArray)")
    fun getUsersByIdArray(idArray : Array<Int>) : List<User>

    @Update
    fun updateUser(user:User)

    @Update
    fun updateUser(user:List<User>)

    @Query("INSERT INTO users(id, username, selected) VALUES(:id, :username, :selected)")
    fun insertUser(id: Int, username:String, selected:Int)

    @Insert
    fun insertUser(user:User)

    @Insert
    fun insertUser(user:List<User>)



    @Delete
    fun deleteUser(user:User)

    @Delete
    fun deleteUser(user:List<User>)
}