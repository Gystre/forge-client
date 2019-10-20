package features.commands;

import console.ConsoleIO;

public class Help extends CommandMod {
    public Help(){
        super("help", "shows commands", "Syntax: .help <command>");
    }

    //TODO: implement arguments in commands
    @Override
    public void call() throws CmdException{
//        if(args.length > 1)
//            throw new CmdSyntaxError();

        ConsoleIO.write("do: .commandname args");
    }
}
