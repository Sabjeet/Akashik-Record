package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

//Tells Lewis to fuck off
public class LewisQuestionCommand extends Command {
    //Defining the event waiter
    private final EventWaiter waiter;

    //Constructor
    public LewisQuestionCommand(EventWaiter waiter) {
        //Adds the waiter
        this.waiter = waiter;
        //Sets the command name
        this.name = "AavinIHaveAQuestion";
        //Sets alternate command names
        this.aliases = new String[]{" "};
        //Sets the help command text
        this.help = "Tells Lewis to fuck off";
    }

    //Runs when the command is executed
    @Override
    protected void execute(CommandEvent event) {
        //Creates a new thread for saying hello
        new Thread(() -> {
            //Tells Lewis to fuck off
            event.reply("Fuck off");
        }).start();
    }
}
