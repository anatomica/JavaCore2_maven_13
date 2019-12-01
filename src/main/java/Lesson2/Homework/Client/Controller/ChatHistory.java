package Lesson2.Homework.Client.Controller;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ChatHistory {

    private static int howManyMsgLoad = 5; // кол-во загрузки сообщений из истории
    private String resource = "/Client/ChatHistory.txt";
    private URL res = getClass().getResource(resource);
    private static File fileHistory;
    private static String pathToHistory;

    static void createFile() {
        try {
            URI uri = MessageService.class.getProtectionDomain().getCodeSource().getLocation().toURI();
            pathToHistory = new File(uri).getParent() + "\\ChatHistory.txt";
            System.out.println(pathToHistory);
            fileHistory = new File(pathToHistory);
            if (fileHistory.createNewFile()) System.out.println("Файл истории создан!");
            else System.out.println("Файл истории ранее создан и найден!");
        } catch (IOException | URISyntaxException e) {
            System.out.println(e.getMessage());
        }
    }

    void getFile() {
//        try {
//            InputStream input = getClass().getResourceAsStream(resource);
//            OutputStream out = new FileOutputStream(file);
//            int read;
//            byte[] bytes = new byte[1024];
//            while ((read = input.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            } out.close();
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//            System.out.println(MessageService.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
//            System.out.println(MessageService.class.getResource("/Client/ChatHistory.txt").toExternalForm());
//            System.out.println(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Client/ChatHistory.txt")).toExternalForm());
//            System.out.println(Objects.requireNonNull(getClass().getClassLoader().getResource("Client/ChatHistory.txt")).getFile());

//        } catch (URISyntaxException e) {
//            System.out.println(e.getMessage());
//        }
    }

    static void writeChatHistory(String messageText) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileHistory, true), "UTF-8"))) {
            bw.write(messageText + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static void loadChatHistory() throws IOException {
        if (fileHistory.createNewFile()) MessageService.textArea.appendText("\n");
        MessageService.textArea.appendText("Последние " + howManyMsgLoad + " сообщений:");
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileHistory), "UTF-8"));
        List<String> listHistory = new ArrayList<>();

        String tmp;
        while ((tmp = br.readLine()) != null) {
            listHistory.add("\n" + tmp);
        }
        Collections.reverse(listHistory);

        List<String> reverseListHistory = new ArrayList<>();
        int count = 1;
        for (int i = 0; i < listHistory.size(); i++) {
            if (count <= howManyMsgLoad) {
                reverseListHistory.add(listHistory.get(i));
                count++;
            }
        }
        Collections.reverse(reverseListHistory);

        StringBuilder chatHistory = new StringBuilder();
        for (String s : reverseListHistory) {
            chatHistory.append(s);
        }
        MessageService.textArea.appendText(chatHistory + "\n");
    }

}
