using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.WebUtilities;
using ReactWeatherApi.Models;
using System.Net;
using System.Runtime.Serialization.Json;

namespace ReactWeatherApi.Controllers
{

    [Route("api/[controller]")]
    [ApiController]
    public class WeatherController : ControllerBase
    {
        private readonly IHttpClientFactory _httpClient;
        private readonly IConfiguration _config;

        public WeatherController(IHttpClientFactory httpClient,
            IConfiguration config)
        {
            _httpClient = httpClient;
            _config = config;
        }

        [HttpGet("forecast")]
        public async Task<IActionResult> Forecast(string location, int days = 1, bool aqi = false, bool alerts = false)
        {
            var weatherForecast = new WeatherForecast();

            var queryParams = new Dictionary<string, string>()
            {
                { "key", _config["ApiKey"] },
                { "q", location },
                { "aqi", aqi ? "yes" : "no" },
                { "alerts", alerts ? "yes" : "no" }
            };

            var response = await CallApi(queryParams, "forecast.json");
            if(response.IsSuccessStatusCode)
            {
                var dcjs = new DataContractJsonSerializer(typeof(WeatherForecast));
                using var responseStream = await response.Content.ReadAsStreamAsync();
                weatherForecast = (WeatherForecast)dcjs.ReadObject(responseStream);

                return Ok(weatherForecast);
            }
            else if (response.StatusCode == HttpStatusCode.BadRequest)
            {
                return BadRequest(await SendError(response));
            }
            else
            {
                return StatusCode((int) response.StatusCode);
            }
        }

        [HttpGet("current")]
        public async Task<IActionResult> Current(string location, bool aqi = false)
        {
            var currentWeather = new CurrentWeather();

            var queryParams = new Dictionary<string, string>()
            {
                { "key", _config["ApiKey"] },
                { "q", location },
                { "aqi", aqi ? "yes" : "no" }
            };

            var response = await CallApi(queryParams, "current.json");
            if(response.IsSuccessStatusCode)
            {
                var dcjs = new DataContractJsonSerializer(typeof(CurrentWeather));
                using var responseStream = await response.Content.ReadAsStreamAsync();
                currentWeather = (CurrentWeather)dcjs.ReadObject(responseStream);

                return Ok(currentWeather);
            }
            else if(response.StatusCode == HttpStatusCode.BadRequest)
            {
                return BadRequest(await SendError(response));
            }
            else
            {
                return StatusCode((int) response.StatusCode);
            }

        }

        private async Task<HttpResponseMessage> CallApi(Dictionary<string, string> qParams, string path)
        {
            var requestUri = QueryHelpers.AddQueryString(_config["BaseUrl"] + path, qParams);

            var client = _httpClient.CreateClient();
            var request = new HttpRequestMessage(HttpMethod.Get, requestUri);
            var response = await client.SendAsync(request);

            return response;
        }

        private async Task<WeatherError> SendError(HttpResponseMessage response)
        {
            var error = new WeatherError();
            var dcjs = new DataContractJsonSerializer(typeof(WeatherError));
            using var responseStream = await response.Content.ReadAsStreamAsync();
            error = (WeatherError)dcjs.ReadObject(responseStream);

            return error;
        }
    }
}
