package Lesson2.Homework.Client.Controller;
import Lesson2.Homework.Client.gson.ClientListMessage;
import Lesson2.Homework.Client.gson.Message;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

class MessageService {

    private static final String HOST_ADDRESS_PROP = "server.address";
    private static final String HOST_PORT_PROP = "server.port";
    private static final String STOP_SERVER_COMMAND = "/end";

    private String hostAddress;
    private int hostPort;

    public static TextArea textArea;
    public static TextField textMessage;
    private Controller controller;
    private boolean needStopServerOnClosed;
    private Network network;
    private String nickname;
    private Thread timeWait;
    private int waitTime = 120;  // ожидание в секундах

    MessageService(Controller controller, boolean needStopServerOnClosed) {
        this.textMessage = controller.textMessage;
        this.textArea = controller.textArea;
        this.controller = controller;
        this.needStopServerOnClosed = needStopServerOnClosed;
        initialize();
    }

    private void initialize() {
        ChatHistory.createFile();
        // ChatHistory.getFile();
        readProperties();
        startConnectionToServer();
        timeWait();
    }

    private void readProperties() {
        Properties serverProperties = new Properties();
        try (InputStream inputStream = getClass().getResourceAsStream("/application.properties")) {
            serverProperties.load(inputStream);
            hostAddress = serverProperties.getProperty(HOST_ADDRESS_PROP);
            hostPort = Integer.parseInt(serverProperties.getProperty(HOST_PORT_PROP));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read application.properties file", e);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid port value", e);
        }
    }

    private void startConnectionToServer() {
        try {
            this.network = new Network(hostAddress, hostPort, this);
        } catch (IOException e) {
            throw new ServerConnectionException("Failed to connect to server", e);
        }
    }

    private void timeWait() {
        timeWait = new Thread(() -> {
            try {
                for (int i = 1; i < waitTime; i++) {
                    System.out.println(i);
                    TimeUnit.SECONDS.sleep(1);
                }
                alertAndClose();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        timeWait.start();
    }

    private void alertAndClose() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка аутентификации!");
            alert.setHeaderText("Предупреждение!");
            alert.setContentText("Превышено допустимое время на аутентификацию! \nПожалуйста, перезапустите приложение!");
            alert.showAndWait();
            controller.shutdown();
            System.exit(0);
        });
    }

    void sendMessage(String message) {
        network.send(message);
        if (!textMessage.getText().equals(""))
            ChatHistory.writeChatHistory("Я: " + textMessage.getText());
    }

    void processRetrievedMessage(String message) throws IOException {
        if (message.startsWith("/authok")) {
            timeWait.interrupt();
            nickname = message.split("\\s+")[1];
            controller.nickName = nickname;
            controller.authPanel.setVisible(false);
            controller.chatPanel.setVisible(true);
            ChatHistory.loadChatHistory();
        } else if (controller.authPanel.isVisible()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка аутентификации!");
            alert.setContentText(message);
            alert.showAndWait();
        } else {
            if (message.startsWith("{") && message.endsWith("}")) {
                Message msg = Message.fromJson(message);
                ClientListMessage clientListMessage = msg.clientListMessage;
                controller.clientList.setItems(FXCollections.observableArrayList(clientListMessage.online));
            } else {
                textArea.appendText(message + System.lineSeparator());
                if (!message.equals("")) {
                    if (!message.endsWith("лайн!"))
                        ChatHistory.writeChatHistory(message);
                }
            }
        }
    }

    void close() throws IOException {
        if (needStopServerOnClosed) {
            sendMessage(STOP_SERVER_COMMAND);
        }
        network.close();
        System.exit(0);
    }

}