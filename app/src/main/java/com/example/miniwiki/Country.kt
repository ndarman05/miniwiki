package com.example.miniwiki

data class Country (
    val name: Name,
    val capital: List<String>,
    val population: Long,
    val area: Long,
    val languages: Map<String, String>,
    val flags: Flags
)

data class Name(
    val common: String,
    val official: String
)

data class Flags(
    val png: String,
    val svg: String,
    val alt: String
)