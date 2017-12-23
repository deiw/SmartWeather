package pl.majorczyk.weatherapp.model.weather;

public class Weather {
    private String city;
    private String main;
    private String temp;
    private String pressure;
    private WeatherDetails details;

    public Weather() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public WeatherDetails getDetails() {
        return details;
    }

    public void setDetails(WeatherDetails details) {
        this.details = details;
    }
}
