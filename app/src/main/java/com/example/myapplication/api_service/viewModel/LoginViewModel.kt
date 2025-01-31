package com.example.myapplication.api_service.viewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api_service.api_interface.RetrofitInstance
import com.example.myapplication.api_service.data.LoginRequest
import com.example.myapplication.api_service.data.LoginResponse
import com.example.myapplication.api_service.storeData.Session
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val session: Session
) : ViewModel() {

    var loginResponse = mutableStateOf<LoginResponse?>(null)
    var errorMessage = mutableStateOf<String?>(null)
    var isLoading = mutableStateOf(false)
    var userName = mutableStateOf("")
    var accessToken = mutableStateOf("")

    fun gettingUserName() : Boolean {
        // Collect username when ViewModel is initialized
        viewModelScope.launch {
            session.getUserName().collect {
                userName.value = it
                println("userName value getting like this ${userName.value} ${userName.value != ""}")
            }
        }
        return userName.value != ""

    }

    fun gettingAccessToken(): Boolean {
        // Collect username when ViewModel is initialized
        viewModelScope.launch {
            session.getAccessToken().collect {
                accessToken.value = it
                println("Access token is :  ${accessToken.value} ")
            }
        }
        return accessToken.value != ""
    }

    fun login(username: String, password: String) {
        val loginRequest = LoginRequest(username, password)

        isLoading.value = true
        errorMessage.value = null

        viewModelScope.launch {
            try {
                val response = RetrofitInstance.apiService.login(loginRequest)
                isLoading.value = false
                if (response.isSuccessful) {
                    loginResponse.value = response.body()
                    session.setUserName(loginResponse.value?.accessToken ?: "")
                    session.getUserName().collect {
                        userName.value = it
                    }
                    session.setAccessToken(loginResponse.value?.accessToken ?: "")
                    session.getAccessToken().collect {
                        accessToken.value = it
                        println("access token getting and printing ${accessToken.value} : $it")
                    }

                } else {

                    errorMessage.value = "Login Failed: ${response.message()}"
                }
            } catch (e: Exception) {
                isLoading.value = false
                errorMessage.value = "An error occurred: ${e.message}"
            }
        }

    }


}
