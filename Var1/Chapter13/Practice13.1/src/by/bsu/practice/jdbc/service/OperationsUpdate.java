package by.bsu.practice.jdbc.service;

import by.bsu.practice.jdbc.connection.DBConnection;
import by.bsu.practice.jdbc.dao.impl.DBInformationDao;
import by.bsu.practice.jdbc.entity.Catalog;
import by.bsu.practice.jdbc.entity.File;
import by.bsu.practice.jdbc.exception.InvalidDataException;
import by.bsu.practice.jdbc.factory.DAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OperationsUpdate {

    private static final DBInformationDao dbInformationDao = OperationsSelect.dbInformationDao;
    //список со всеми каталогами и файлами из бд
    private static final List<Catalog> catalogs = OperationsSelect.catalogs;
    private static final List<File> files = OperationsSelect.files;

    public static void changeFileCatalog(String file, String newCatalog) throws InvalidDataException {

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


        Catalog correctCatalog = new Catalog();
        isFind = false;
        //находим в списке каталогов нам нужный
        for (Catalog catalog1 : catalogs)
            if(catalog1.getName().equals(newCatalog)){
                correctCatalog = catalog1;
                isFind = true;
                break;
            }

        //если не находим бросаем исключение
        if(!isFind)
            throw new InvalidDataException("Catalog with this name does not exist!");

        int catalogId = correctCatalog.getId();
        int fileId = correctFile.getId();

        PreparedStatement preparedStatement = null;
        Connection dbConnection = null;
        try{
            dbConnection = DBConnection.getConnection();
            String sqlQuery = "UPDATE file SET parentCatalogID = ? WHERE idfiles=?";
            preparedStatement = dbConnection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,catalogId);
            preparedStatement.setInt(2,fileId);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public static void deleteFiles(String catalog) throws InvalidDataException {
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

        //id каталога
        int id = correctCatalog.getId();

        //рекурсивно удаляем все подкаталоги каталога
        for(Catalog catalog1 : OperationsSelect.checkCatalogsInCatalog(correctCatalog)){
            PreparedStatement preparedStatement = null;
            Connection dbConnection = null;
            try{
                dbConnection = DBConnection.getConnection();
                String sqlQuery = "DELETE FROM catalog WHERE idcatalogs=? ";
                preparedStatement = dbConnection.prepareStatement(sqlQuery);
                preparedStatement.setInt(1,catalog1.getId());
                preparedStatement.executeUpdate();
                deleteFiles(catalog1.getName());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }
}
