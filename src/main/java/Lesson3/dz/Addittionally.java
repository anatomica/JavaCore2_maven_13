package Lesson3.dz;


import java.sql.*;

class Additionally {
    private static Connection conn;
    private static Statement stmt;
    private static PreparedStatement pstmt;

    public static void main(String[] args) throws SQLException {

        //1. Подключиться к БД
        try {
            connection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        //2. Создать таблицу товаров (good_id, good_name, good_price) запрсом из Java пиложения.
        stmt.execute("CREATE TABLE IF NOT EXISTS goods (\n" +
                "good_id integer PRIMARY KEY,\n" +
                "good_name VARCHAR NOT NULL,\n" +
                "good_price int NOT NULL" +
                ");");

        System.out.println("Table created");

        //3. При запуске приложения очистить таблицу и заполнить 10000 товаров
        stmt.execute("DELETE FROM goods");

        System.out.println("Data deleted");

        conn.setAutoCommit(false);
        for (int i = 0; i < 10000; i++) {
            stmt.addBatch("INSERT INTO goods (good_name, good_price) VALUES ('Name" + (i+1) + "', " + (i+1) + ")");
        }
        stmt.executeBatch();
        conn.setAutoCommit(true);

        System.out.println("Data updated");

        //4. Написать консольное приложение, которое позволяет узнать цену товара по его имени, либо вывести сообщение «Такого товара нет», если товар не обнаружен в базе.
        findPriceForProduct("Name34");
        findPriceForProduct("Name10004");

        //5. Добавить возможность изменения цены товара. Указываем имя товара и новую цену.
        replacePriceForProduct("Name55", "1234");

        //6. Вывести товары в заданном ценовом диапазоне.
        findGoodInPrice(50,60);

        disconnect();
    }

    private static void findGoodInPrice(int priceFrom, int priceTo) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT good_name,good_price FROM goods WHERE good_price >= '" + priceFrom + "' and good_price <= '" + priceTo + "';");

        System.out.println("good_name | good_price");
        while (rs.next()) {
            System.out.println(rs.getString("good_name") + " | " + rs.getInt(2));
        }
    }

    private static void replacePriceForProduct(String name, String price) throws SQLException {
        System.out.print("To replace ");
        findPriceForProduct(name);
        stmt.executeUpdate("UPDATE goods SET good_price = '" + price + "' WHERE good_name = '" + name + "';");
        System.out.print("After replace ");
        findPriceForProduct(name);
    }

    private static void findPriceForProduct(String s) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT good_price FROM goods WHERE good_name = '" + s + "';");

        if (rs.next()) {
            System.out.println("Price: " + rs.getInt(1));
        }
        else {
            System.out.println("Product not found!");
        }
    }

    private static void connection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:src/Lesson2/mainDB.db");
        stmt = conn.createStatement();
    }

    private static void disconnect() throws SQLException {
        conn.close();
    }

    public static void replaceLogin(String login, String newLogin) throws SQLException {
        try {
            connection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        stmt.executeUpdate("UPDATE auth SET login = '" + newLogin + "' WHERE login = '" + login + "';");

        disconnect();
    }
}