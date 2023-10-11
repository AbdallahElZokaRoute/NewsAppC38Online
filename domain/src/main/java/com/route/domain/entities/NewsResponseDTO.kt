package com.route.domain.entities



data class NewsResponseDTO(

    val totalResults: Int? = null,

    val articles: List<ArticlesItemDTO>? = null,

    val status: String? = null
)

data class ArticlesItemDTO(
    var id: Int? = null,

    var publishedAt: String? = null,


    var author: String? = null,


    var urlToImage: String? = null,


    var description: String? = null,

    var source: SourceDTO? = null,


    var title: String? = null,


    var url: String? = null,


    var content: String? = null
)

data class SourceDTO(


    val name: String? = null,


    val id: String? = null
)
