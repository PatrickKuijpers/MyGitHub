/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.tcilegnar.mygithub.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nl.tcilegnar.mygithub.model.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = true
)
abstract class GithubDb : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: GithubDb? = null

        fun getDatabase(context: Context): GithubDb {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = create(context.applicationContext)
                INSTANCE = instance
                return instance
            }
        }

        fun create(context: Context): GithubDb {
            return return Room
                .databaseBuilder(context, GithubDb::class.java, "github.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
