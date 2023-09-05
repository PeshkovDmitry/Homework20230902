import Exceptions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean incorrectData = true;
        String dataString = null;
        String[] data = new String[6];
        while (incorrectData) {
            System.out.println("Введите строку данных:");
            dataString = scanner.nextLine();
            try {
                data = parse(dataString);
                incorrectData = false;
            } catch (FieldCountDataException e) {
                System.out.println(e.getMessage());
                System.out.printf("Введено %d полей, а должно быть 6\n", e.getFieldCount());
            } catch (SurnameDataException e) {
                System.out.println(e.getMessage());
                System.out.printf("Вы ввели: %s\n", e.getText());
                System.out.println("Фамилия должна быть из русских символов и начинаться с большой буквы");
            } catch (NameDataException e) {
                System.out.println(e.getMessage());
                System.out.printf("Вы ввели: %s\n", e.getText());
                System.out.println("Имя должно быть из русских символов и начинаться с большой буквы");
            } catch (SecondNameDataException e) {
                System.out.println(e.getMessage());
                System.out.printf("Вы ввели: %s\n", e.getText());
                System.out.println("Отчество должно быть из русских символов и начинаться с большой буквы");
            } catch (BirthdayDataException e) {
                System.out.println(e.getMessage());
                System.out.printf("Вы ввели: %s\n", e.getText());
                System.out.println("Дата рождения должна быть в формате dd.mm.yyyy");
            } catch (PhoneNumberDataException e) {
                System.out.println(e.getMessage());
                System.out.printf("Вы ввели: %s\n", e.getText());
                System.out.println("Номер телефона должен быть целым беззнаковым числом без форматирования");
            } catch (GenderDataException e) {
                System.out.println(e.getMessage());
                System.out.printf("Вы ввели: %s\n", e.getText());
                System.out.println("Пол должен быть m или f");
            }
        }
        scanner.close();
        try (FileWriter fileWriter = new FileWriter(data[0] + ".txt", true)) {
            fileWriter.write("<" + String.join("><", data) + ">\n");
        } catch (IOException e) {
            System.out.println("Не удалось записать данные в файл");
            e.printStackTrace();
        }
    }

    private static String[] parse(String dataString) throws FieldCountDataException, SurnameDataException, NameDataException, SecondNameDataException, BirthdayDataException, PhoneNumberDataException, GenderDataException {
        String[] data = dataString.split(" ");
        if (data.length != 6)
            throw new FieldCountDataException("Введено некорректное число полей", dataString, data.length);
        if (!data[0].matches("[А-Я][а-я]+"))
            throw new SurnameDataException("Введенная фамилия неверна", data[0]);
        if (!data[1].matches("[А-Я][а-я]+"))
            throw new NameDataException("Введенное имя неверно", data[1]);
        if (!data[2].matches("[А-Я][а-я]+"))
            throw new SecondNameDataException("Введенное отчество неверно", data[2]);
        if (!data[3].matches("[0-3][0-9]\\.[0-1][0-9]\\.[1-2][0-9][0-9][0-9]"))
            throw new BirthdayDataException("Введенная дата рождения неверна", data[3]);
        if (!data[4].matches("[0-9]+"))
            throw new PhoneNumberDataException("Введенный номер телефона неверен", data[4]);
        if (!data[5].matches("m|f"))
            throw new GenderDataException("Введенный пол неверен", data[5]);
        return data;
    }
}