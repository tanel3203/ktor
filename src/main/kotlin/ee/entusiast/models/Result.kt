package ee.entusiast.models

import kotlinx.serialization.Serializable

@Serializable
data class Result(val id: String, val result: String)


val resultStorage = mutableListOf<Result>()

fun populate() {
    resultStorage.add(Result("12", "hi"))
    resultStorage.add(Result("13", "there"))
}
