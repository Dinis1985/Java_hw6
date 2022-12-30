
/** Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
 Создать множество ноутбуков.
 Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации
 и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map. Например:
 “Введите цифру, соответствующую необходимому критерию:
 1 - ОЗУ
 2 - Объем ЖД
 3 - Операционная система
 4 - Цвет …
 Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
 Отфильтровать ноутбуки из первоначального множества и вывести проходящие по условиям.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Progr {

    public static void main(String[] params) {
        Set<Nout> nouts = initSet();
        Scanner iScanner = new Scanner(System.in);

        List<String> criteriesInteger = Arrays.asList("1", "2", "3");
        List<String> criteriesString = Arrays.asList("4", "5");
        Map<String, String> filter = new HashMap<String, String>();

        printNouts(nouts, filter);
        while (true) {
            System.out.println("Введите цифру, соответствующую необходимому критерию:");
            System.out.println("1 - Минимальный объём ОЗУ (Гб)");
            System.out.println("2 - Минимальный объём ЖД (Гб)");
            System.out.println("3 - Диагональ экрана");
            System.out.println("4 - Цвет");
            System.out.println("5 - Операционная система");
            System.out.println("0 - Сброс");
            System.out.println("-1 - Выход");
            System.out.print("Номер критерия--> ");

            String key = iScanner.nextLine();
            if (key != null)
                key = key.trim();
            else
                continue;
            if (key.equalsIgnoreCase("-1"))
                break;
            if (key.equalsIgnoreCase("0")) {
                filter.clear();
                printNouts(nouts, filter);
            }
            if (criteriesInteger.contains(key) || criteriesString.contains(key)) {
                System.out.print("Значение критерия--> ");
                String value = iScanner.nextLine();
                if (value != null)
                    value = value.trim();
                else
                    continue;

                if (value.equals("")) {
                    filter.put(key, value);
                } else {
                    if (criteriesString.contains(key)) {
                        filter.put(key, value);
                    } else {
                        try {
                            Integer i = Integer.parseInt(value);
                            filter.put(key, value);
                        } catch (NumberFormatException e) {
                            System.out.println();
                            System.out
                                    .println("Для числового критерия (" + key + ") введено нечисловое значение!!!");
                            continue;
                        }
                    }
                }
                printNouts(nouts, filter);
            } else {
                System.out.println();
                System.out.println(String.format("!Критерий %s отсутствует", key));
            }
        }
    }

    private static void printNouts(Set<Nout> nouts, Map<String, String> filter) {
        List<String> forPrint = new ArrayList<String>();
        for (Nout n : nouts) {
            if (filter(n, filter)) {
                String s = String.format("S/N:%12s: ОЗУ(Гб):%d, диск(Гб):%d, экран(дюйм):%d, ОС:%s, цвет:%s",
                        n.getSerialNumber(),
                        n.getRamSizeGb(),
                        n.getDiskSizeGb(),
                        n.getScreenSizeInch(),
                        n.getOs(),
                        n.getColor());
                forPrint.add(s);
            }
        }

        System.out.println();
        System.out.println(String.format("***Ноутбуки. Результат (%d из %d)", forPrint.size(), nouts.size()));
        for (String s : forPrint) {
            System.out.println(s);
        }
    }

    private static boolean filter(Nout n, Map<String, String> filter) {
        boolean result = true;

        for (String key : filter.keySet()) {
            String value = filter.get(key);
            if (value == null || value.trim().equals(""))
                continue;
            //
            if (key.equals("1")) {
                try {
                    int i = Integer.parseInt(value);
                    if (n.getRamSizeGb() >= i) {

                    } else {
                        result = false;
                        break;
                    }
                } catch (NumberFormatException e) {
                    result = false;
                    break;
                }
            } else if (key.equals("2")) {
                try {
                    int i = Integer.parseInt(value);
                    if (n.getDiskSizeGb() >= i) {

                    } else {
                        result = false;
                        break;
                    }
                } catch (NumberFormatException e) {
                    result = false;
                    break;
                }
            } else if (key.equals("3")) {
                try {
                    int i = Integer.parseInt(value);
                    if (n.getScreenSizeInch() >= i) {

                    } else {
                        result = false;
                        break;
                    }
                } catch (NumberFormatException e) {
                    result = false;
                    break;
                }
            } else if (key.equals("4")) {
                if (n.getColor().equalsIgnoreCase(value)) {

                } else {
                    result = false;
                    break;
                }
            } else if (key.equals("5")) {
                if (n.getOs().toUpperCase().contains(value.toUpperCase())) {

                } else {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    public static Set<Nout> initSet() {
        String OS_WINDOWS_10 = "Windows 10";
        String OS_WINDOWS_11 = "Windows 11";
        String OS_LINUX_UBUNTU = "Linux Ubuntu 22.04 LTS";

        Set<Nout> set = new HashSet<Nout>();
        //
        set.add(new Nout(
                "HP32745",
                15,
                4,
                1024,
                OS_WINDOWS_10,
                "Белый"));
        set.add(new Nout(
                "PH52749",
                13,
                8,
                512,
                OS_WINDOWS_10,
                "Черный"));
        set.add(new Nout(
                "GP83940",
                17,
                16,
                256,
                OS_LINUX_UBUNTU,
                "Красный"));
        set.add(new Nout(
                "OD60601",
                18,
                8,
                512,
                OS_WINDOWS_10,
                "Синий"));
        set.add(new Nout(
                "ZVO00001",
                22,
                32,
                2048,
                OS_WINDOWS_11,
                "Серебро"));
        set.add(new Nout(
                "JV85756",
                14,
                2,
                512,
                OS_WINDOWS_11,
                "Серый"));
        return set;
    }
}