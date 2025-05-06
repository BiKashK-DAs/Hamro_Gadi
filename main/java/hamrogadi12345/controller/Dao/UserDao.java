package hamrogadi12345.controller.Dao;

import hamrogadi12345.controller.models.UserModel;

import java.sql.Statement;

import hamrogadi12345.controller.models.CarModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDao {

	public int registerUser(UserModel userModel) {
	    try (Connection con = DBConnections.getConnection()) {
	        // Check if email already exists
	        if (checkEmailIfExists(userModel.getEmail())) {
	            return -2; // Email already exists
	        }

	        String role = userModel.getRole();
	        // If role is "admin", store as "pending"
	        if ("admin".equals(role)) {
	            role = "pending"; // Store as 'pending' for admin
	        }

	        PreparedStatement stmt = con.prepareStatement(
	            "INSERT INTO User (Name, Email, Password, PhoneNumber, Role, Address) VALUES (?, ?, ?, ?, ?, ?)"
	        );
	        stmt.setString(1, userModel.getName());
	        stmt.setString(2, userModel.getEmail());
	        stmt.setString(3, userModel.getPassword());
	        stmt.setString(4, userModel.getPhoneNumber());
	        stmt.setString(5, role);
	        stmt.setString(6, userModel.getAddress());

	        int result = stmt.executeUpdate();
	        return (result > 0) ? 1 : 0;
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        return -1; // Internal error
	    }
	}
    // Login a user
    public int loginUser(String email, String password) {
        try (Connection con = DBConnections.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT Password FROM User WHERE Email = ?"
            );
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                String passwordDb = result.getString("Password");
                return passwordDb.equals(password) ? 1 : 0;
            } else {
                return -1; // Email not found
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -2; // Internal error
        }
    }
    
    public static List<UserModel> getAllUsers() throws SQLException, ClassNotFoundException {
        List<UserModel> userList = new ArrayList<>();
        try (Connection con = DBConnections.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM User");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UserModel user = new UserModel();
                user.setName(rs.getString("Name"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setPhoneNumber(rs.getString("PhoneNumber"));
                user.setRole(rs.getString("Role"));
                user.setAddress(rs.getString("Address"));
                userList.add(user);
            }
        }
        return userList;
    }

    public static UserModel getUserByEmail(String email) throws SQLException, ClassNotFoundException {
        try (Connection con = DBConnections.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM User WHERE Email = ?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                UserModel user = new UserModel();
                user.setName(rs.getString("Name"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setPhoneNumber(rs.getString("PhoneNumber"));
                user.setRole(rs.getString("Role"));
                user.setAddress(rs.getString("Address"));
                return user;
            }
        }
        return null;
    }

    public static boolean updateUser(UserModel user) throws SQLException, ClassNotFoundException {
        try (Connection con = DBConnections.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(
                "UPDATE User SET Name=?, Password=?, PhoneNumber=?, Role=?, Address=? WHERE Email=?");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getPhoneNumber());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getEmail());
            return stmt.executeUpdate() > 0;
        }
    }

    public static boolean deleteUserByEmail(String email) throws SQLException, ClassNotFoundException {
        try (Connection con = DBConnections.getConnection()) {
            con.setAutoCommit(false); // Start transaction

            try {
                // 1. Delete from testdrives table where user is referenced
                PreparedStatement deleteTestDrives = con.prepareStatement("DELETE FROM testdrives WHERE UserEmail = ?");
                deleteTestDrives.setString(1, email);
                deleteTestDrives.executeUpdate();

                // 2. Delete from User table
                PreparedStatement deleteUser = con.prepareStatement("DELETE FROM User WHERE Email = ?");
                deleteUser.setString(1, email);
                int affectedRows = deleteUser.executeUpdate();

                con.commit(); // Commit transaction
                return affectedRows > 0;
            } catch (SQLException e) {
                con.rollback(); // Rollback in case of error
                e.printStackTrace();
                return false;
            }
        }
    }
    public String getUserRole(String email) throws SQLException, ClassNotFoundException {
        try (Connection con = DBConnections.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("SELECT Role FROM User WHERE Email = ?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Role");
            }
        }
        return null; // return null if role not found
    }
    
    
    
    
    
    // Check if email already exists
    private boolean checkEmailIfExists(String email) {
        try (Connection con = DBConnections.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT Email FROM User WHERE Email = ?"
            );
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();
            return result.next();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}