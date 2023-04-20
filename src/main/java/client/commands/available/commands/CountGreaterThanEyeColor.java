package client.commands.available.commands;

import client.commands.Command;
import common.data.Color;
import server.PersonCollection;

/**
 * count_greater_than_eye_color eyeColor
 */
public class CountGreaterThanEyeColor extends Command {
    private final PersonCollection personCollection;

    public CountGreaterThanEyeColor(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            countGreater(args[1]);
        }
    }

    /**
     * display the number of elements whose eyeColor field value is greater than the set value
     *
     * @param eyeColor_s
     */
    public void countGreater(String eyeColor_s) {
        try {
            String eyeColor = eyeColor_s.trim();
            int code = Integer.parseInt(eyeColor);
            boolean flag = false;

            for (Color ourColor : Color.values()) {
                if (ourColor.getCode() == code) {
                    personCollection.countEyeColor(code);
                    flag = true;
                }
            }
            if (!flag) {
                System.out.println("Такого цвета нет в списке");
            }
        } catch (NumberFormatException e) {
            System.out.println("Вы неправильно ввели аргумент команды");
        }
    }

    @Override
    public String getName() {
        return "count_greater_than_eye_color";
    }

    @Override
    public String getDescription() {
        return "count_greater_than_eye_color: вывести количество элементов, значение поля eyeColor которых больше заданного";
    }
}

