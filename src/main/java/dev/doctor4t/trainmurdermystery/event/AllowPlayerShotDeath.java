package dev.doctor4t.trainmurdermystery.event;

import net.fabricmc.fabric.api.event.Event;
import net.minecraft.entity.player.PlayerEntity;

import static net.fabricmc.fabric.api.event.EventFactory.createArrayBacked;

public interface AllowPlayerShotDeath {

    Event<AllowPlayerShotDeath> EVENT = createArrayBacked(AllowPlayerShotDeath.class, listeners -> player -> {
        for (AllowPlayerShotDeath listener : listeners) {
            if (!listener.allowShot(player)) {
                return false;
            }
        }
        return true;
    });

    boolean allowShot(PlayerEntity player);
}