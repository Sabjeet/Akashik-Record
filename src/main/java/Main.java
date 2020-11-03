import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import java.util.Arrays;
import java.util.Random;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        //Logs into the bot using it's token
        JDA jda = JDABuilder.createDefault("NzY4MTk0MDY0OTYwNDU0NjY3.X486eQ.JjIAZp10S3xNCmL38TDGm_Sm_Yo").addEventListeners(new Main()).build();
    }

    //Listens for messages in any channel the bot can see
    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equals(">stats")) {
            //Instantiating Variables
            int[] rolls;
            boolean flag = false;
            StringBuilder message = null;

            while (!flag) {
                //Declaring loop variables
                int total = 0;

                //Clearing the string builder
                message = new StringBuilder();
                //Beginning the message array
                message.append("__Generated Stat Array (4d6 drop lowest):__\n");
                //Generates the random rolls
                for (int i = 0; i < 6; i++) {
                    rolls = rollStats();
                    message.append("**").append("Roll ").append(i + 1).append(":** (");
                    message.append(rolls[2]).append(", ").append(rolls[3]).append(", ").append(rolls[4]).append(", ~~").append(rolls[1]).append("~~) = `").append(rolls[0]).append("`").append("\n");
                    total += rolls[0];
                }
                //Records the total
                message.append("**Total** = ").append("`").append(total).append("`");

                //Checks if the total is between 75 and 85
                if (total >= 75 && total <= 85) flag = true;
            }

            //Prints the stat array
            event.getChannel().sendMessage(message).queue();
        }
    }

    //Rolls 4d6 and returns the largest 3 numbers and the total in an array
    private int[] rollStats() {
        //Instantiating an array to hold the rolls and total
        int[] rolls = new int[5];

        //Generates 4 rolls
        for (int i = 0; i < 4; i++) {
            Random rand = new Random();
            rolls[i] = rand.nextInt((6 - 1) + 1) + 1;
        }

        //Sorts the array from smallest to largest
        Arrays.sort(rolls);

        //Totals the largest 3 rolls into the 4th slot of the array
        for (int i = 2; i <= 4; i++) {
            rolls[0] += rolls[i];
        }

        //Returns the array of rolls
        return rolls;
    }
}
