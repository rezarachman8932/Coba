package com.project.coba.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.coba.data.local.dao.ContentDao
import com.project.coba.data.local.entity.ContentEntity

@Database(
    entities = [ContentEntity::class],
    version = 1
)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun contentDao(): ContentDao

    companion object {
        private const val DATABASE_VERSION = "_db_v1"

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context): AppRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    context.packageName + DATABASE_VERSION
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}