package com.example.miniwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.miniwiki.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener{
            val countryName = binding.countryEditNameText.text.toString()
            lifecycleScope.launch {
                try {
                    val countries = restCountriesService.getCountryByName(countryName)
                    val country: Country = countries[0]
                    binding.countryNameTextView.text = country.name.common
                    binding.capitalTextView.text = country.capital[0]
                    binding.populatioinTextView.text = formatNumber(country.population)
                    binding.areaTextView.text = country.area.toString()
                    binding.languagesTextView.text = mapToString(country.languages)

                    loadSvg(binding.imageView, country.flags)

                    binding.statusLayout.visibility = View.INVISIBLE
                    binding.resultLayout.visibility = View.VISIBLE
                } catch (e: Exception) {
                    binding.statusTextView.text = "Страна не найдено"
                    binding.statusImageView.setImageResource(R.drawable.baseline_error_outline_24)
                    binding.statusLayout.visibility = View.VISIBLE
                    binding.resultLayout.visibility = View.INVISIBLE
                }
            }
        }
    }
}

