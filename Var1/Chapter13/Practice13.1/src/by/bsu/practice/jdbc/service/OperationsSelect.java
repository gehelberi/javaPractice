package by.bsu.practice.jdbc.service;

import by.bsu.practice.jdbc.dao.impl.DBInformationDao;
import by.bsu.practice.jdbc.entity.Catalog;
import by.bsu.practice.jdbc.entity.File;
import by.bsu.practice.jdbc.exception.InvalidDataException;
import by.bsu.practice.jdbc.factory.DAOFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperationsSelect {

    public static final DBInformationDao dbInformationDao = DAOFactory.getInstance().getDbFileDAO();
    //список со всеми каталогами и файлами из бд
    public static final List<Catalog> catalogs = dbInformationDao.getAllCatalog();
    public static final List<File> files = dbInformationDao.getAllFile();

    //находим полный путь к каталогу по его названию
    public static String fullPathCatalog(String catalog) throws InvalidDataException {

        StringBuilder result = new StringBuilder();
        Catalog correctCatalog = new Catalog();
        boolean isFind = false;
        //находим в списке каталогов нам нужный
        for (Catalog catalog1 : catalogs)
            if(catalog1.getName().equals(catalog)){
                correctCatalog = catalog1;
                isFind = true;
                break;
            }

        //если не находим бросаем исключение
        if(!isFind)
            throw new InvalidDataException("Catalog with this name does not exist!");

        //список с id каталогов, которые находятся в нужном пути
        List<Integer> list = new ArrayList<>();
        //добавляем его id
        list.add(correctCatalog.getId());

        //пока не находим каталог, в котором id равен id родителя(единственным таким является начальный)
        // добавляем id всех нужных каталогов в список
        while(correctCatalog.getIdParentCatalog() != correctCatalog.getId()){
            int idParent = correctCatalog.getIdParentCatalog();
            list.add(idParent);
            correctCatalog = findCatalogById(idParent);
        }

        //результатом будут названия всех тех каталогов, id которых находятся в списке
        for(int i = list.size() - 1; i >= 0; i--){
            result.append(findCatalogById(list.get(i)).getName() + "/");
        }

        return result.toString();
    }

    //находим полный путь к файлу по его названию
    public static String fullPathFile(String file) throws InvalidDataException{

        StringBuilder result = new StringBuilder();
        File correctFile = new File();
        boolean isFind = false;
        //находим в списке файлов нам нужный
        for (File file1 : files)
            if(file1.getName().equals(file)){
                correctFile = file1;
                isFind = true;
                break;
            }

        //если не находим бросаем исключение
        if(!isFind)
            throw new InvalidDataException("File with this name does not exist!");

        //записываем в результат полный путь его родительского каталога
        String parentCatalog = findCatalogById(correctFile.getIdParentCatalog()).getName();
        result.append(fullPathCatalog(parentCatalog));
        //добавляем название самого файла
        result.append(correctFile.getName());
        return result.toString();
    }

    //находим каталог по переданному id
    private static Catalog findCatalogById(int id) throws InvalidDataException {

        Catalog result = null;
        boolean isFind = false;
        for(Catalog catalog : catalogs){
            if(catalog.getId() == id){
                result = catalog;
                isFind = true;
                break;
            }
        }
        //если такого нет бросаем исключение
        if(!isFind)
            throw new InvalidDataException("Catalog with this id does not exist!");

        return result;
    }


    //считаем кол-во файлов в каталоге
    public static int countFiles(String catalog) throws InvalidDataException {

        StringBuilder result = new StringBuilder();
        Catalog correctCatalog = new Catalog();
        boolean isFind = false;
        //находим в списке каталогов нам нужный
        for (Catalog catalog1 : catalogs)
            if(catalog1.getName().equals(catalog)){
                correctCatalog = catalog1;
                isFind = true;
                break;
            }

        //если не находим бросаем исключение
        if(!isFind)
            throw new InvalidDataException("Catalog with this name does not exist!");

        //счетчик количества файлов
        int filesCount = 0;
        //прибавляем файлы в текущем каталоге
        filesCount += countFilesCatalog(correctCatalog);

        //рекурсивно считает кол-во файлов для каждого подкаталога пока не будут подсчитаны все файлы
        for(Catalog catalog1 : checkCatalogsInCatalog(correctCatalog)){
            filesCount += countFiles(catalog1.toString());
        }

        return filesCount;

    }

    //считаем размер всех файлов каталога включая подкаталоги
    public static int countSizeCatalog(String catalog) throws InvalidDataException {
        StringBuilder result = new StringBuilder();
        Catalog correctCatalog = new Catalog();
        boolean isFind = false;
        //находим в списке каталогов нам нужный
        for (Catalog catalog1 : catalogs)
            if(catalog1.getName().equals(catalog)){
                correctCatalog = catalog1;
                isFind = true;
                break;
            }

        //если не находим бросаем исключение
        if(!isFind)
            throw new InvalidDataException("Catalog with this name does not exist!");

        //счетчик размера файлов
        int sizeCount = 0;
        //прибавляем файлы в текущем каталоге
        sizeCount += countSizeOneCatalog(correctCatalog);

        //рекурсивно считает кол-во файлов для каждого подкаталога пока не будут подсчитаны все файлы
        for(Catalog catalog1 : checkCatalogsInCatalog(correctCatalog)){
            sizeCount += countSizeCatalog(catalog1.toString());
        }

        return sizeCount;
    }

    //считает файлы в конкретном каталоге
    private static int countFilesCatalog(Catalog catalog){
        int count = 0;
        for(File file : files){
            if(file.getIdParentCatalog() == catalog.getId())
                count++;
        }
        return count;
    }

    //считаем размер конкретного каталога
    private static int countSizeOneCatalog(Catalog catalog){
        int size = 0;
        for(File file : files){
            if(file.getIdParentCatalog() == catalog.getId())
                size += file.getSize();
        }
        return size;
    }

    //ищет подкаталоги в данном каталоге и возвращает их список
    public static List<Catalog> checkCatalogsInCatalog(Catalog cat){
        List<Catalog> list = new ArrayList<>();
        for(Catalog catalog : catalogs){
            if(catalog.getIdParentCatalog() == cat.getId() && cat.getId() != catalog.getId())
                list.add(catalog);
        }
        return list;
    }

    //находим полные пути к файлам через маску
    public static List<String> findFileByMask(String mask) throws InvalidDataException {

        List<File> needFiles = new ArrayList<>();
        //через регулярные выражения ищем совпадения среди названий файлов
        Pattern pattern = Pattern.compile(mask);
        for(File file : files){
            Matcher matcher = pattern.matcher(file.getName());
            if(matcher.find())
                needFiles.add(file);
        }

        //для каждого находим полный путь
        List<String> paths = new ArrayList<>();
        for(File file : needFiles){
            paths.add(fullPathFile(file.getName()));
        }

        return paths;

    }



}
