package Lesson2.Homework.Server.auth;
import org.jetbrains.annotations.Nullable;
import java.sql.SQLException;

public interface AuthService {

    void start();
    void stop();

    @Nullable
    String getNickByLoginPass(String login, String pass) throws SQLException;

}
