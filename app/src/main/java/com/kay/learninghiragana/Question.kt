package com.kay.learninghiragana

// TODO (Create a Question Data Model Class.)
// We define here how the Question is gonna looks like.
data class Question (
    val id: Int,
    val question: String,
    val image: Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Int
)