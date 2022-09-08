package homework;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.*;

public class ComplexExamples {

    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }


    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };
        /*  Raw data:

        0 - Harry
        0 - Harry
        1 - Harry
        2 - Harry
        3 - Emily
        4 - Jack
        4 - Jack
        5 - Amelia
        5 - Amelia
        6 - Amelia
        7 - Amelia
        8 - Amelia

        **************************************************

        Duplicate filtered, grouped by name, sorted by name and id:

        Amelia:
        1 - Amelia (5)
        2 - Amelia (6)
        3 - Amelia (7)
        4 - Amelia (8)
        Emily:
        1 - Emily (3)
        Harry:
        1 - Harry (0)
        2 - Harry (1)
        3 - Harry (2)
        Jack:
        1 - Jack (4)
     */

    public static void main(String[] args) {
        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();

        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться
                Key:Amelia
                Value:4
                Key: Emily
                Value:1
                Key: Harry
                Value:3
                Key: Jack
                Value:1
         */

        Map<String, Long> collect = Arrays.stream(RAW_DATA)
                .distinct()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(Person::getId))
                .collect(groupingBy(Person::getName,
                        mapping(Person::getId, counting())));
        collect.forEach((key, value) -> System.out.println("Key:" + key + "\n" + "Value:" + value));

        /*
        Task2

            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
         */
        System.out.println();
        System.out.println("**************************************************");
        System.out.println("3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10");
        System.out.println();

        assert Arrays.equals(task2(new int[]{2, 3, 4, 6, 8}, 10), new int[]{2, 8});
        assert Arrays.equals(task2(new int[]{0, 10, 3, 5}, 10), new int[]{0, 10});
        assert Arrays.equals(task2(new int[]{0, 1, 3, 5, 4}, 10), new int[]{0, 0});
        assert Arrays.equals(task2(null, 10), new int[]{0, 0});
        assert Arrays.equals(task2(new int[]{0}, 10), new int[]{0, 0});

        int[] a = {3, 4, 2, 7};
        System.out.println(Arrays.toString(task2(a, 10)));

       /* Task3
            Реализовать функцию нечеткого поиска
                    fuzzySearch("car", "ca6$$#_rtwheel"); // true
                    fuzzySearch("cwhl", "cartwheel"); // true
                    fuzzySearch("cwhee", "cartwheel"); // true
                    fuzzySearch("cartwheel", "cartwheel"); // true
                    fuzzySearch("cwheeel", "cartwheel"); // false
                    fuzzySearch("lw", "cartwheel"); // false
       */
        System.out.println();
        System.out.println("**************************************************");
        System.out.println("Реализовать функцию нечеткого поиска");
        System.out.println();

        assert fuzzySearch("abc", "ajnbfgc");
        assert !fuzzySearch("bac", "ajnbfgc");
        assert fuzzySearch("", "ajnbfgc");
        assert !fuzzySearch(" ", "ajnbfgc");
        assert !fuzzySearch(null, "ajnbfgc");

        System.out.println(fuzzySearch("car", "ca6$$#_rtwheel")); // true
        System.out.println(fuzzySearch("cwhl", "cartwheel")); // true
        System.out.println(fuzzySearch("cwhee", "cartwheel")); // true
        System.out.println(fuzzySearch("cartwheel", "cartwheel")); // true
        System.out.println(fuzzySearch("cwheeel", "cartwheel")); // false
        System.out.println(fuzzySearch("lw", "cartwheel")); // false

    }

    public static int[] task2(int[] array, int num) {
        int[] result = new int[2];
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                int j = i;
                while (j != array.length - 1) {
                    if (array[i] + array[j + 1] == num) {
                        result[0] = array[i];
                        result[1] = array[j + 1];
                        return result;
                    }
                    j++;
                }
            }
        }
        return result;
    }

    public static boolean fuzzySearch(String looking, String lookingHere) {
        if (looking == null || lookingHere == null) {
            return false;
        }
        int count = 0;
        int resultLength = 0;
        for (int i = 0; i < looking.length(); i++) {
            for (int j = count; j < lookingHere.length(); j++) {
                if (looking.charAt(i) == lookingHere.charAt(j)) {
                    count = j + 1;
                    resultLength++;
                    break;
                }
            }
        }
        return resultLength == looking.length();
    }
}
