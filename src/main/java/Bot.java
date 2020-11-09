import Commands.HelloCommand;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.jagrosh.jdautilities.examples.command.PingCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Bot extends ListenerAdapter{
    public static void main(String[] args) throws IOException, LoginException, IllegalArgumentException, RateLimitedException, InterruptedException {
        //Loads "config.txt" containing the bots information
        List<String> list = Files.readAllLines(Paths.get("config.txt"));
        //The first line fo thr text file containing the bots token is saved
        String token = list.get(0);
        //The second line of the text file containing the bots owner id is saved
        String ownerId = list.get(1);

        //Defining an event waiter
        EventWaiter waiter = new EventWaiter();

        //Defining an command client
        CommandClientBuilder client = new CommandClientBuilder();
        //Sets the activity to the default ([prefix]help)
        client.useDefaultGame();
        //Sets the bots owner id
        client.setOwnerId(ownerId);
        //Sets emojis used throughout the bot on successes, warnings, and failures
        client.setEmojis("\uD83D\uDE03", "\uD83D\uDE2E", "\uD83D\uDE26");
        //Sets the bot prefix
        client.setPrefix(">");
        //Adding new Commanded
        client.addCommands(
                new PingCommand(),
                new HelloCommand(waiter)
        );

        //Setting up the bot account
        JDA jda = JDABuilder
                .createDefault(token)
                .addEventListeners(waiter, client.build())
                .build();
    }
}