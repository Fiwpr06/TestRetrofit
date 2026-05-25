package com.example.testretrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testretrofit.databinding.ActivityMainBinding
import com.example.testretrofit.model.Lop
import com.example.testretrofit.repository.LopRepository
import com.example.testretrofit.ui.adapter.LopAdapter
import com.example.testretrofit.viewmodel.LopViewModel
import com.example.testretrofit.viewmodel.LopViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: LopAdapter
    
    private val viewModel: LopViewModel by viewModels {
        LopViewModelFactory(LopRepository())
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupRecyclerView()
        observeViewModel()
        
        binding.fabAdd.setOnClickListener {
            showAddDialog()
        }
    }
    
    private fun setupRecyclerView() {
        adapter = LopAdapter(
            emptyList(),
            editOnClick = { lop, _ ->
                showUpdateDialog(lop)
            },
            deleteOnClick = { lop, _ ->
                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.confirm_delete_title))
                    .setMessage(getString(R.string.confirm_delete_msg, lop.tenlop))
                    .setPositiveButton(getString(R.string.btn_delete)) { _, _ ->
                        viewModel.deleteLop(this, lop.malop)
                    }
                    .setNegativeButton(getString(R.string.btn_cancel), null)
                    .show()
            }
        )
        binding.rvLops.layoutManager = LinearLayoutManager(this)
        binding.rvLops.adapter = adapter
    }
    
    private fun observeViewModel() {
        viewModel.lops.observe(this) { lops ->
            adapter.updateLops(lops)
        }
        
        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }
    }
    
    private fun showAddDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_lop, null)
        val etTenLop = dialogView.findViewById<EditText>(R.id.etTenLop)
        
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_add_title))
            .setView(dialogView)
            .setPositiveButton(getString(R.string.btn_save)) { _, _ ->
                val tenLop = etTenLop.text.toString()
                if (tenLop.isNotBlank()) {
                    viewModel.insertLop(this, tenLop)
                }
            }
            .setNegativeButton(getString(R.string.btn_cancel), null)
            .show()
    }
    
    private fun showUpdateDialog(lop: Lop) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_lop, null)
        val etTenLop = dialogView.findViewById<EditText>(R.id.etTenLop)
        etTenLop.setText(lop.tenlop)
        
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_update_title))
            .setView(dialogView)
            .setPositiveButton(getString(R.string.btn_save)) { _, _ ->
                val tenLop = etTenLop.text.toString()
                if (tenLop.isNotBlank()) {
                    viewModel.updateLop(this, lop.malop, tenLop)
                }
            }
            .setNegativeButton(getString(R.string.btn_cancel), null)
            .show()
    }
}
