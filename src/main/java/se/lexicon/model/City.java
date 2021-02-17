package se.lexicon.model;

import java.util.Objects;

public class City {
    private int id;
    private String name;
    private int countryCode;
    private String district;
    private int population;

    public City() {
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public int getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id && code == city.code && Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code=" + code +
                '}';
    }
}
