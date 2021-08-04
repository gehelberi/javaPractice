package by.bsu.practice.collections.entity;

//класс Person с полями позиции человека в кругу
public class Person{
    private int position;

    public Person(){

    }
    public Person(int startPosition) {
        this.position = startPosition;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Person{" +
                "position=" + position +
                '}';
    }
}
