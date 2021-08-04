package by.bsu.practice.jdbc.entity;

public class File {
    private int id;
    private int idParentCatalog;
    private String name;
    private int size;

    public File(){

    }

    public File(int id, int idParentCatalog, String name, int size) {
        this.id = id;
        this.idParentCatalog = idParentCatalog;
        this.name = name;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public int getIdParentCatalog() {
        return idParentCatalog;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdParentCatalog(int idParentCatalog) {
        this.idParentCatalog = idParentCatalog;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", idParentCatalog=" + idParentCatalog +
                ", name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}
