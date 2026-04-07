package com.kholiq.tugas3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kholiq.tugas3.databinding.ActivityProfileBinding
import com.kholiq.tugas3.model.UserData

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayUserData()
    }

    private fun displayUserData() {
        val userData = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("USER_DATA", UserData::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("USER_DATA")
        }

        userData?.let {
            binding.tvNama.text = it.nama
            binding.tvNim.text = it.nim
            binding.tvProdi.text = it.programStudi
            binding.tvJk.text = it.jenisKelamin
            binding.tvHobi.text = it.hobi
        }
    }
}
