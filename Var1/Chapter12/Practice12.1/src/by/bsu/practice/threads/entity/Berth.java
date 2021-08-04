package by.bsu.practice.threads.entity;

import java.util.concurrent.atomic.AtomicBoolean;

//класс причал, с единственной переменной, показывающей занят ли он
public class Berth {

    private AtomicBoolean busy = new AtomicBoolean();

    public Berth() {
        this.busy.set(false);
    }

    public AtomicBoolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy){
        this.busy.set(busy);
    }
}
