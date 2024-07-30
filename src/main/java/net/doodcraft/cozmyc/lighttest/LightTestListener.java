package net.doodcraft.cozmyc.lighttest;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.ability.CoreAbility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class LightTestListener implements Listener {

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);

        if (bPlayer == null || !bPlayer.canBendIgnoreCooldowns(CoreAbility.getAbility(LightTest.class))) {
            return;
        }

        if (player.isSneaking() && bPlayer.getBoundAbilityName().equalsIgnoreCase("LightTest")) {
            if (!CoreAbility.hasAbility(player, LightTest.class)) {
                new LightTest(player);
            }
        }
    }
}
