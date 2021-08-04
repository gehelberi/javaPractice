package by.bsu.practice.collections.service;

import by.bsu.practice.collections.entity.Person;
import by.bsu.practice.collections.exception.IncorrectValuesException;

import java.util.List;

public class CircleCount {

        public static Person findLastPerson(List<Person> list) throws IncorrectValuesException {
            //если передан пустой список, выбрасываем исключение
            if(list.size() == 0)
                throw new IncorrectValuesException("List is empty!");


            if(list.size() > 1) {

                Person now;
                Person next = list.get(1);

                //удаляем из списка каждого второго пользователя пока в списке не останется 1 человек
                while (list.size() > 1) {
                    now = next;
                    next = nextPerson(list, now);
                    list.remove(now);
                }
            }
            //результатом будет единственный оставшийся человек
            return list.get(0);
        }

        //метод, возвращающий каждого второго незакчеркнутого человека
        private static Person nextPerson(List<Person> list, Person now){
            return list.get(next(list.size(),list.indexOf(now)));
        }

        //ищет индекс элемента в списке(как в кругу) через одного
        private static int next(int size, int num){
            if(num == size - 1)
                return 1;
            if(num == size - 2)
                return 0;
            return num + 2;
        }

    //заполняет список людьми с номерами от 1 до N
    public static void fillList(List<Person> list, int N){
        for(int i = 1;i <= N;i++){
            list.add(new Person(i));
        }
    }


}
