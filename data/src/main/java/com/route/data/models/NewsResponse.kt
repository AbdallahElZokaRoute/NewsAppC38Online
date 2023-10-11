package com.route.data.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

fun <T> Any.convertTo(clazz: Class<T>): T {
    val json = Gson().toJson(this)
    return Gson().fromJson(json, clazz)
}

data class NewsResponse(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<ArticlesItem>? = null,

    @field:SerializedName("status")
    val status: String? = null
) {

}

@Entity
data class ArticlesItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @field:SerializedName("publishedAt")
    var publishedAt: String? = null,

    @field:SerializedName("author")
    var author: String? = null,

    @field:SerializedName("urlToImage")
    var urlToImage: String? = null,

    @field:SerializedName("description")
    var description: String? = null,
    @Ignore
    @field:SerializedName("source")
    var source: Source? = null,

    @field:SerializedName("title")
    var title: String? = null,

    @field:SerializedName("url")
    var url: String? = null,

    @field:SerializedName("content")
    var content: String? = null
)

data class Source(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null
)
