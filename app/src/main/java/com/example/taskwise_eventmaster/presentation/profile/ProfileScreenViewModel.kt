package com.example.taskwise_eventmaster.presentation.profile

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.imageLoader
import com.example.taskwise_eventmaster.domain.service.authorization.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val authService: AuthService,
    @ApplicationContext context: Context,
) : ViewModel() {
    var state by mutableStateOf(ProfileScreenState())
        private set

    private val imageLoader: ImageLoader

    init {
        imageLoader = context.imageLoader

        state = state.copy(
            userData = authService.getSignedInUser()
        )
    }

    fun onEvent(event: ProfileScreenEvent) = when (event) {
        is ProfileScreenEvent.OnSignOut -> signOut()
    }

    private fun signOut() {
        clearCacheEventThumbnails().invokeOnCompletion {
            viewModelScope.launch {
                authService.signOut()

                state = state.copy(
                    userData = null
                )
            }
        }
    }

    private fun clearCacheEventThumbnails() =
        viewModelScope.launch {

            clearingDiskCache().invokeOnCompletion {
                clearingMemoryCache()
            }
        }

    private fun clearingDiskCache() =
        viewModelScope.launch {
            imageLoader.memoryCache?.clear()
        }


    @OptIn(ExperimentalCoilApi::class)
    private fun clearingMemoryCache() =
        viewModelScope.launch {
            imageLoader.diskCache?.clear()
        }

}
