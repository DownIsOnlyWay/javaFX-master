package sample.model;

import java.sql.*;

public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/dakar";


    public static void insert(User user) {


        String query = "INSERT INTO `user`( `user_name`, `password`, `email`) VALUES (? , ? , ?)";

        try {
            Connection conn = DriverManager.getConnection(URL, "root", "");
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getEmail());

            st.executeUpdate();
            st.close();

            System.out.println("Įrašas sukurtas sėkmingai");

        } catch (SQLException e) {

            System.out.println("Įvyko klaida kuriant naują įrašą");
            e.printStackTrace();
        }
    }

    public static String getBCryptPassword(String username) {
        String query = "SELECT `password` FROM `user` WHERE user_name = ?";

        String bCryptPassword = "";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, username);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                bCryptPassword = resultSet.getString("password");
            }
            ps.close();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bCryptPassword;
    }


}
