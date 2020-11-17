package com.newspaperapp.enums

enum class ItemArticleTypeEnum(private val value: Int) {
    AUTHOR_NAME_TOP(1),
    AUTHOR_NAME_BOTTOM(2);

    fun getIntValue(): Int = value
}