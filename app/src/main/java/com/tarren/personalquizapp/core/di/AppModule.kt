package com.tarren.personalquizapp.core.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.tarren.personalquizapp.core.service.AuthService
import com.tarren.personalquizapp.core.service.StorageService
import com.tarren.personalquizapp.data.repo.UserRepo
import com.tarren.personalquizapp.data.repo.UserRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// This is the Dagger Hilt module used for providing dependencies for the application.
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    // Provides a singleton instance of AuthService for the application context.
    @Provides
    @Singleton
    fun provideAuth(@ApplicationContext context: Context): AuthService {
        return AuthService()
    }

    // Provides a singleton instance of StorageService.
    @Provides
    @Singleton
    fun provideStorageService(): StorageService {
        return StorageService()
    }

    // Provides a singleton DatabaseReference for Firebase Realtime Database.
    @Provides
    @Singleton
    fun provideFirebaseRealtimeRef(): DatabaseReference {
        return FirebaseDatabase.getInstance().getReference("todos")
    }

    // Provides a singleton FirebaseFirestore instance.
    @Provides
    @Singleton
    fun provideFirebaseTodosCollection(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    // Provides a singleton instance of UserRepo implemented with Firestore.
    @Provides
    @Singleton
    fun provideUserRepoFirestore(db: FirebaseFirestore): UserRepo {
        return UserRepoImpl(db.collection("users"))
    }
}
