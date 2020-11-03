import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Main extends ListenerAdapter {
    //Global variables
    private boolean menuOneFlag = false;
    private String currentUser = null;
    private int numberOfSelections = 0;

    public static void main(String[] args) throws LoginException {
        //Logs into the bot using it's token
        JDA jda = JDABuilder.createDefault("NzY4MTk0MDY0OTYwNDU0NjY3.X486eQ.epp1aUBMMMz1gzlGeFbuLdhjeAY").setActivity(Activity.playing("Making Bugs")).addEventListeners(new Main()).build();
    }

    //Listens for messages in any channel the bot can see
    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        //Generates stats for a character between 75 and 85
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
                message.append("Generated Stat Array (4d6 drop lowest):\n");
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

        //Starts rolling gui
        else if (event.getMessage().getContentRaw().equals(">start")) {
            //Instantiating Variables
            StringBuilder messageBuilder = null;

            //Clearing the string builder
            messageBuilder = new StringBuilder();

            //Building starting message
            messageBuilder.append("**Select the dice you would like to roll:**").append("\n").append(":one: : d4").append("\n").append(":two: : d6").append("\n").append(":three: : d8").append("\n").append(":four: : d10").append("\n").append(":five: : d12").append("\n").append(":six: : d20").append("\n").append(":seven: : d100").append("\n\n").append(":white_check_mark: : Roll");

            //Sending the message
            event.getChannel().sendMessage(messageBuilder).queue(message -> {
                message.addReaction("\u0031\uFE0F\u20E3").queue();
                message.addReaction("\u0032\uFE0F\u20E3").queue();
                message.addReaction("\u0033\uFE0F\u20E3").queue();
                message.addReaction("\u0034\uFE0F\u20E3").queue();
                message.addReaction("\u0035\uFE0F\u20E3").queue();
                message.addReaction("\u0036\uFE0F\u20E3").queue();
                message.addReaction("\u0037\uFE0F\u20E3").queue();
                message.addReaction("\u2705").queue();
            });
        }
    }

    //Checks for reactions added
    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {

        //Checks for the white check mark reaction
        if (event.getReactionEmote().getAsCodepoints().equals("U+2705")) {
            //Checks to see if the user reacting is the bot
            if (!Objects.requireNonNull(event.getMember()).getUser().equals(event.getJDA().getSelfUser())){
                //Check to see that the message reacted to is the bots
                if (event.getChannel().retrieveMessageById(event.getMessageId()).complete().getAuthor().equals(event.getJDA().getSelfUser())){
                    //Case for the first menu
                    if (!menuOneFlag){
                        menuOneFlag = true;
                        currentUser = event.getUserId();
                        System.out.println(event.getReaction().getCount());
//                        numberOfSelections = event.getReaction().getCount();
                    }
                }
            }
        }
    }

    //Rolls 4d6 and returns the largest 3 numbers and the total in an array
    private int[] rollStats() {
        //Instantiating an array to hold the rolls and total
        int[] rolls = new int[5];

        //Generates 4 rolls
        for (int i = 0; i < 4; i++) {
            rolls[i] = rollDie(6);
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

    //Rolls a dice of specified sides
    private int rollDie(int sides) {
        //Variable declaration
       int roll;

       //Rolling the die
        roll = ThreadLocalRandom.current().nextInt(1, sides);

        //Returning the rolled number
        return roll;
    }
}