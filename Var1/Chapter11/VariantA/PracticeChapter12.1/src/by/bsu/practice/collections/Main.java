// Ввести строки из файла, записать в список. Вывести строки в файл в обратном порядке.
package by.bsu.practice.collections;

import by.bsu.practice.collections.exception.FileException;
import by.bsu.practice.collections.read.FileWork;

import java.util.List;

public class Main {
    public static void main(String... args) {
        try {
            //путь для файла с исходными строками
            String filepath1 = "src\\resourses\\strings.txt";
            //для файла с результатом
            String filepath2 = "src\\resourses\\result.txt";

            List<String> list = FileWork.readStrings(filepath1);
            FileWork.saveStringsInverse(list, filepath2);
        }
        catch (FileException e){
            e.printStackTrace();
        }
    }
}
