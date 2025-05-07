package domain.usecase

import domain.repository.LocationRepository
import domain.useCase.GetWeatherByCityNameUseCase
import io.mockk.mockk
import kotlin.test.BeforeTest

class GetWeatherByCityNameUseCaseTest {

    private lateinit var locationRepository: LocationRepository
    private lateinit var getWeatherByCityName: GetWeatherByCityNameUseCase

    @BeforeTest
    fun setup() {
        locationRepository = mockk(relaxed = true)
        getWeatherByCityName = GetWeatherByCityNameUseCase(locationRepository)
    }

}