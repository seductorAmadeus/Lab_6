package client.commands;

import client.RequestManager;
import client.commands.available.commands.*;
import server.PersonCollection;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * The class is responsible for checking for the correctness of commands and running them
 */
public class CommandManager {
    private PersonCollection personCollection;
    private static boolean isWorking = true;
    private static HashMap<String, Command> commandMap = new HashMap<String,Command>();
    private static String filelink;

    /**
     * creates a commandMap with commands
     *
     * @param
     */
    public CommandManager(RequestManager requestManager) {
        commandMap = new HashMap<>();
        initializeCommand(new Add(requestManager));
        initializeCommand(new AddIfMax(requestManager));
        initializeCommand(new AddIfMin(requestManager));
        initializeCommand(new Show(requestManager));
        initializeCommand(new Clear(requestManager));
        initializeCommand(new Info(requestManager));
        initializeCommand(new Exit());
        initializeCommand(new Help(requestManager));
        initializeCommand(new CountGreaterThanEyeColor(requestManager));
        initializeCommand(new FilterGreaterThanLocation(requestManager));
        initializeCommand(new PrintUniqueLocation(requestManager));
        initializeCommand(new RemoveById(requestManager));
        initializeCommand(new RemoveGreater(requestManager));
        initializeCommand(new Update(requestManager));
        /**
        initializeCommand(new Save(requestManager,personCollection));
        initializeCommand(new ExecuteScript(requestManager,personCollection));*/
    }

    public PersonCollection getPersonCollection() {
        return this.personCollection;
    }

    /**
     * checks for the correctness of the command and starts
     */
    public static void existCommand(String input) {
        String[] args = input.trim().split(" ");
        try {
            //String command = sc.nextLine().trim();
            String command = args[0];

            String[] commandArg = args;
            String argument;

            if (args.length == 1)
                argument = null;
            else if (args.length == 2)
                argument = args[1];
            else {
                System.out.println("Проблема с аргументом, обратитесь к команде help");
                return;
            }

            if (commandMap.containsKey(command)) {
                commandMap.get(command).setArgument(argument);
                commandMap.get(command).execute(args);
            } else {
                System.out.println("Команды " + commandArg[0] + " не существует");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Команда введена неверно");
            isWorking = false;
            System.exit(0);
        } catch (JAXBException | IOException e) {
            System.out.println("Файл не найден");
        }

    }



    public static boolean getWork() {
        return isWorking;
    }

    public static HashMap<String, Command> getCommandMap() {
        return commandMap;
    }

    public String getFilelink() {
        return filelink;
    }

    public void setFilelink(String filelink) {
        this.filelink = filelink;
    }

    void initializeCommand(Command command){
        if(commandMap.containsKey(command.getName())){
            throw new IllegalArgumentException("Данная команда уже есть");
        }
        commandMap.put(command.getName(), command);
    }


}




