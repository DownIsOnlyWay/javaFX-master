package sample.model;

import java.sql.*;
import java.util.ArrayList;

public class DakarDAO {


    private static final String URL = "jdbc:mysql://localhost:3306/dakar";

    public static void insert(Dakar dakar) {


        String query = "INSERT INTO `dakar`(`team_name`, `name_surname`,`sponsor`,`racing_cars`,`members`) VALUES(? , ? , ? , ? , ? )";

        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, dakar.getTeamName());
            statement.setString(2, dakar.getNameSurname());
            statement.setString(3, dakar.getSponsor());
            statement.setString(4, dakar.getRacingCars());
            statement.setInt(5, dakar.getMembers());

            statement.executeUpdate();
            statement.close();
            System.out.println("Irasas sukurtas");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Dakar> viewAll() {

        String query = "SELECT * FROM `dakar`";
        ArrayList<Dakar> list = new ArrayList<>();
        try {

            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement st = connection.prepareStatement(query);

            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                list.add(new Dakar(
                        resultSet.getInt("id"),
                        resultSet.getString("team_name"),
                        resultSet.getString("name_surname"),
                        resultSet.getString("sponsor"),
                        resultSet.getString("racing_cars"),
                        resultSet.getInt("members")
                ));
            }
            st.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            return list;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public static boolean teamName(String teamName) {


        String query = "SELECT * FROM `dakar` WHERE `team_name` = ?";
        try {

            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, teamName);

            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public static int isSameTeamName(String teamName) {

        String query = "SELECT `id` FROM `dakar` WHERE `team_name` = ?";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, teamName);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void update(Dakar dakar) {

        String query = "UPDATE `dakar` SET `team_name` = ?, `name_surname` = ?, `sponsor` = ?, `racing_cars` = ?, `members` = ? WHERE `id` = ?";

        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dakar.getTeamName());
            preparedStatement.setString(2, dakar.getNameSurname());
            preparedStatement.setString(3, dakar.getSponsor());
            preparedStatement.setString(4, dakar.getRacingCars());
            preparedStatement.setInt(5, dakar.getMembers());
            preparedStatement.setInt(6, dakar.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteByID(int id) {

        String query = "DELETE FROM `dakar` WHERE `id` = ?";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
