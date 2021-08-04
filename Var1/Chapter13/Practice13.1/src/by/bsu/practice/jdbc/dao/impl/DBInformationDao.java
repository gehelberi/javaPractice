package by.bsu.practice.jdbc.dao.impl;

import by.bsu.practice.jdbc.connection.DBConnection;
import by.bsu.practice.jdbc.dao.InformationDAO;
import by.bsu.practice.jdbc.entity.Catalog;
import by.bsu.practice.jdbc.entity.File;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBInformationDao implements InformationDAO {


    //получаем с базы данных все каталоги
    @Override
    public List<Catalog> getAllCatalog() {
        Connection dbConnection = null;
        Statement statement = null;
        List<Catalog> catalogs = new ArrayList<>();
        try{
            //извлекаем из бд каталоги
            String sqlQuery = "SELECT idcatalogs, parentCatalogID, name FROM catalog";
            dbConnection = DBConnection.getConnection();
            statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                //создаем объект класса Catalog с данными из бд для каждой строки
                Catalog catalog = new Catalog();
                catalog.setId(resultSet.getInt("idcatalogs"));
                catalog.setName(resultSet.getString("name"));
                catalog.setIdParentCatalog(resultSet.getInt("parentCatalogID"));
                catalogs.add(catalog);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return catalogs;
    }

    //аналогично для файлов
    @Override
    public List<File> getAllFile(){
        Connection dbConnection = null;
        Statement statement = null;
        List<File> files = new ArrayList<>();
        try{
            String sqlQuery = "SELECT idfiles, parentCatalogID, name, size FROM file";
            dbConnection = DBConnection.getConnection();
            statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                File file = new File();
                file.setId(resultSet.getInt("idfiles"));
                file.setName(resultSet.getString("name"));
                file.setSize(resultSet.getInt("size"));
                file.setIdParentCatalog(resultSet.getInt("parentCatalogID"));
                files.add(file);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return files;
    }


}
