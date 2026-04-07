package com.kholiq.tugas3.model

import android.os.Parcel
import android.os.Parcelable

data class UserData(
    val nama: String,
    val nim: String,
    val programStudi: String,
    val jenisKelamin: String,
    val hobi: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nama)
        parcel.writeString(nim)
        parcel.writeString(programStudi)
        parcel.writeString(jenisKelamin)
        parcel.writeString(hobi)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<UserData> {
        override fun createFromParcel(parcel: Parcel): UserData {
            return UserData(parcel)
        }

        override fun newArray(size: Int): Array<UserData?> {
            return arrayOfNulls(size)
        }
    }
}
