package domain.exception

open class ClothesSuggesterAppException(message: String) : Exception(message)

open class WeatherException(message: String) : ClothesSuggesterAppException(message)
class MissingTemperatureException: WeatherException("Temperature is missing")
class MissingWeatherConditionException: WeatherException("Weather condition is missing")

open class LocationException(message: String) : ClothesSuggesterAppException(message)
class MissingLocationException: LocationException("Location is missing")