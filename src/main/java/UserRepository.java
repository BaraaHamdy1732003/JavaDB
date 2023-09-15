import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements UserRep<User> {
    private Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public List<User> findByName(String name) {
        List<User> matchingUsers = new ArrayList<>();

        try {
            String sql = "SELECT * FROM accounts WHERE first_name LIKE ? OR second_name LIKE ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setString(2, "%" + name + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User(1,"baraa","hamdy",30);
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setSecondName(resultSet.getString("second_name"));
                user.setAge(resultSet.getInt("age"));

                matchingUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matchingUsers;
    }

    @Override
    public List<User> findAllByAge(Integer age) {
        return null;
    }

    public List<User> findAll() {
        List<User> allUsers = new ArrayList<>();

        try {
            String sql = "SELECT * FROM accounts";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User(1,"baraa","hamdy",30);
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setSecondName(resultSet.getString("second_name"));
                user.setAge(resultSet.getInt("age"));

                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allUsers;
    }

}
