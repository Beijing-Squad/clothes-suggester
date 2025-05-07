package domain.usecase

import io.mockk.mockk
import org.beijingteam.domain.repository.WeatherRepository
import domain.useCase.GetWeatherByLongitudeAndLatitudeUseCase
import kotlin.test.BeforeTest

class GetWeatherByLongitudeAndLatitudeUseCaseTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var getWeatherByLongitudeAndLatitude: GetWeatherByLongitudeAndLatitudeUseCase

    @BeforeTest
    fun setup() {
        weatherRepository = mockk(relaxed = true)
        getWeatherByLongitudeAndLatitude = GetWeatherByLongitudeAndLatitudeUseCase(weatherRepository)
    }

}