package com.project.coba.presentation.content

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.coba.core.ResultState
import com.project.coba.data.remote.model.content.ContentDataModel
import com.project.coba.databinding.ActivityContentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ContentActivity : AppCompatActivity() {

    private val viewModel: ContentViewModel by viewModel()
    private lateinit var binding: ActivityContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.getContentDetail.observe(this, { handleResponse(it) })
        viewModel.fetchContent()
    }

    private fun handleResponse(resultState: ResultState<*>) {
        if (resultState is ResultState.Success && resultState.data is ContentDataModel) {
            viewModel.save(resultState.data)
            val test = resultState.data.bpi.USD.description
            binding.tvContent.text = test
        }
    }

}