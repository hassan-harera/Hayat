package com.harera.dwaa.network.repository

import android.graphics.Bitmap
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.UploadTask
import com.harera.dwaa.model.MedicineData

interface MedicineRepository {

    fun addMedicine(medicineData: MedicineData): Task<Void>
    fun getNewMedicineId(medicineCategory: String): String
    fun uploadMedicineImage(image: Bitmap, medicineCategory: String, medicineId: String): UploadTask
    fun getMedicines(limit: Int, underPrice: Float): Task<QuerySnapshot>
    fun searchMedicines(
        searchWord: String,
        limit: Long,
        orderBy: String?,
        direction: Query.Direction?
    ): Task<QuerySnapshot>
}