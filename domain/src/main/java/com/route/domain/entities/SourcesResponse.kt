package com.route.domain.entities

// Data Transfer Object
data class SourceResponseDTO(

    val sources: List<SourceItemDTO>? = null,

    val status: String? = null
)


data class SourceItemDTO(

    var country: String? = null,

    var name: String? = null,


    var description: String? = null,


    var language: String? = null,


    var id: String,

    var category: String? = null,

    var url: String? = null
)
