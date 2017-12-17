package pl.majorczyk.weatherapp.parser;

public interface DataParser<T> {
    T parse(String response);
}
