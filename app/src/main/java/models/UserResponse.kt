package models

import com.google.gson.annotations.SerializedName
import java.util.*

data class User(val id: Int, val name: String, @SerializedName("birthdate") val birthDate: Date)
