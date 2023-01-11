package com.geektech.marvelapp.ui.auth

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geektech.marvelapp.domain.model.AuthInfo
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val auth: FirebaseAuth) : ViewModel() {
    private var regResultMutable: MutableLiveData<AuthInfo> = MutableLiveData()
    private var authPasswordResultMutable: MutableLiveData<AuthInfo> = MutableLiveData()
    private var exceptionMessageMutable: MutableLiveData<String> = MutableLiveData()

    val regResultLive: LiveData<AuthInfo> = regResultMutable
    val authPasswordResultLive: LiveData<AuthInfo> = authPasswordResultMutable
    val exceptionMessageLive: LiveData<String> = exceptionMessageMutable

    fun regNewUser(email: String, password: String, activity: FragmentActivity) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { reg ->
                if (reg.isSuccessful) {
                    regResultMutable.value = AuthInfo(true)
                } else {
                    regResultMutable.value = AuthInfo(true, reg.exception.toString())
                }
            }
    }

    fun passwordAuth(email: String, password: String, activity: FragmentActivity) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { login ->
                if (login.isSuccessful) {
                    authPasswordResultMutable.value = AuthInfo(true)
                } else {
                    authPasswordResultMutable.value = AuthInfo(false, login.exception.toString())
                }
            }
    }

    fun googleAuth(){}

    fun checkPasswordAuth(message: String) {
        when (message) {
            "com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: The password is invalid or the user does not have a password." ->
                exceptionMessageMutable.value = "Invalid password"
        }
    }
}