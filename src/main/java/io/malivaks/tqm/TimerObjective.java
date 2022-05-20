package io.malivaks.tqm;

import me.blackvein.quests.CustomObjective;
import me.blackvein.quests.Quest;
import me.blackvein.quests.Quests;
import me.blackvein.quests.events.quester.QuesterPostStartQuestEvent;
import me.blackvein.quests.quests.IQuest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TimerObjective extends CustomObjective implements Listener {
    Quests qp = (Quests) Bukkit.getServer().getPluginManager().getPlugin("Quests");

    public TimerObjective() {
        this.setName("Timer Objective");
        this.setAuthor("Malivaks");
        this.setItem("CLOCK", (short) 0);
        this.setShowCount(true);
        this.setCountPrompt("Enter the amount of time (in seconds) the player needs to wait:");
        this.setDisplay("Wait %count% seconds");
    }

    @EventHandler
    public void questStarted(final QuesterPostStartQuestEvent event) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(qp, () -> {
            Player player = event.getQuester().getPlayer();
            IQuest quest = event.getQuest();

            incrementObjective(player, this, 1, (Quest) quest);
        }, 0, 20);
    }
}
