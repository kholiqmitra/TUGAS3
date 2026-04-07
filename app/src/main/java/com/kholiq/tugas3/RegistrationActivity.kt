package com.kholiq.tugas3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kholiq.tugas3.databinding.ActivityRegistrationBinding
import com.kholiq.tugas3.model.UserData

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private var selectedProdi: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()
        setupClickListener()
    }

    private fun setupSpinner() {
        val prodiList = arrayOf(
            getString(R.string.prodi_informatika),
            getString(R.string.prodi_sistem_informasi),
            getString(R.string.prodi_teknik_komputer),
            getString(R.string.prodi_desain_komunikasi_visual)
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            prodiList
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerProdi.adapter = adapter

        binding.spinnerProdi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedProdi = prodiList[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedProdi = ""
            }
        }
    }

    private fun setupClickListener() {
        binding.btnTampilkan.setOnClickListener {
            if (validateInput()) {
                val userData = collectUserData()
                navigateToProfile(userData)
            }
        }
    }

    private fun validateInput(): Boolean {
        val nama = binding.etNama.text.toString().trim()
        val nim = binding.etNim.text.toString().trim()

        if (nama.isEmpty()) {
            binding.etNama.error = getString(R.string.error_nama_empty)
            binding.etNama.requestFocus()
            return false
        }

        if (nim.isEmpty()) {
            binding.etNim.error = getString(R.string.error_nim_empty)
            binding.etNim.requestFocus()
            return false
        }

        if (selectedProdi.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_prodi_empty), Toast.LENGTH_SHORT).show()
            return false
        }

        if (!binding.rbLaki.isChecked && !binding.rbPerempuan.isChecked) {
            Toast.makeText(this, getString(R.string.error_jk_empty), Toast.LENGTH_SHORT).show()
            return false
        }

        if (!binding.cbOlahraga.isChecked && !binding.cbMusik.isChecked &&
            !binding.cbBaca.isChecked) {
            Toast.makeText(this, getString(R.string.error_hobi_empty), Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun collectUserData(): UserData {
        val nama = binding.etNama.text.toString().trim()
        val nim = binding.etNim.text.toString().trim()

        val jenisKelamin = if (binding.rbLaki.isChecked) {
            getString(R.string.gender_laki)
        } else {
            getString(R.string.gender_perempuan)
        }

        val hobbies = mutableListOf<String>()
        if (binding.cbOlahraga.isChecked) hobbies.add(getString(R.string.hobi_olahraga))
        if (binding.cbMusik.isChecked) hobbies.add(getString(R.string.hobi_musik))
        if (binding.cbBaca.isChecked) hobbies.add(getString(R.string.hobi_baca))

        val hobiString = hobbies.joinToString(", ")

        return UserData(
            nama = nama,
            nim = nim,
            programStudi = selectedProdi,
            jenisKelamin = jenisKelamin,
            hobi = hobiString
        )
    }

    private fun navigateToProfile(userData: UserData) {
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra("USER_DATA", userData)
        startActivity(intent)
    }
}
