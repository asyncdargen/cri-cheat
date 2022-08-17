package ru.zalupa.util;

import dev.xdark.clientapi.config.Config;
import dev.xdark.clientapi.config.ConfigFactory;
import dev.xdark.clientapi.entity.Entity;
import dev.xdark.clientapi.entity.EntityPlayer;
import dev.xdark.clientapi.util.Collections;
import lombok.Getter;
import ru.zalupa.Zalupa;

import java.util.Set;

@Getter
public class FriendManager {

    protected static Config config = ConfigFactory.create("friends", Zalupa.getZalupa());

    protected Set<String> friends;

    {
        friends = Collections.concurrentSet();
        try {
            for (String friend : config.getString("friends", "").split("; "))
                if (!friend.isEmpty())
                    friends.add(friend);
        } catch (Throwable e) {
            System.out.println("Error while load friends");
            e.printStackTrace();
        }
    }

    public void addFriend(String name) {
        friends.add(name.toLowerCase());
        save();
    }

    public void removeFriend(String name) {
        friends.remove(name.toLowerCase());
        save();
    }

    public void clearFriends() {
        friends.clear();
        save();
    }

    public boolean isFriend(String name) {
        return friends.contains(name.toLowerCase());
    }

    public boolean isFriend(Entity entity) {
        if (entity instanceof EntityPlayer)
            return isFriend(entity.getName());
        return false;
    }

    public void save() {
        config.set("friends", String.join("; ", friends));
        config.save();
    }

}
