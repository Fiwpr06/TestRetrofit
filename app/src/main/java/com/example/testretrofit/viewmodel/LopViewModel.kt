package com.example.testretrofit.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testretrofit.model.Lop
import com.example.testretrofit.repository.LopRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException

class LopViewModel(private val repository: LopRepository) : ViewModel() {
    private val _lops = MutableLiveData<List<Lop>>(emptyList())
    val lops: LiveData<List<Lop>> get() = _lops
    
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error
    
    init {
        fetchLops()
    }
    
    fun fetchLops() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val lstLop = repository.fetchLops()
                withContext(Dispatchers.Main) {
                    _lops.value = lstLop
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _error.value = "Error fetching lops: ${e.message}"
                }
            }
        }
    }
    
    fun insertLop(context: Context, tenlop: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val thongBao = repository.insertLop(tenlop)
                withContext(Dispatchers.Main) {
                    try {
                        val status = thongBao?.success
                        if (status == 1) {
                            fetchLops()
                            Toast.makeText(context, thongBao.message, Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, thongBao?.message ?: "Unknown error", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        _error.value = "Error: ${e.message}"
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _error.value = "Error inserting lops: ${e.message}"
                }
            }
        }
    }
    
    fun updateLop(context: Context, malop: Int, tenlop: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val thongBao = repository.updateLop(malop, tenlop)
                withContext(Dispatchers.Main) {
                    try {
                        val status = thongBao?.success
                        if (status == 1) {
                            fetchLops()
                            Toast.makeText(context, thongBao.message, Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, thongBao?.message ?: "Unknown error", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        _error.value = "Error: ${e.message}"
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _error.value = "Error updating lops: ${e.message}"
                }
            }
        }
    }
    
    fun deleteLop(context: Context, malop: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val thongBao = repository.deleteLop(malop)
                withContext(Dispatchers.Main) {
                    try {
                        val status = thongBao?.success
                        if (status == 1) {
                            fetchLops()
                            Toast.makeText(context, thongBao.message, Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, thongBao?.message ?: "Unknown error", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        _error.value = "Error: ${e.message}"
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _error.value = "Error deleting lops: ${e.message}"
                }
            }
        }
    }
}
