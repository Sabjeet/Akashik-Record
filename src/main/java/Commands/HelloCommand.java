package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.util.concurrent.TimeUnit;

//Asks the user for their name and responds
public class HelloCommand extends Command {
    //Defining the event waiter
    private final EventWaiter waiter;
    //Constructor
    public HelloCommand(EventWaiter waiter)
    {
        //Adds rhe waiter
        this.waiter = waiter;
        //Sets the command name
        this.name = "hello";
        //Sets alternate command names
        this.aliases = new String[]{"hi"};
        //Sets the help command text
        this.help = "says hello and waits for a response";
    }

    //Runs when the command is executed
    @Override
    protected void execute(CommandEvent event) {
        //Asks the user for their name
        event.reply("Hello. What is your name?");

        //Starts te waiter to wait for the user's response
        waiter.waitForEvent(MessageReceivedEvent.class,
                //Checks to see that the message sender is the same person, channel, and a different message
                event2 -> event2.getAuthor().equals(event.getAuthor())
                        && event2.getChannel().equals(event.getChannel())
                        && !event2.getMessage().equals(event.getMessage()),
                //Responds with the users name
                event2 -> event.reply("Hello, `"+ event2.getMessage().getContentRaw() + "`! I'm `" + event2.getJDA().getSelfUser().getName() + "`!"),
                //Sets the time the bot will wait for a response to 1 minute
                1, TimeUnit.MINUTES, () -> event.reply("Sorry, you took too long."));
    }
}
