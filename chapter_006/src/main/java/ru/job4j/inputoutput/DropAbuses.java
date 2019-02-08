package ru.job4j.inputoutput;

import java.io.*;

public class DropAbuses {

    void dropAbuses(InputStream in, OutputStream out, String[] abuse) throws UnsupportedEncodingException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "Cp1251"));

        try {
            while (reader.ready()) {
                String read = reader.readLine();
                read
                System.out.println(read);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        DropAbuses dropAbuses = new DropAbuses() ;

        FileInputStream fr = new FileInputStream("d:/1.txt");
        FileOutputStream fw = null;

        String[] abused = {"принадлежала", "попросил", "недоволен"};

        try {
            fw = new FileOutputStream("d:/2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            dropAbuses.dropAbuses(fr, fw, abused);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
