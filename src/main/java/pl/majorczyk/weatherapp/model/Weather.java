package pl.majorczyk.weatherapp.model;

public class Weather {
    private String main;
    private String temp;
    private String pressure;
    private String visibility;
    private WeatherDetails details;

    public Weather() {
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

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public WeatherDetails getDetails() {
        return details;
    }

    public void setDetails(WeatherDetails details) {
        this.details = details;
    }
}
