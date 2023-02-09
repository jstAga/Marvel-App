package com.geektech.marvelapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.geektech.marvelapp.R
import com.geektech.marvelapp.databinding.FragmentAuthBinding
import com.geektech.marvelapp.utils.Constants
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


@Suppress("DEPRECATION")
class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: `GoogleSignInClient`
    private val auth = FirebaseAuth.getInstance()

    //hash maps for find exceptions
    private val emailExceptionDict =
        hashMapOf("com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: The email address is badly formatted." to "Invalid email",
        "com.google.firebase.auth.FirebaseAuthUserCollisionException: The email address is already in use by another account." to "The email address is already in use by another account.")
    private val passwordExceptionDict =
        hashMapOf("com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: The password is invalid or the user does not have a password." to "Invalid password",
        "com.google.firebase.auth.FirebaseAuthWeakPasswordException: The given password is invalid. [ Password should be at least 6 characters ]" to "Password should be at least 6 characters")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicker()
    }

    private fun initClicker() {
        passwordAuth()
        openIncludeReg()
        googleAuth()
    }


    private fun openIncludeReg() {
        with(binding) {
            inAuth.btnRegistration.setOnClickListener {
                authContainer.visibility = View.GONE
                regContainer.visibility = View.VISIBLE
            }
        }
        signUp()
    }

    private fun signUp() {
        with(binding.inReg) {
            btnReg.setOnClickListener {
                if (etEmail.text.toString().isNotEmpty() && etPassword.text.toString()
                        .isNotEmpty()
                ) {
                    auth.createUserWithEmailAndPassword(
                        etEmail.text.toString(),
                        etPassword.text.toString()
                    )
                        .addOnCompleteListener(requireActivity()) { reg ->
                            if (reg.isSuccessful) {
                                findNavController().navigate(R.id.homeFragment)
                            } else {
                                ifException(reg.exception.toString(), etEmail, etPassword)
                                Log.e("aga", "passwordAuth: " + reg.exception.toString())
                            }
                        }
                } else {
                    ifEmpty(etEmail, etPassword)
                }
            }
        }
    }

    private fun passwordAuth() {
        with(binding.inAuth) {
            btnLogin.setOnClickListener {
                if (etEmail.text.toString().isNotEmpty() && etPassword.text.toString()
                        .isNotEmpty()
                ) {
                    auth.signInWithEmailAndPassword(
                        etEmail.text.toString(),
                        etPassword.text.toString()
                    )
                        .addOnCompleteListener(requireActivity()) { login ->
                            if (login.isSuccessful) {
                                findNavController().navigate(R.id.homeFragment)
                            } else {
                                Log.e("aga", "passwordAuth: " + login.exception.toString())
                                ifException(login.exception.toString(), etEmail, etPassword)
                            }
                        }
                } else {
                    ifEmpty(etEmail, etPassword)
                }
            }
        }
    }

    private fun googleAuth() {
        // create request
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        gsc = GoogleSignIn.getClient(requireActivity(), gso)

        binding.inAuth.btnGoogle.setOnClickListener {
            val signInIntent = gsc.signInIntent
            startActivityForResult(signInIntent, Constants.GOOGLE_AUTH_OK)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.GOOGLE_AUTH_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                // check auth
                auth.signInWithCredential(credential)
                    .addOnCompleteListener(requireActivity()) { result ->
                        if (result.isSuccessful) {
                            findNavController().navigate(R.id.homeFragment)
                        } else {
                            Toast.makeText(requireActivity(), "Login Failed: ", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            } catch (e: ApiException) {
                Toast.makeText(requireActivity(), "Login Failed", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    // check if email and password is correct, when user is logging in or signing up
    private fun ifEmpty(email: EditText, password: EditText) {
        if (email.text.toString().isEmpty()) {
            email.error = "Mail is empty"
        } else if (password.text.toString().isEmpty()) {
            password.error = "Password is empty"
        }
    }

    private fun ifException(exceptionMessage: String, email: EditText, password: EditText) {
        Log.e("aga", "ifException: "+ emailExceptionDict.containsKey(exceptionMessage) )
        if (emailExceptionDict.containsKey(exceptionMessage)) {
            email.error = emailExceptionDict[exceptionMessage]
        } else if (passwordExceptionDict.containsKey(exceptionMessage)) {
            password.error = passwordExceptionDict[exceptionMessage]
        }
    }
}