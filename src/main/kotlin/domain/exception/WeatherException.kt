package domain.exception

open class WeatherException(message: String) : Exception(message)
class MissingTemperatureException: WeatherException("Temperature is missing")
class MissingWeatherCodeException: WeatherException("Weather code is missing")

open class LocationException(message: String) : Exception(message)
class MissingLocationException: LocationException("Location is missing")