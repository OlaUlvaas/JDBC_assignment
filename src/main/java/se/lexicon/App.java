package se.lexicon;

import se.lexicon.dao.CityDao;
import se.lexicon.dao.CityDaoJDBC;
import se.lexicon.model.City;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        // findById()
        System.out.println("------------------------------");
        System.out.println("findById(): ");
        CityDao dao = new CityDaoJDBC();
        City city = dao.findById(5);
        System.out.println("city = " + city);

        // findByCode()
        System.out.println("------------------------------");
        System.out.println("findByCode(): ");
        List<City> cities1 = dao.findByCode("NLD");
        cities1.forEach(System.out::println);

        // findByName()
        System.out.println("------------------------------");
        System.out.println("findByName(): ");
        List<City> cities2 = dao.findByName("Staun");
        cities2.forEach(System.out::println);

        // findAll()
        System.out.println("------------------------------");
        System.out.println("findAll(): ");
        List<City> cities3 = dao.findAll();
        cities3.forEach(System.out::println);

        // add()
        System.out.println("------------------------------");
        System.out.println("add(): ");
        City addCity = new City("Staun", "ARG", "HUH",8800);
        City addedCity = dao.add(addCity);


        // update()
        System.out.println("------------------------------");
        System.out.println("update(): ");
        City update = new City(4085,"Staun", "NOR", "YYY",777);
        City updated = dao.update(update);


        // delete()
        System.out.println("------------------------------");
        System.out.println("delete(): ");
        City delete = new City(4076, "Hebron", "PSE", "Hebron", 119401);
        int deleted = dao.delete(delete);
    }
}
