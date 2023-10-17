package me.elrevin.data

fun searchLocationNotEmptyData() = """
    [
        {
            "id": 568120,
            "name": "Berlin",
            "region": "Berlin",
            "country": "Germany",
            "lat": 52.52,
            "lon": 13.4,
            "url": "berlin-berlin-germany"
        },
        {
            "id": 2322383,
            "name": "Berlin",
            "region": "Usulutan",
            "country": "El Salvador",
            "lat": 13.5,
            "lon": -88.53,
            "url": "berlin-usulutan-el-salvador"
        },
        {
            "id": 2610505,
            "name": "Berlin",
            "region": "New Hampshire",
            "country": "United States of America",
            "lat": 44.47,
            "lon": -71.19,
            "url": "berlin-new-hampshire-united-states-of-america"
        },
        {
            "id": 2017704,
            "name": "Berliste",
            "region": "Caras-Severin",
            "country": "Romania",
            "lat": 44.99,
            "lon": 21.46,
            "url": "berliste-caras-severin-romania"
        },
        {
            "id": 920098,
            "name": "Berlin",
            "region": "Santa Barbara",
            "country": "Honduras",
            "lat": 14.83,
            "lon": -88.5,
            "url": "berlin-santa-barbara-honduras"
        }
    ]
""".trimIndent()

fun searchLocationEmptyData() = """
    []
""".trimIndent()


fun getLocationNotFoundErrorData() = """
    {
    "error": {
        "code": 1006,
        "message": "No matching location found."
    }
}
""".trimIndent()

fun getApiKeyErrorData() = """
{
    "error": {
        "code": 2008,
        "message": "API key has been disabled."
    }
}
""".trimIndent()
