package repository.impl;

import model.Loads;
import repository.LoadsRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoadsRepositoryImpl implements LoadsRepository {

    @Override
    public List<Loads> findAll() {
        List<Loads> result = new ArrayList<>();

        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM loads";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result.add(new Loads(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("Loc_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<String> getLocNames() {
        List<String> result = new ArrayList<>();

        try (Connection conn = getConnection()) {
            String sql = "SELECT loc.name FROM loads l left join location loc on loc.id = l.Loc_id";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Loads findById(int id) {
        Loads loads = null;

        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM loads WHERE id = (?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, String.valueOf(id));

            ResultSet rs = statement.executeQuery();
            loads = new Loads(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("Loc_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loads;
    }

    @Override
    public Loads findByName(String name) {
        Loads loads = null;

        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM loads WHERE name = (?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();
            loads = new Loads(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("Loc_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loads;
    }

    @Override
    public int insert(Loads entity) {
        int result = 0;

        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO loads (name, Loc_id) VALUES ((?), (?))";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getIdLocations());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int update(Loads entity) {
        int result = 0;

        try (Connection conn = getConnection()) {
            String sql = "UPDATE loads SET name = (?), Loc_id = (?) WHERE id = (?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getIdLocations());
            statement.setInt(3, entity.getId());

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
            String sql = "DELETE FROM loads WHERE id = (?)";
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

    public int getMaxId() {
        int result = 0;
        try (Connection conn = getConnection()) {
            String sql = "SELECT id FROM loads ORDER BY id DESC LIMIT 1";
            PreparedStatement statement = conn.prepareStatement(sql);

            result = statement.executeQuery().getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
