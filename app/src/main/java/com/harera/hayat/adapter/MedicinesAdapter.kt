package com.harera.hayat.adapter

import android.location.Geocoder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harera.hayat.adapter.MedicinesAdapter.ViewHolder
import com.harera.hayat.databinding.CardViewMedicineProductBinding
import com.harera.hayat.ui.feed.FeedMedicine
import com.harera.hayat.ui.feed.MedicineViewModel
import com.harera.hayat.utils.location.LocationUtils
import com.harera.hayat.utils.time.Time
import java.util.*


class MedicinesAdapter(private var list: List<FeedMedicine>) : RecyclerView.Adapter<ViewHolder>() {

    class ViewHolder(val bind: CardViewMedicineProductBinding) :
        RecyclerView.ViewHolder(bind.root) {

        var medicineViewModel = MedicineViewModel()

        fun updateUI(feedMedicine: FeedMedicine) {
            bind.addTime.text = Time.timeFromNowInString(feedMedicine.addingTime)
            bind.expireDate.text = Time.convertTimestampToString(feedMedicine.expireDate)
            bind.price.text = feedMedicine.price.toString().plus(" جنيه")
            bind.medicineName.text = feedMedicine.name
            updateLocation(feedMedicine.location)
            updateImage(feedMedicine.imageUrl)
        }

        private fun updateImage(imageUrl: String) {
            medicineViewModel.image.observeForever {
                bind.image.setImageBitmap(it)
            }
            medicineViewModel.loadImage(imageUrl)
        }

        private fun updateLocation(location: Map<String, Double>) {
            val geocoder = Geocoder(bind.root.context, Locale.getDefault())
            bind.location.text =
                LocationUtils.getLocationAddressName(location, geocoder)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        CardViewMedicineProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .let {
                return ViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateUI(list[position])
    }

    override fun getItemCount(): Int =
        list.size

    fun setList(medicines: List<FeedMedicine>) {
        list = medicines
    }
}