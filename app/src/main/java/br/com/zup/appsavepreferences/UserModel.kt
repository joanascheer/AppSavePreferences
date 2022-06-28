package br.com.zup.appsavepreferences

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class UserModel(
    var name: String = "",
    var age: String = ""
) : Parcelable