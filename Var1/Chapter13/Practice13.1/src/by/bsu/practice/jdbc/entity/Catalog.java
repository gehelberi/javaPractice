package by.bsu.practice.jdbc.entity;

public class Catalog {
    private int id;
    private int idParentCatalog;
    private String name;

    public Catalog(){}

    public Catalog(int id, int idParentCatalog, String name){
        this.id = id;
        this.idParentCatalog = idParentCatalog;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdParentCatalog() {
        return idParentCatalog;
    }

    public void setIdParentCatalog(int idParentCatalog) {
        this.idParentCatalog = idParentCatalog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
