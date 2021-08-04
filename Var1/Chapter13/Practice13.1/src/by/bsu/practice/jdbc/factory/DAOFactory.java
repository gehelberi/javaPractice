package by.bsu.practice.jdbc.factory;


import by.bsu.practice.jdbc.dao.impl.DBInformationDao;

//фабрика для дао класса(сделан синглтоном)
public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return instance;
    }

    private final DBInformationDao dbFileDao = new DBInformationDao();

    public DBInformationDao getDbFileDAO(){
        return dbFileDao;
    }

}
