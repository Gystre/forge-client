package features.commands;

import console.ConsoleIO;
import mod.BaseMod;
import mod.Category;

public abstract class CommandMod extends BaseMod {
    private final String[] syntax;

    public CommandMod(String name, String desc, String... syntax) {
        super(Category.SERVICE, name, desc);
        this.syntax = syntax;
    }

    public abstract class CmdException extends Exception
    {
        public CmdException()
        {
            super();
        }

        public CmdException(String message)
        {
            super(message);
        }

        public abstract void printToChat();
    }

    public final class CmdError extends CmdException
    {
        public CmdError(String message)
        {
            super(message);
        }

        @Override
        public void printToChat()
        {
            ConsoleIO.error(getMessage());
        }
    }

    public final class CmdSyntaxError extends CmdException
    {
        public CmdSyntaxError()
        {
            super();
        }

        public CmdSyntaxError(String message)
        {
            super(message);
        }

        @Override
        public void printToChat()
        {
            if(getMessage() != null)
                ConsoleIO.error("\u00a74Syntax error:\u00a7r " + getMessage());

            for(String line : syntax)
                ConsoleIO.error(line);
        }
    }

    //all commands start not enabled so when user accesses them they become active
    @Override
    public boolean isEnabled(){
        return false;
    }

    @Override
    public void enable() {
        try {
            call();
        } catch (CmdException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disable(){ }

    public abstract void call() throws CmdException;

    public String[] getSyntax() {
        return syntax;
    }
}
