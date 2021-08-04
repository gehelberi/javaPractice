package by.bsu.practice.collections.read;

import by.bsu.practice.collections.exception.FileException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWork {
    //метод, читающий строки из файла в список
    public static List<String> readStrings(String filePath) throws FileException {
        List<String> strings = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String str = null;
            while ((str = br.readLine()) != null) {
                if (!str.isEmpty()) {
                    strings.add(str);
                }
            }
        }
        catch (IOException e) {
            throw new FileException("Error with reading from file", e);
        }
        return strings;
    }

//метод, записывающий строки в файл в обратном порядке
    public static void saveStringsInverse(List<String> strings, String filePath) throws FileException {

        try(FileWriter writer = new FileWriter(filePath, false))
        {
            for(int i = strings.size() - 1;i > 0;i--){
                writer.write(strings.get(i));
                writer.append('\n');
            }
            if(strings.size() > 0){
                writer.write(strings.get(0));
            }
            writer.flush();
        }
        catch(IOException e){
            throw new FileException("Error with writing to file file", e);
        }
    }
}
