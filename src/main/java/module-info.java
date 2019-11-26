module NetworkChatMaven {
    requires com.google.gson;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires org.apache.commons.lang3;
    requires java.desktop;

    exports Lesson2.Homework.Client;
    exports Lesson2.Homework.Client.gson;
    exports Lesson2.Homework.Server.gson;
    exports Lesson2.Homework.Client.Controller;

//    opens NetworkChatMaven to javafx.fxml, javafx.graphics;
    opens Lesson2.Homework.Client.Controller to javafx.fxml;
}