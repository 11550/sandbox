package repository.impl;

import model.Location;
import repository.LocationRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationRepositoryImpl implements LocationRepository {
    @Override
    public List<Location> findAll() {
        List<Location> result = new ArrayList<>();

        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM location";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result.add(new Location(rs.getInt("id"),
                        rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Location findById(int id) {
        Location location = null;

        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM location WHERE id = (?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, String.valueOf(id));

            ResultSet rs = statement.executeQuery();
            location = new Location(rs.getInt("id"),
                    rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return location;
    }

    @Override
    public Location findByName(String name) {
        Location location = null;

        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM location WHERE name = (?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();
            location = new Location(rs.getInt("id"),
                    rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return location;
    }

    @Override
    public int insert(Location entity) {
        int result = 0;

        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO location (name) VALUES ((?))";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, entity.getName());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int update(Location entity) {
        int result = 0;

        try (Connection conn = getConnection()) {
            String sql = "UPDATE location SET name = (?) WHERE id = (?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getId());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int delete(int id) {
        int result = 0;
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM location WHERE id = (?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, String.valueOf(id));

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:src/main/resources/test.db";
        return DriverManager.getConnection(url);
    }
}
