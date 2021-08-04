// В кругу стоят N человек, пронумерованных от 1 до N. При ведении счета по кругу вычеркивается каждый второй человек,
// пока не останется один. Составить две программы, моделирующие процесс. Одна из программ должна использовать класс
// ArrayList, а вторая - LinkedList. Какая из двух программ работает быстрее? Почему?
package by.bsu.practice.collections;

import by.bsu.practice.collections.entity.Person;
import by.bsu.practice.collections.exception.IncorrectValuesException;
import by.bsu.practice.collections.service.CircleCount;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/*
Для значений <+-2500 элементов в списке linkedlist будет работать быстрее,
тк вставка и удаление в середину LinkedList устроены гораздо проще,
чем в ArrayList. Мы просто переопределяем ссылки соседних элементов, а ненужный элемент “выпадает” из цепочки ссылок.
Но для большего количества элементов получается наоборот, поскольку доступ к элементу осуществляется в ArrayList за фиксированное время.
Когда указывается:
list.add(2_000_000, new Integer(Integer.MAX_VALUE));
то в случае с ArrayList [2_000_000] это конкретный адрес в памяти, ведь у него внутри массив.
В то время как у LinkedList массива нет. Он будет искать элемент номер 2_000_000 по цепочке ссылок. Для него это не адрес в памяти,
а ссылка, до которой еще надо дойти:
fistElement.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next………
В итоге при каждой вставке (удалении) в середине списка ArrayList уже знает точный адрес в памяти,
к которому он должен обратиться, а вот LinkedList’у еще надо до нужного места “дотопать”.
 */
public class Main {
    public static void main(String... args){
        final int N = 2500;
        try {
            //проводим операцию с ArrayList
            List<Person> listArray = new ArrayList<>();
            CircleCount.fillList(listArray,N);
            Date currentTime1 = new Date();
            Person result = CircleCount.findLastPerson(listArray);
            Date newTime1 = new Date();
            //подсчитываем время за которое она прошла
            long msDelayArray = newTime1.getTime() - currentTime1.getTime();

            //проводим операцию с LinkedList
            List<Person> listLinked = new LinkedList<>();
            CircleCount.fillList(listLinked,N);
            Date currentTime2 = new Date();
            Person result2 = CircleCount.findLastPerson(listLinked);
            Date newTime2 = new Date();
            long msDelayLinked = newTime2.getTime() - currentTime2.getTime();

            System.out.println("In circle there are " + N + " people");
            System.out.println("Last person is: " + result);
            System.out.println("Time of Arraylist operation: " + msDelayArray);
            System.out.println("Last person is: " + result2);
            System.out.println("Time of Linkedlist operation: " + msDelayLinked);

        } catch (IncorrectValuesException e) {
            e.printStackTrace();
        }

    }
}
