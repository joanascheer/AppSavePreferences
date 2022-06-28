package br.com.zup.appsavepreferences.main.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.appsavepreferences.AGE
import br.com.zup.appsavepreferences.FLAG_KEY
import br.com.zup.appsavepreferences.NAME
import br.com.zup.appsavepreferences.PREF_KEY
import br.com.zup.appsavepreferences.main.model.UserModel
import br.com.zup.appsavepreferences.main.repository.MainRepository

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository = MainRepository()
    private val _savedData: MutableLiveData<UserModel> = MutableLiveData()
    val savedData: LiveData<UserModel> = _savedData

    private val _savedDataFlag: MutableLiveData<Boolean> = MutableLiveData()
    val savedDataFlag: LiveData<Boolean> = _savedDataFlag

    val pref = application.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
    val prefEditor = pref.edit()

    fun getUserData(user: UserModel, flagswitch: Boolean) {
        try {
            prefEditor.putBoolean(FLAG_KEY,flagswitch)
            prefEditor.apply()

            if (flagswitch) {
                prefEditor.putString(NAME,user.name)
                prefEditor.putInt(AGE,user.age)
                prefEditor.apply()
            } else {
                prefEditor.remove(NAME)
                prefEditor.remove(AGE)
                prefEditor.apply()
            }
            _savedData.value = repository.getUserData(user)
        } catch (e: Exception) {
            Log.i("Error", "------> ${e.message}")
        }
    }

    fun getSavedUserData() {
        try {
            val name = pref.getString(NAME, "Joana").toString()
            val age = pref.getInt(AGE, 35)
            val user = UserModel(name, age)
            _savedData.value = user
            _savedDataFlag.value = pref.getBoolean(FLAG_KEY, false)
        } catch (e: Exception) {
            Log.i("Error", "------> ${e.message}")
        }

    }
}