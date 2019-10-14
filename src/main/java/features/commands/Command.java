package features.commands;

import toolbox.Helper;

public abstract class Command extends ForgeRegistryEntry<Command>{
    private final String name;
    private final String description;
    private final String[] syntax;

    public Command(String name, String description, String... syntax)
    {
        this.name = name;
        this.description = description;
        this.syntax = syntax;
    }

    public abstract void call(String[] args) throws CmdException;

    public final String getName()
    {
        return name;
    }

    public final String getDescription()
    {
        return description;
    }

    public final String[] getSyntax()
    {
        return syntax;
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
            Helper.printMessageNaked(getMessage());
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
                Helper.printMessageNaked("\u00a74Syntax error:\u00a7r " + getMessage());

            for(String line : syntax)
                Helper.printMessageNaked(line);
        }
    }
}
