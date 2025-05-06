package hamrogadi12345.controller.Dao;
import java.sql.Statement;

import hamrogadi12345.controller.models.CarModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class CarDao {

    public List<CarModel> getAllCars() {
        List<CarModel> cars = new ArrayList<>();
        String sql = "SELECT * FROM cardetails";

        try (Connection conn = DBConnections.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CarModel car = new CarModel(
                    rs.getInt("CarID"),
                    rs.getString("Brand"),
                    rs.getString("Model"),
                    rs.getDouble("Price"),
                    rs.getString("Description"),
                    rs.getString("Carcondition"),
                    rs.getString("URL"),
                    rs.getInt("CategoryID")
                );
                cars.add(car);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return cars;
    }

    
 // Inside CarDao.java
    public CarModel getCarById(int carId) {
        String sql = "SELECT * FROM cardetails WHERE CarID = ?";
        try (Connection conn = DBConnections.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, carId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new CarModel(
                        rs.getInt("CarID"),
                        rs.getString("Brand"),
                        rs.getString("Model"),
                        rs.getDouble("Price"),
                        rs.getString("Description"),
                        rs.getString("Carcondition"),
                        rs.getString("URL"),
                        rs.getInt("CategoryID")
                    );
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    
    
    // Get the next available car ID
    public int getNextCarId() {
        String sql = "SELECT MAX(CarID) FROM cardetails";
        try (Connection conn = DBConnections.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
            return 1; // If no cars exist yet
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Modified addCar method to auto-generate ID
    public int addCar(CarModel carModel) {
        String sql = "INSERT INTO cardetails (Brand, Model, Price, Description, Carcondition, URL, CategoryID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = DBConnections.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, carModel.getBrand());
            stmt.setString(2, carModel.getModel());
            stmt.setDouble(3, carModel.getPrice());
            stmt.setString(4, carModel.getDescription());
            stmt.setString(5, carModel.getCarCondition());
            stmt.setString(6, carModel.getUrl());
            stmt.setInt(7, carModel.getCategoryID());

            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Return the generated ID
                    }
                }
            }
            return 0; // Failure
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }
    // Update an existing car
    public int updateCar(CarModel carModel) {
        String sql = "UPDATE cardetails SET Brand = ?, Model = ?, Price = ?, Description = ?, Carcondition = ?, URL = ?, CategoryID = ? WHERE CarID = ?";

        try (Connection con = DBConnections.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, carModel.getBrand());
            stmt.setString(2, carModel.getModel());
            stmt.setDouble(3, carModel.getPrice());
            stmt.setString(4, carModel.getDescription());
            stmt.setString(5, carModel.getCarCondition());
            stmt.setString(6, carModel.getUrl());
            stmt.setInt(7, carModel.getCategoryID());
            stmt.setInt(8, carModel.getCarID());

            return stmt.executeUpdate() > 0 ? 1 : 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Delete a car by CarID
    public int deleteCar(int carID) {
        String sql = "DELETE FROM cardetails WHERE CarID = ?";

        try (Connection con = DBConnections.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, carID);
            return stmt.executeUpdate() > 0 ? 1 : 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }
}