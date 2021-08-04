package by.bsu.practice.threads.entity;

import java.util.concurrent.atomic.AtomicInteger;

//класс Порт(сделан синглтоном)
public class  Port {
    private static final Port instance = new Port();
    //число причалов
    public static final int BERTHS = 5;
    //максимальная вместимость порта
    public static final int STORAGE_CAPACITY = 5000;
    //массив со всеми причалами
    private Berth[] berths;
    //текущая заполненность порта
    private AtomicInteger storage;

    private Port() {
        this.berths = new Berth[BERTHS];

        for (int i = 0;i < BERTHS;i++)
            berths[i]= new Berth();

        this.storage = new AtomicInteger();
        storage.set(0);
    }

    public static Port getInstance() {
        return instance;
    }

    public Berth[] getBerths() {
        return berths;
    }

    public int getStorage() {
        return storage.get();
    }

    public void setStorage(int storage) {
        this.storage.set(storage);
    }

}
