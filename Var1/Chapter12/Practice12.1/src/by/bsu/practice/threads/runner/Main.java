// Разработать многопоточное приложение. Использовать возможности, предоставляемые пакетом java.util.concurrent.
// Не использовать слово synchronized. Все сущности, желающие получить доступ к ресурсу, должны быть потоками.

// Порт. Корабли заходят в порт для разгрузки/загрузки контейнеров. Число контейнеров, находящихся в текущий момент в
// порту и на корабле, должно быть неотрицательным и превышающим заданную грузоподъемность судна и вместимость порта.
// В порту работает несколько причалов. У одного причала может стоять один корабль. Корабль может загружаться у причала,
// разгружаться или выполнять оба действия.
package by.bsu.practice.threads.runner;

import by.bsu.practice.threads.entity.Port;
import by.bsu.practice.threads.entity.Ship;
import by.bsu.practice.threads.service.PortWorking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {
    private static final Port PORT = Port.getInstance();
    private static final Semaphore SEMAPHORE = new Semaphore(Port.BERTHS, true);
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    private static PortWorking portWorking = new PortWorking(SEMAPHORE, PORT);

    public static void main(String[] args) {
        //создаем корабли с различной грузоподъемностью и загруженностью и их одновременное функционирование
        List<Callable<Integer>> callable = new ArrayList<>();

        callable.add(new Ship(100,250,portWorking));
        callable.add(new Ship(150,150,portWorking));
        callable.add(new Ship(0,500,portWorking));
        callable.add(new Ship(0,250,portWorking));
        callable.add(new Ship(100,250,portWorking));
        callable.add(new Ship(150,150,portWorking));
        callable.add(new Ship(100,250,portWorking));
        callable.add(new Ship(0,250,portWorking));

        try {
            executorService.invokeAll(callable);
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
