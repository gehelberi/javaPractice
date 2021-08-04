package by.bsu.practice.threads.entity;


import by.bsu.practice.threads.service.PortWorking;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

//класс Корабль(является потоком)
public class Ship implements Callable<Integer> {

        //текщая загруженность корабля
        private AtomicInteger storage = new AtomicInteger();
        //грузоподъемность
        private final int CAPACITY;
        //номер причала
        private AtomicInteger berthNum = new AtomicInteger();
        private final PortWorking portWorking;

        public Ship(int storage, int capacity, PortWorking portManager) {
            this.storage.set(storage);
            this.CAPACITY = capacity;
            this.portWorking = portManager;
        }

        @Override
        public Integer call() {
            //при запуске ищем свободный причал, затем выгружаем/загружаем и освобождаем причал
            portWorking.findBerth(this);
            portWorking.loadUnloadShip(this);

            return null;
        }

        public int getStorage() {
            return storage.get();
        }

        public int getCAPACITY() {
            return CAPACITY;
        }

        public void setStorage(int storage) {
            this.storage.set(storage);
        }

        public int getBerthNum() {
                    return berthNum.get();
                }

        public void setBerthNum(int berthNum) {
            this.berthNum.set(berthNum);
        }
}
