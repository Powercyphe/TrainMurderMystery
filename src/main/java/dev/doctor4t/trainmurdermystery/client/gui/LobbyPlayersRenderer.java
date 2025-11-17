package dev.doctor4t.trainmurdermystery.client.gui;

import dev.doctor4t.ratatouille.util.TextUtils;
import dev.doctor4t.trainmurdermystery.cca.GameWorldComponent;
import dev.doctor4t.trainmurdermystery.game.GameConstants;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class LobbyPlayersRenderer {
    public static void renderHud(TextRenderer renderer, @NotNull ClientPlayerEntity player, @NotNull DrawContext context) {
        var game = GameWorldComponent.KEY.get(player.getWorld());
        if (!game.isRunning()) {
            context.getMatrices().push();
            context.getMatrices().translate(context.getScaledWindowWidth() / 2f, 6, 0);
            var world = player.getWorld();
            var players = world.getPlayers();
            var count = players.size();
            var ready = players.stream().filter(p -> GameConstants.READY_AREA.contains(p.getPos())).count();
            var playerCountText = Text.translatable("lobby.players.count", ready, count);
            context.drawTextWithShadow(renderer, playerCountText, -renderer.getWidth(playerCountText) / 2, 0, 0xFFFFFFFF);
            context.getMatrices().pop();

            context.getMatrices().push();
            float scale = 0.75f;
            context.getMatrices().translate(0, context.getScaledWindowHeight(), 0);
            context.getMatrices().scale(scale, scale, 1f);
            int i = 0;
            for (Text text : TextUtils.getWithLineBreaks(Text.translatable("lobby.credit", ready, count))) {
                i++;
                context.drawTextWithShadow(renderer, text, 10, -80 + 10 * i, 0xFFFFFFFF);
            }
            context.getMatrices().pop();
        }
    }
}