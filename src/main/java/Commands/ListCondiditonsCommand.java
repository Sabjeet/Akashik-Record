package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.EmbedBuilder;

//Posts a list of all the conditions in dnd 5e
public class ListCondiditonsCommand extends Command {
    //Defining the event waiter
    private final EventWaiter waiter;
    //Defining the embed builder
    private EmbedBuilder embedBuilder = new EmbedBuilder();

    //Constructor
    public ListCondiditonsCommand(EventWaiter waiter) {
        //Adds the waiter
        this.waiter = waiter;
        //Sets the command name
        this.name = "Conditions";
        //Sets alternate command names
        this.aliases = new String[]{""};
        //Sets the help command text
        this.help = "Posts a list of all the conditions in dnd 5e";

        //Building the embed post
        //Setting the title of the post
        embedBuilder.setTitle("Conditions");
        //Adding fields for each condition
        embedBuilder.addField("Blinded",
                "A blinded creature cannot see and fails any ability check that requires sight." +
                        "\nAttack rolls against the creature have Advantage." +
                        "\nThe blinded creature's attack rolls have Disadvantage.",
                false);
        embedBuilder.addField("Charmed",
                "A charmed creature cannot attack or target the charmer with harmful abilities or magical effects\n" +
                "The charmer has Advantage on any ability check to interact socially with the creature.",
                false);
        embedBuilder.addField("Deafened",
                "A deafened creature can't hear and fails any ability check that requires hearing.",
                false);
        embedBuilder.addField("Frightened",
                "A frightened creature has Disadvantage on ability checks and attack rolls while the source of the fear effect is within line of sight.\n" +
                        "The creature cannot willingly move closer to the source of the fear effect.",
                false);
        embedBuilder.addField("Grappled",
                "A grappled creature's Speed becomes 0, and cannot benefit from any bonus to Speed.\n" +
                        "The condition ends if the grappler becomes incapacitated.\n" +
                        "The condition also ends if an effect removes the grappled creature from the reach of the grappler or grappling effect.",
                false);
        embedBuilder.addField("Incapacitated",
                "An incapacitated creature can't take actions or reactions",
                false);
        embedBuilder.addField("Invisible",
                "An invisible creature cannot be seen except by magic or a special sense but can be detected if it makes noise.\n" +
                        "An invisible creature is considered heavily obscured for the purpose of hiding.\n" +
                        "Attacks rolls against an invisible creature have Disadvantage.\n" +
                        "An invisible creature has Advantage on attack rolls.",
                false);
        embedBuilder.addField("Paralyzed",
                "A paralyzed creature is incapacitated and can't move or speak.\n" +
                        "The creature automatically fails STR and DEX Saving Throws.\n" +
                        "Attacks rolls against the creature have Advantage.\n" +
                        "Any attack that hits the creature is a critical hit if the attacker is within 5 feet.",
                false);
        embedBuilder.addField("Petrified",
                "A petrified creature is transformed along with any non-magical objects on its person into a solid inanimate substance. Its weight increases by a factor of 10 and it ceases aging.\n" +
                        "The creature is incapacitated and can't move or speak and is unaware of its surroundings.\n" +
                        "The creature automatically fails STR and DEX Saving Throws.\n" +
                        "Attacks rolls against the creature have Advantage.\n" +
                        "The creature has resistance to all damage.\n" +
                        "The creature is immune to poison and disease, but any poison in its system already is suspended not neutralized.",
                false);
        embedBuilder.addField("Poisoned",
                "A poisoned creature has Disadvantage on attack rolls and ability checks",
                false);
        embedBuilder.addField("Prone",
                "A prone creature's only movement is to crawl.\n" +
                        "The creature has Disadvantage on all attack rolls.\n" +
                        "An attack against the creature has Advantage if the attacker is within 5 feet. Otherwise the attacker has Disadvantage.\n" +
                        "Standing up from prone costs one half the creature's speed.",
                false);
        embedBuilder.addField("Restrained",
                "A restrained creature's speed is zero and it can't benefit from any bonuses to Speed.\n" +
                        "Attack rolls against the creature have Advantage.\n" +
                        "The creature's attack rolls have Disadvantage.",
                false);
        embedBuilder.addField("Stunned",
                "A stunned creature is incapacitated, can't move, and only speaks falteringly.\n" +
                        "The creature automatically fails STR and DEX saving throws.\n" +
                        "Attacks rolls against the creature have Advantage.",
                false);
        embedBuilder.addField("Unconscious",
                "An unconscious creature is incapacitated and can't move or speak and is unaware of its surroundings.\n" +
                        "The creature drops whatever it is holding and falls prone.\n" +
                        "The creature automatically fails STR and DEX saving throws.\n" +
                        "Attacks rolls against the creature have Advantage.\n" +
                        "Any attack that hits the creature is a critical hit if the attacker is within 5 feet.",
                false);
        embedBuilder.addField("Exhaustion",
                "Exhaustion is measured in six levels. An effect can give a creature one or more levels of Exhaustion. If an already exhausted creature suffers another effect that causes Exhaustion, its current level of Exhaustion increases by the amount specified in the effect, and the creature suffers the effects of its current level as well as all the lower levels.\n" +
                        "\n" +
                        "**Level 1:** Disadvantage on all Ability Checks\n" +
                        "**Level 2:** Speed Halved \n" +
                        "**Level 3:** Disadvantage on Saving Throws and Attack rolls\n" +
                        "**Level 4:** Hit point maximum halved\n" +
                        "**Level 5:** Speed reduced to 0\n" +
                        "**Level 6:** Death\n" +
                        "\n" +
                        "An effect that removes Exhaustion reduces its level as specified, and all effects of Exhaustion end if a creature's exhaustion level is reduced below 1.\n" +
                        "Finishing a Long Rest reduces a creature's exhaustion level by 1, so long as that creature has ingested some food and water.",
                false);
    }

    //Runs when the command is executed
    @Override
    protected void execute(CommandEvent event) {
        //Creates a new thread for saying hello
        new Thread(() -> {
            //Posts the embed
            event.reply(embedBuilder.build());
        }).start();
    }
}