package client.commands.available.commands;

import client.commands.Command;
import server.PersonCollection;

/**
 * filter_greater_than_location location :
 * display elements whose location field value is greater than the given one
 */
public class FilterGreaterThanLocation extends Command {
    private final PersonCollection personCollection;

    public FilterGreaterThanLocation(PersonCollection personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Вы неправильно ввели команду");
        } else {
            personCollection.filterGreater(args[1]);
        }
    }
    @Override
    public String getName() {
        return "filter_greater_than_location";
    }

    @Override
    public String getDescription() {
        return "вывести элементы, значение поля location которых больше заданного";
    }
}
