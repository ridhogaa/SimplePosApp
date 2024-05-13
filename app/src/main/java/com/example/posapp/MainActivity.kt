package com.example.posapp

import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.posapp.adapter.ItemAdapter
import com.example.posapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val builder = StringBuilder()

    private val itemAdapter: ItemAdapter by lazy {
        ItemAdapter {
            if (it.contains("<-")) {
                if (builder.isNotEmpty()) {
                    builder.deleteCharAt(builder.length - 1)
                } else {
                    Toast.makeText(this, "Input must not empty", Toast.LENGTH_SHORT).show()
                }
            } else {
                builder.append(it)
            }
            binding.tvNumber.text = if (builder.isNotEmpty()) "$$builder" else "$0.00"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.rvItem.apply {
            layoutManager =
                GridLayoutManager(this@MainActivity, 3)
            adapter = this@MainActivity.itemAdapter
            isNestedScrollingEnabled = false
        }
        listData()
    }

    private fun listData() {
        itemAdapter.setItems(
            mutableListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "00", "0", "<-")
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}