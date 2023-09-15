import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "22121";
    private static final String DB_URL = "jbdc:postgresql://localhost:5432/accounts";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from accounts");

        while (result.next()) {
            System.out.println(result.getInt("id") + " " + result.getString("first_name"));
        }

        Scanner scanner = new Scanner(System.in);
        String firstName = scanner.nextLine();
        String secondName = scanner.nextLine();
        int age = scanner.nextInt();


        String sqlInsertUser = "insert into accounts(first_name, second_name, age ) values (' " +
                firstName + " ' , ' " + secondName + " ' , ' " + age + " ');";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);
        preparedStatement.setString(1,firstName);
        preparedStatement.setString(2,secondName);
        preparedStatement.setInt(3,age);


        int affectedRows = statement.executeUpdate(sqlInsertUser);
        preparedStatement.executeUpdate(sqlInsertUser);
        System.out.println("было добавлено" + affectedRows + "строк");






    }
}