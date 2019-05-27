package lista01;

import org.h2.jdbcx.JdbcDataSource;

import java.sql.*;
import java.util.regex.Pattern;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

public class Main {

    public static void main(String[] args) {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:./test");
        ds.setUser("sa");
        ds.setPassword("sa");
        staticFileLocation("/public");
        try {
            Connection conn = ds.getConnection();

            //Questão 1 - Login com vulnerabilidade
            get("login", (req, res) -> {
                Statement statement = conn.createStatement();
                String username = req.queryParams("username");
                String password = req.queryParams("password");
                /*
                 * Nesta query os parâmetros não são tratados.
                 * Exemplo de injeção de SQL, informar no parâmetro "username" o valor: ' or 1=1 --
                 */
                ResultSet resultSet = statement.executeQuery(String.format(
                        "SELECT * FROM PUBLIC.USER WHERE username = '%s' AND password = '%s';",
                        username, password));
                return resultSet.next();
            });

            //Questão 2
            get("loginListaBranca", (req, res) -> {
                Statement statement = conn.createStatement();
                String username = req.queryParams("username");
                String password = req.queryParams("password");

                /*
                 * Nesta query é feito uma validação se username e password possuem apenas caracteres válidos(alfanuméricos).
                 */
                if (!(hasOnlyValidCharacters(username) && hasOnlyValidCharacters(password))) {
                    return false;
                }
                ResultSet resultSet = statement.executeQuery(String.format(
                        "SELECT * FROM PUBLIC.USER WHERE username = '%s' AND password = '%s';",
                        username, password));
                return resultSet.next();
            });

            //Questão 3
            get("loginQueryParametrizada", (req, res) -> {
                String username = req.queryParams("username");
                String password = req.queryParams("password");

                /*
                 * Nesta execução é utilizado query parâmetrizada, desta forma,
                 * independente do que o usuário informar, não sera tratado como comando.
                 */
                PreparedStatement preparedStatement = conn.prepareStatement(
                        "SELECT * FROM PUBLIC.USER WHERE username = ? AND password = ?;");
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.next();
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean hasOnlyValidCharacters(String value) {
        Pattern pattern = Pattern.compile("$[0-9a-zA-Z]*^");
        return pattern.matcher(value).find();
    }
}
