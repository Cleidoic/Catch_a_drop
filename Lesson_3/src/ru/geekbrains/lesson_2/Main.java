package ru.geekbrains.lesson_2;

import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner (System.in);

    public static void main(String[] args) {
        System.out.println("Ваша задача угадать число.");
        for (int i = 1000; i <= 3000; i += 1000) level(i);
        System.out.println("Победа!!");
        scanner.close();
    }

    private static void level(int range) {
        int number = (int) (Math.random()*range);
        while (true) {
            System.out.println("Угадайте число от 1 до " + range + ": ");
            int input_number = scanner.nextInt();
            if (number == input_number) {
                System.out.println("Вы угадали!");
                break;
            } else if (number < input_number) {
                System.out.println("Загаданное число меньше");
            } else {
                System.out.println("Загаданное число больше");
            }
        }
    }
}
