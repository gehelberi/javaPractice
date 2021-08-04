package by.bsu.practice.threads.service;

import by.bsu.practice.threads.entity.Port;
import by.bsu.practice.threads.entity.Ship;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;


public class PortWorking {
    private Semaphore semaphore;
    private ReentrantLock lock = new ReentrantLock();
    private Port port;

    public PortWorking(Semaphore semaphore, Port port) {
        this.semaphore = semaphore;
        this.port = port;
    }

    //ищем свободный причал
    public void findBerth(Ship ship) {
        try {
            // получаем разрешение семафора
            semaphore.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // создаем лок, чтоб не было конфликта потоков
        lock.lock();
        int j = 0;
        boolean b = false;
        //ищем свободный причал
        while (!b) {
            // как только находим свободный занимаем его текущим кораблем
            if (!port.getBerths()[j].isBusy().get()) {
                port.getBerths()[j].setBusy(true);
                ship.setBerthNum(j);
                b = true;
            }
            j++;
        }
        lock.unlock();
        System.out.println("Ship " + Thread.currentThread().getName() + " had arrived to berth num " + ship.getBerthNum());

    }


    public void loadUnloadShip(Ship ship) {
        lock.lock();
        //если корабль пустой, загружаем его на полную вместимость с порта
        if(ship.getStorage() == 0){
            int min = Math.min(ship.getCAPACITY(), port.getStorage());

            port.setStorage(Math.abs(ship.getCAPACITY() - port.getStorage()));
            ship.setStorage(min);
        }
        //если полный разгружаем
        else{
            if(ship.getStorage() + port.getStorage() <= Port.STORAGE_CAPACITY){
                port.setStorage(ship.getStorage() + port.getStorage());
                ship.setStorage(0);
            }
            else {
                port.setStorage(Port.STORAGE_CAPACITY);
                ship.setStorage(ship.getStorage() + port.getStorage() - Port.STORAGE_CAPACITY);
            }
        }
        //освобождаем причал
        port.getBerths()[ship.getBerthNum()].setBusy(false);
        System.out.println("Ship " + Thread.currentThread().getName() + " had moved from berth num " + ship.getBerthNum());
        lock.unlock();
        semaphore.release();
    }
}
