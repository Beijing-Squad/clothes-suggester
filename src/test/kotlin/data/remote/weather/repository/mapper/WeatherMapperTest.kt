package data.remote.weather.repository.mapper

import com.google.common.truth.Truth.assertThat
import domain.exception.MissingTemperatureException
import domain.exception.MissingWeatherConditionException
import org.beijingteam.data.remote.weather.dto.CurrentWeather
import org.beijingteam.data.remote.weather.dto.WeatherDto
import org.beijingteam.data.remote.weather.repository.mapper.WeatherMapper
import org.beijingteam.domain.type.TemperatureCategory
import org.beijingteam.domain.entity.Weather
import org.beijingteam.domain.type.WeatherCondition
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class WeatherMapperTest {

    private lateinit var mapper: WeatherMapper

    @BeforeEach
    fun setup() {
        mapper = WeatherMapper()
    }

    @Test
    fun `mapToDomain should return Weather when data is valid`() {
        // Given
        val dto = baseDto(
            currentWeather = validCurrentWeather(
                temperature = 18.5,
                code = 2
            )
        )

        // When
        val result = mapper.mapToDomain(dto)

        // Then
        assertThat(result).isEqualTo(
            Weather(
                temperature = 18.5,
                weatherCondition = WeatherCondition.PARTLY_CLOUDY,
                temperatureCategory = TemperatureCategory.COOL
            )
        )
    }

    @Test
    fun `mapToDomain should throw MissingTemperatureException when temperature2m is null`() {
        // Given
        val dto = baseDto(
            currentWeather = validCurrentWeather(
                temperature = null,
                code = 2
            )
        )

        // When & Then
        assertThrows<MissingTemperatureException> {
            mapper.mapToDomain(dto)
        }
    }

    @Test
    fun `mapToDomain should throw MissingWeatherConditionException when weatherCode is null`() {
        // Given
        val dto = baseDto(
            currentWeather = validCurrentWeather(
                temperature = 15.0,
                code = null
            )
        )

        // When & Then
        assertThrows<MissingWeatherConditionException> {
            mapper.mapToDomain(dto)
        }
    }

    @Test
    fun `mapToDomain should throw MissingTemperatureException when currentWeather is null`() {
        // Given
        val dto = baseDto(currentWeather = null)

        // When & Then
        assertThrows<MissingTemperatureException> {
            mapper.mapToDomain(dto)
        }
    }

    companion object {
        private fun validCurrentWeather(
            temperature: Double?,
            code: Int?
        ) = CurrentWeather(
            time = "2025-05-08T12:00",
            interval = 1,
            temperature2m = temperature,
            weatherCode = code
        )

        private fun baseDto(currentWeather: CurrentWeather?): WeatherDto {
            return WeatherDto(
                latitude = 0.0,
                longitude = 0.0,
                generationTime = 1.0,
                utcOffsetSeconds = 0,
                timeZone = "UTC",
                timeZoneAbbreviation = "UTC",
                elevation = 0.0,
                currentWeatherUnits = null,
                currentWeather = currentWeather
            )
        }
    }
}