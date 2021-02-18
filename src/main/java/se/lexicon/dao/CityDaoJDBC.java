package se.lexicon.dao;

import se.lexicon.dao.db.MySqlConnection;
import se.lexicon.model.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CityDaoJDBC implements CityDao{
    @Override
    public City findById(int id) {
        String query = "select * from city where id = ?";
        City city = new City();
        try (
                PreparedStatement preparedStatement = MySqlConnection.getConnection().prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                city.setId(resultSet.getInt(1));
                city.setName(resultSet.getString(2));
                city.setCountryCode(resultSet.getString(3));
                city.setDistrict(resultSet.getString(4));
                city.setPopulation(resultSet.getInt(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public List<City> findByCode(String Code) {
        String query = "select * from city where countrycode = ?";
        List<City> cities = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = MySqlConnection.getConnection().prepareStatement(query);
        ) {
            preparedStatement.setString(1, Code);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                cities.add(new City(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)

                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public List<City> findByName(String Name) {
        String query = "select * from city where name = ?";
        List<City> cities = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = MySqlConnection.getConnection().prepareStatement(query);
        ) {
            preparedStatement.setString(1, Name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                cities.add(new City(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)

                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public List<City> findAll() {
        String query = "select * from city";
        List<City> cities= new ArrayList<>();
        try {
            Statement statement = MySqlConnection.getConnection().createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            while (resultSet.next()){
                cities.add(new City(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }


    @Override
    public City add(City city) {
        String query = "insert into city (name, countrycode, district, population) values (?, ?, ?, ?) ";
        try (
                PreparedStatement preparedStatement = MySqlConnection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {

            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());


            int resultSet = preparedStatement.executeUpdate();
            System.out.println((resultSet == 1) ? "Added" : "Not added");
            ResultSet rs = preparedStatement.getGeneratedKeys();

            int idKey = 0;
            while(rs.next()){
                idKey = rs.getInt(1);
            }
            city.setId(idKey);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public City update(City city) {

        String query = "update city set name = ?, countrycode = ?, district = ?, population = ? where id = ? ";
        try (
                PreparedStatement preparedStatement = MySqlConnection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {

            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.setInt(5, city.getId());


            int resultSet = preparedStatement.executeUpdate();
            System.out.println((resultSet == 1) ? "Updated" : "Not updated");
            ResultSet rs = preparedStatement.getGeneratedKeys();

            int idKey = 0;
            while(rs.next()){
                idKey = rs.getInt(1);
            }
            city.setId(idKey);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public int delete(City city) {
        //String query = "delete from city where name = ?, countrycode = ?, district = ?, population = ? where id = ? ";

        String query = "delete from city where id= ?";
        try(
                PreparedStatement preparedStatement = MySqlConnection.getConnection().prepareStatement(query);
        ){

            preparedStatement.setInt(1, city.getId());
            int resultSet = preparedStatement.executeUpdate();
            System.out.println((resultSet == 1) ? "Deleted" : "Not deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }
}

