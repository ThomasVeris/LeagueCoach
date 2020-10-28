package be.vdab;

import be.vdab.commands.Command;

public class Commander {
    public enum Commands {;
        //TODO: choose commands and implement them
        //GET_KDA("Get KDA of this player", new Command()),


        private final String commandDescription;
        private final Command command;

        Commands(String commandDescription, Command command) {
            this.commandDescription = commandDescription;
            this.command = command;
        }

        public String getCommandDescription() {
            return commandDescription;
        }

        public Command getCommand() {
            return command;
        }
    }
}
