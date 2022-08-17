package ru.zalupa.command;

import dev.xdark.clientapi.text.Text;
import lombok.Getter;
import ru.zalupa.Zalupa;
import ru.zalupa.command.context.CommandContext;
import ru.zalupa.command.parameter.Parameter;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public abstract class Command {

    private final List<Command> subCommands = new LinkedList<>();
    private final List<Parameter<?>> parameters = new LinkedList<>();

    private final String name;
    private String prefix = "";
    private String description;
    private List<String> aliases;

    public Command(String name) {
        this(name, "Unknown usage");
    }

    public Command(String name, String description, String... aliases) {
        this.name = name;
        description(description).aliases(aliases);
    }

    public abstract void execute(CommandContext ctx);

    //Config methods

    public final Command aliases(List<String> aliases) {
        this.aliases = aliases == null ? Collections.emptyList() : aliases;
        return this;
    }

    public final Command aliases(String... aliases) {
        return aliases(Arrays.asList(aliases));
    }

    public final Command description(String description) {
        this.description = description == null ? "" : description;
        return this;
    }

    public final Command prefix(String prefix) {
        this.prefix = prefix == null ? "" : prefix;
        return this;
    }

    public final Command subCommand(Command subCommand) {
        subCommands.add(subCommand);
        return this;
    }

    public final Command parameter(Parameter<?> parameter) {
        if (!parameters.isEmpty() && !parameters.get(parameters.size() - 1).isRequired() && parameter.isRequired())
            throw new IllegalStateException("can`t add required parameter after non-required");
        parameters.add(parameter);
        return this;
    }

    //Minecraft handlers

    protected final void handle(String[] args) {
        if (args.length > 0) {
            String sub = args[0];
            for (Command command : subCommands) {
                if (command.getName().equalsIgnoreCase(sub)
                        || command.getAliases().stream().anyMatch(a -> a.equalsIgnoreCase(sub))) {
//                    args = (String[]) ArrayUtils.remove(args, 0);
                    //idk how?!??!?!?
                    String[] args0 = new String[args.length - 1];
                    for (int i = 0; i < args0.length; i++) {
                        args0[i] = args[i + 1];
                    }
                    command.handle(args0);
                    return;
                }
            }
        }

        List<Object> parsedParameters = new LinkedList<>();
        try {
            for (int i = 0; i < parameters.size(); i++) {
                Parameter<?> param = parameters.get(i);
                if (args.length - 1 < i) {
                    if (!param.isRequired())
                        continue;
                    throw new CommandParseException("Enter §c" + param.getName());
                }
                parsedParameters.add(param.getType().parse(args[i]));
            }
            execute(
                    CommandContext.builder(this)
                            .args(parsedParameters)
                            .originalArgs(Arrays.asList(args)).build()
            );
        } catch (CommandParseException e) {
            sendMessage(e.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

//    protected final void handleTab(CommandSender sender, String[] args, List<String> complete) {
//        if (!sender.getClass().isAssignableFrom(senderType))
//            return;
//
//        for (Require require : requires)
//            if (!require.check(sender))
//                return;
//
//        if (args.length > 0) {
//            String sub = args[0];
//            for (Command<?> command : subCommands) {
//                if (command.getName().equalsIgnoreCase(sub)
//                        || command.getAliases().stream().anyMatch(a -> a.equalsIgnoreCase(sub))) {
//                    args = (String[]) ArrayUtils.remove(args, 0);
//                    command.handleTab(sender, args, complete);
//                    return;
//                }
//            }
//            subCommands.stream()
//                    .filter(cmd -> cmd.getRequires().stream().allMatch(r -> r.check(sender)))
//                    .map(Command::getName).forEach(complete::add);
//            return;
//        }
//
//        for (int i = 0; i < parameters.size(); i++)
//            if (args.length - 1 < i && parameters.get(i).tab() != null)
//                complete.addAll(parameters.get(i).tab().getParameters(sender));
//    }

    //Util methods

    public String getUsage() {
        StringBuilder builder = new StringBuilder(" §f.")
                .append(name).append(" ");

        getParameters().stream()
                .map(p -> "§7" + (p.isRequired() ? "<" : "[") + "§a" + p.getName().toUpperCase() + "§7" + (p.isRequired() ? "> " : "] "))
                .forEach(builder::append);

        builder.append("§7- ").append(description);

        if (!subCommands.isEmpty()) {
            builder.append("\n §f.").append(name);
            builder.append(" §7[§a");
            builder.append(
                    subCommands.stream()
                            .map(Command::getName)
                            .collect(Collectors.joining("§7/§a"))
            );
            builder.append("§7]");
        }

        return builder.toString();
    }

    public Command sendMessage(String message, Object... params) {
        Zalupa.getApi().chat().printChatMessage(Text.of(String.format(prefix + message, params).replace("&", "§")));
        return this;
    }

    public static void send(String message, Object... params) {
        Zalupa.getApi().chat().printChatMessage(Text.of(String.format(message, params).replace("&", "§")));
    }
}
