package ru.zalupa.command;

import dev.xdark.clientapi.event.chat.ChatSend;
import dev.xdark.clientapi.text.Text;
import dev.xdark.clientapi.util.Collections;
import lombok.Getter;
import ru.zalupa.Zalupa;

import java.util.Arrays;
import java.util.Map;

@Getter
public class CommandManager {

    private final Map<String, Command> registeredCommands = Collections.concurrentMap();

    public CommandManager() {
        ChatSend.BUS.register(this::handle);
    }

    private void handle(ChatSend event) {
        if (!event.getMessage().startsWith(".")) return;
        event.setCancelled(true);
        String name = event.getMessage().split(" ")[0].toLowerCase().substring(1);
        for (Command cmd : registeredCommands.values()) {
            if (cmd.getName().equalsIgnoreCase(name) || cmd.getAliases().contains(name)) {
                cmd.handle(event.getMessage().substring(name.length() + 1 + (event.getMessage().contains(" ") ? 1 : 0)).split(" "));
                return;
            }
        }
        Zalupa.getApi().chat().printChatMessage(Text.of("§8[§cZalupa§8] §fCommand not found, type §c.help"));
    }

    public void registerCommands(Command... commands) {
        Arrays.stream(commands).forEach(this::registerCommand);
    }

    public void registerCommand(Command command) {
        registeredCommands.put(command.getName().toLowerCase(), command);
    }

}
