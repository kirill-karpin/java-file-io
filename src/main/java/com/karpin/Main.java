package com.karpin;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*Компания «Игры для всех» переносит популярные карточные игры в мета-пространство.
На очереди любимец всех вечеринок — Алиас.
В этой игре количество участников не ограничено.
Участники должны объяснить членам своей команды слова, указанные на виртуальных карточках.
Осталось написать модуль для распределения слов по карточкам.
База всех слов для игры хранится в файле words.txt — каждое слово записано на новой строке.
Этот файл можно дополнить и обновить. На вход приложение должно принимать количество участников,
а на выходе генерировать файл-карточку для каждого участника со словами в случайном порядке.*/

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество участников: ");
        int playersNumber = scanner.nextInt();
        List<String> words = readWordsFromFile("words.txt");
        if (playersNumber > words.size()) {
            System.out.println("Недостаточно слов в файле. Добавьте слова и обновите файл.");
            return;
        }

        Collections.shuffle(words);


        int wordsNumber = words.size() / playersNumber;

        for (int i = 0; i < playersNumber; i++) {
            String filename = String.format("player%s.txt", i + 1);
            List<String> subList = words.subList(i * wordsNumber, (i + 1) * wordsNumber);

            writeListToFile(subList, filename);
        }

        System.out.println("Карточки готовы!");
    }

    private static List<String> readWordsFromFile(String filename) throws IOException {
        ArrayList<String> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            br.readLine();
            while (br.ready()) {
                String line = br.readLine();
                list.add(line);
            }
            return list;
        } catch (IOException e) {
            System.out.println("Произошла ошибка во время чтения файла.");
        }

        return list;
    }

    private static void writeListToFile(List<String> list, String filename) throws IOException {
        try ( FileWriter fileWriter = new FileWriter(filename))  {
            for (String s : list) {
                fileWriter.write(s + "\n");
            }
        } catch (IOException e) {
           System.out.println("Произошла ошибка во время записи файла.");
        }
    }
}