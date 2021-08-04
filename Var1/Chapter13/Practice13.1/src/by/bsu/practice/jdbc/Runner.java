/* Выполнить следующие действия:
- организацию соединения с БД вынести в отдельный класс, метод которого возвращает соединение;
- создать БД. Привести таблиц к одной из нормальных форм;
- создать класс для выполнения запросов на извлечение информации из БД с использованием компилированных запросов;
- создать класс на модификацию информации.

Файловая система. В БД хранится информация о дереве каталогов файловой системы - каталоги, подкаталоги, файлы.
Для каталогов необходимо хранить:
- родительский каталог;
- название.
Для файлов необходимо хранить:
- родительский каталог;
- название;
- место, занимаемое на диске.

- Определить полный путь заданного файла (каталога).
- Подсчитать кол-во файлов в заданном каталоге, включая вложенные файлы и каталоги.
- Подсчитать место, занимаемое на диске содержимым заданного каталога.
- Найти в базе файлы по заданной маске с выдачей полного пути.
- Переместить файлы и подкаталоги из одного каталога в другой.
- Удалить файлы и каталоги заданного каталога.
*/
package by.bsu.practice.jdbc;

import by.bsu.practice.jdbc.exception.InvalidDataException;
import by.bsu.practice.jdbc.service.OperationsSelect;
import by.bsu.practice.jdbc.service.OperationsUpdate;

public class Runner {

    public static void main(String... args) throws InvalidDataException {

        OperationsSelect.fullPathCatalog("Photos");
        OperationsSelect.fullPathFile("TenLab");
        OperationsSelect.countFiles("C");
        OperationsSelect.countSizeCatalog("Videos");
        OperationsSelect.findFileByMask("Photo*");
        OperationsUpdate.changeFileCatalog("Video3","FunnyVideos");
        OperationsUpdate.deleteFiles("Movies");
    }

}
