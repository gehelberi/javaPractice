package by.bsu.practice.jdbc.dao;

import by.bsu.practice.jdbc.entity.Catalog;
import by.bsu.practice.jdbc.entity.File;

import java.util.List;


public interface InformationDAO {
    public List<Catalog> getAllCatalog();
    public List<File> getAllFile();
}
