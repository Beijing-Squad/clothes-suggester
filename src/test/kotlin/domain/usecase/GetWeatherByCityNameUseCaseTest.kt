package domain.usecase

import io.mockk.mockk
import org.beijingteam.domain.repository.WeatherRepository
import org.beijingteam.domain.usecase.GetWeatherByCityNameUseCase
import kotlin.test.BeforeTest

class GetWeatherByCityNameUseCaseTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var getWeatherByCityName: GetWeatherByCityNameUseCase

    @BeforeTest
    fun setup() {
        weatherRepository = mockk(relaxed = true)
        getWeatherByCityName = GetWeatherByCityNameUseCase(weatherRepository)
    }

}