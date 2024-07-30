package net.doodcraft.cozmyc.lighttest;

import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.FireAbility;
import com.projectkorra.projectkorra.util.LightManager;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LightTest extends FireAbility implements AddonAbility {

    private long cooldown;
    private long duration;
    private long startTime;

    public LightTest(Player player) {
        super(player);

        if (!bPlayer.canBend(this) || !bPlayer.canBendIgnoreCooldowns(this)) {
            return;
        }

        setFields();
        start();
    }

    private void setFields() {
        this.cooldown = 1000;
        this.duration = 60000;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void progress() {
        if (System.currentTimeMillis() - startTime > duration || !bPlayer.getBoundAbilityName().equalsIgnoreCase("LightTest")) {
            remove();
            return;
        }

        Location playerLocation = bPlayer.getPlayer().getLocation();

        int radius = 4;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Location blockLocation = playerLocation.clone().add(x, y, z);
                    LightManager.get().addLight(blockLocation, 15, 300L);
                }
            }
        }
    }

    @Override
    public long getCooldown() {
        return cooldown;
    }

    @Override
    public String getName() {
        return "LightTest";
    }

    @Override
    public Element getElement() {
        return Element.FIRE;
    }

    @Override
    public Location getLocation() {
        return player != null ? player.getLocation() : null;
    }

    @Override
    public boolean isHarmlessAbility() {
        return true;
    }

    @Override
    public boolean isIgniteAbility() {
        return false;
    }

    @Override
    public boolean isExplosiveAbility() {
        return false;
    }

    @Override
    public boolean isSneakAbility() {
        return true;
    }

    @Override
    public void load() {
        ProjectKorra.plugin.getServer().getPluginManager().registerEvents(new LightTestListener(), ProjectKorra.plugin);
    }

    @Override
    public void stop() {}

    @Override
    public String getAuthor() {
        return "Cozmyc";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }
}
