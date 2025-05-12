package org.beijingteam.data.remote.utils

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import org.koin.mp.KoinPlatform.getKoin

suspend inline fun <reified T> HttpClient.fetchWithQueryParams(
    baseUrl: String,
    queryParams: Map<String, String> = emptyMap(),

): T {
    val json: Json = getKoin().get()
    val response = get(baseUrl) {
        url {
            queryParams.forEach { (queryParameterName, queryParameterValue) ->
                parameters.append(queryParameterName, queryParameterValue)
            }
        }
    }
    return json.decodeFromString<T>(response.bodyAsText())
}