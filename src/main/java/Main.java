import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, LoginException, IllegalArgumentException, RateLimitedException {
        //Variable Declaration
        List<String> list;
        String token;
        String ownerId;
        EventWaiter waiter;
        CommandClientBuilder client;

        //Loads a text file into our List containing the bot's information
        list = Files.readAllLines(Paths.get("config.txt"));
        //Saves the first line of the text file containing the bot's token
        token = list.get(0);
        //Saves the second line of the text file containing the bot's owner id
        ownerId = list.get(1);

        //Defines the event waiter
        waiter = new EventWaiter();

        //Defines a command client
        client = new CommandClientBuilder();
        //The default is "Type [prefix]help"
        client.useDefaultGame();
        //Sets the owner of the bot
        client.setOwnerId(ownerId);
        //Sets emojis used throughout the bot on successes, warnings, and failures
        client.setEmojis("\uD83D\uDE03", "\uD83D\uDE2E", "\uD83D\uDE26");
        //Sets the bot prefix
        client.setPrefix(">");

        //Adds commands
        client.addCommands();

        //Start getting a bot account set up
        new JDABuilder(AccountType.BOT)
                //Set the token
                .setToken(token)
                //Sets the game for when the bot is loading
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setActivity(Activity.playing("loading..."))
                //Adds the listeners
                .addEventListeners(waiter, client.build())
                //Start the bot
                .build();
    }
}