package Lesson3;

import java.io.*;
import java.nio.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {

//        File file = new File("123");
//
//        String[] str = file.list(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.endsWith(".txt");
//            }
//        });
//
//        for (String o:str) {
//            System.out.println(o);
//        }

        File file = new File("123/test.txt");
 /*        System.out.println(file.exists());
        System.out.println(file.length());
        System.out.println(file.canWrite());
        System.out.println(new Date(file.lastModified()));


        System.out.println(file.isDirectory());
        System.out.println(file.isFile());

        System.out.println(file.getPath());
//        System.out.println(file.getPath());
//        System.out.println(file.getAbsolutePath().getParentFile().getName());*/

//
/*        try (FileWriter writer = new FileWriter(file, true);) {
            String text = "\nHello World";
            writer.write(text);
//            ---------
            writer.append('\n');
            writer.append('E');
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }*/

//        long t = System.currentTimeMillis();
//        try (FileInputStream in = new FileInputStream(file)) {
//            int x;
//
//            while ((x = in.read()) != -1) {
//                System.out.println((char) x);
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//        System.out.println(System.currentTimeMillis() - t);


//        try (InputStreamReader in = new InputStreamReader(new FileInputStream(file), "UTF-8")) {
//            int x;
//
//            while ((x = in.read()) != -1) {
//                System.out.println((char) x);
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }

/*        long t = System.currentTimeMillis();

        try (FileInputStream in = new FileInputStream(file)) {
            int x;
            byte[] arr = new byte[512];

            while ((x = in.read(arr)) > 0) {
                System.out.println(new String(arr, 0, x));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(System.currentTimeMillis() - t);*/

/*    FileReader fr = new FileReader(file);
    BufferedReader br = new BufferedReader(fr);

    String str;
    while ((str = br.readLine()) != null) {
        System.out.println(str);
        }*/

        ArrayList<InputStream> ar = new ArrayList<>();

        ar.add(new FileInputStream("123/test.txt"));
        ar.add(new FileInputStream("123/text2.txt"));

        SequenceInputStream in = new SequenceInputStream(Collections.enumeration(ar));

        int x;

            while ((x = in.read()) != -1) {
                System.out.println((char) x);
            }


    }
}
