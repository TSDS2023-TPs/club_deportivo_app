package com.example.appclubdeportivo.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.appclubdeportivo.data.AppDatabase
import com.example.appclubdeportivo.db_entities.DocumentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class PersonViewModel(private val appDatabase: AppDatabase) : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _id = MutableStateFlow("")
    val id: StateFlow<String> = _id.asStateFlow()

    private val _selectedDocumentType = MutableStateFlow<DocumentType?>(null)
    val selectedDocumentType: StateFlow<DocumentType?> = _selectedDocumentType.asStateFlow()

    private val _bornDate = MutableStateFlow("")
    val bornDate: StateFlow<String> = _bornDate.asStateFlow()

    private val _telephone = MutableStateFlow("")
    val telephone: StateFlow<String> = _telephone.asStateFlow()

    private val _gender = MutableStateFlow("")
    val gender: StateFlow<String> = _gender.asStateFlow()

    private val _weight = MutableStateFlow("")
    val weight: StateFlow<String> = _weight.asStateFlow()

    private val _height = MutableStateFlow("")
    val height: StateFlow<String> = _height.asStateFlow()

    fun updateName(value: String) {
        _name.value = value
    }

    fun updateId(value: String) {
        _id.value = value
    }

    fun updateSelectedDocumentType(value: DocumentType?) {
        _selectedDocumentType.value = value
    }

    fun updateBornDate(value: String) {
        _bornDate.value = value
    }

    fun updateTelephone(value: String) {
        _telephone.value = value
    }

    fun updateGender(value: String) {
        _gender.value = value
    }

    fun updateWeight(value: String) {
        _weight.value = value
    }

    fun updateHeight(value: String) {
        _height.value = value
    }

    val documentTypes: LiveData<List<DocumentType>> = liveData {
        emit(getDocumentTypesFromDB())
    }

    private suspend fun getDocumentTypesFromDB(): List<DocumentType> {
        return withContext(Dispatchers.IO) {
            appDatabase.documentTypeDao().getAllDocumentTypes()
        }
    }

    fun clearFields() {
        updateName("")
        updateId("")
        updateSelectedDocumentType(null)
        updateBornDate("")
        updateTelephone("")
        updateGender("")
        updateWeight("")
        updateHeight("")
    }
}