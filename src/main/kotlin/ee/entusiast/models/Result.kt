package ee.entusiast.models

import kotlinx.serialization.Serializable

@Serializable
data class Result(val id: Int, val result: String)


val resultStorage = mutableListOf<Result>()

fun populate() {
    resultStorage.add(Result(12, "hi"))
}
