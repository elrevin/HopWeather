package me.elrevin.data

fun getSuccessfulCurrentWeatherData() = """
    {
        "location": {
            "name": "Berlin",
            "region": "Berlin",
            "country": "Germany",
            "lat": 52.52,
            "lon": 13.4,
            "tz_id": "Europe/Berlin",
            "localtime_epoch": 1696779547,
            "localtime": "2023-10-08 17:39"
        },
        "current": {
            "last_updated_epoch": 1696779000,
            "last_updated": "2023-10-08 17:30",
            "temp_c": 15.0,
            "temp_f": 59.0,
            "is_day": 1,
            "condition": {
                "text": "Sunny",
                "icon": "//cdn.weatherapi.com/weather/64x64/day/113.png",
                "code": 1000
            },
            "wind_mph": 5.6,
            "wind_kph": 9.0,
            "wind_degree": 340,
            "wind_dir": "NNW",
            "pressure_mb": 1026.0,
            "pressure_in": 30.3,
            "precip_mm": 0.0,
            "precip_in": 0.0,
            "humidity": 41,
            "cloud": 0,
            "feelslike_c": 14.6,
            "feelslike_f": 58.3,
            "vis_km": 10.0,
            "vis_miles": 6.0,
            "uv": 4.0,
            "gust_mph": 9.5,
            "gust_kph": 15.3,
            "air_quality": {
                "co": 293.7,
                "no2": 16.5,
                "o3": 59.4,
                "so2": 4.9,
                "pm2_5": 1.9,
                "pm10": 2.4,
                "us-epa-index": 1,
                "gb-defra-index": 1
            }
        }
    }
""".trimIndent()

fun getLocationNotFoundErrorData() = """
    {
    "error": {
        "code": 1006,
        "message": "No matching location found."
    }
}
""".trimIndent()
