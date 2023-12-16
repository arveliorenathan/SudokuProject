package Controller;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.sql.*;

public class Database {
    private static Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    static String id_user;

    public Database() {
        try {
            String url = "jdbc:mysql://localhost:3306/sudoku";
            String username = "root";
            String password = "";
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            System.out.println("Koneksi sukses!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void setId_user(String id_user){
        Database.id_user = id_user;
    }
    public static String getId_user(){
        return id_user;
    }

    public static boolean register(String username, String password) throws SQLException{
        String insertQuery = "INSERT INTO user (username, password) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, String.valueOf(username));
            statement.setString(2, String.valueOf(password));

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Registrasi Succsess", "Silahkan Login", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                return false;
            }
        }
        catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Username Anda Sudah Terdaftar", "Registrasi Gagal", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static  boolean  loginUser(String username, String password) {
        String query = "SELECT * FROM user WHERE username = '"+username+"'";
        try (Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()){
                setId_user(rs.getString("id_user"));
                String passDB = rs.getString("password");

                if(password.equals(passDB)){
                    System.out.println("Login Succes");
                    JOptionPane.showMessageDialog(null,"login succes");
                    return true;
                } else {
                    System.out.println("Login Failed");
                    JOptionPane.showMessageDialog(null, "Login Failed");
                }
            } else {
                System.out.println("Login Failed");
                JOptionPane.showMessageDialog(null, "Login Failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public int updateTime(int minute, int second) throws SQLException {
        String updateQuery;
        String checkQuery = "SELECT menit, detik FROM score WHERE id_user = ?";

        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
            checkStatement.setString(1, getId_user());
            ResultSet rs = checkStatement.executeQuery();

            if (rs.next()) {
                // Jika sudah ada, lakukan update
                updateQuery = "UPDATE score SET menit = ?, detik = ? WHERE id_user = ?";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                    updateStatement.setInt(1, minute);
                    updateStatement.setInt(2, second);
                    updateStatement.setString(3, getId_user());

                    return updateStatement.executeUpdate();
                }
            } else {
                // Jika belum ada, lakukan insert
                updateQuery = "INSERT INTO score (id_user, menit, detik) VALUES (?, ?, ?)";
                try (PreparedStatement insertStatement = connection.prepareStatement(updateQuery)) {
                    insertStatement.setString(1, getId_user());
                    insertStatement.setInt(2, minute);
                    insertStatement.setInt(3, second);

                    return insertStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }



    public ResultSet getLeaderboard () throws SQLException{
        String query = "SELECT username, menit, detik FROM user JOIN score USING (id_user) ORDER BY menit, detik ASC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }
}
