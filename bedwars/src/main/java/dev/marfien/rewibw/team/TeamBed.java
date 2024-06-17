package dev.marfien.rewibw.team;

import de.slikey.effectlib.EffectType;
import de.slikey.effectlib.effect.HelixEffect;
import de.slikey.effectlib.util.ParticleEffect;
import dev.marfien.rewibw.Message;
import dev.marfien.rewibw.RewiBWPlugin;
import dev.marfien.rewibw.game.playing.PlayingGameState;
import dev.marfien.rewibw.perk.PerkManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.MaterialData;

@Getter
public class TeamBed implements Listener {

    private final Block firstBedBlock;
    private final Block secondBedBlock;
    private final GameTeam team;

    private boolean alive = true;

    public TeamBed(GameTeam team, Block block, BlockFace direction) {
        this.team = team;
        this.firstBedBlock = block;
        this.secondBedBlock = this.firstBedBlock.getRelative(direction);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onBedBreak(BlockBreakEvent event) {
        if (!this.alive || !this.isValid(event.getBlock())) return;

        Player breaker = event.getPlayer();
        if (this.team.isMember(breaker)) {
            event.setCancelled(true);
            breaker.sendMessage(RewiBWPlugin.PREFIX + Message.OWN_BED_DESTROY);
            return;
        }

        this.firstBedBlock.setType(Material.AIR);
        this.secondBedBlock.setType(Material.AIR);
        Block block = event.getBlock();
        startBreakEffect(block.getLocation().add(0.5, 0.5, 0.5));
        block.getWorld().playEffect(block.getLocation(), Effect.TILE_BREAK, new MaterialData(block.getType()));

        PlayingGameState.getSidebarObjective().removeScore(this.team.getScoreboardEntry());
        this.alive = false;
        this.team.updateScoreboardEntry();
        this.team.getDisplayScoreboardTeam().updatePrefix();
        Message.broadcast(RewiBWPlugin.PREFIX + Message.BED_DESTROYED.format(this.team.getColor().getDisplayName(), breaker.getDisplayName()));
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), PerkManager.BED_DESTROY_SOUND_PERK_GROUP.getOrDefault(player).getData(), 1F, 0.8F);
        }
        for (Player member : this.team.getMembers()) {
            member.sendTitle("§6Dein Bett", "§6wurde zerstört");
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onPhysics(BlockPhysicsEvent event) {
        if (!this.isValid(event.getBlock())) return;

        event.setCancelled(false);
    }

    @EventHandler
    private void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (!this.isValid(event.getClickedBlock())) return;

        event.setUseInteractedBlock(Event.Result.DENY);
    }

    private boolean isValid(Block block) {
        return this.firstBedBlock.equals(block) || this.secondBedBlock.equals(block);
    }

    public double distanceSquared(Location location) {
        return Math.min(
                this.firstBedBlock.getLocation().distanceSquared(location),
                this.secondBedBlock.getLocation().distanceSquared(location)
        );
    }

    private static void startBreakEffect(Location location) {
        HelixEffect effect = new HelixEffect(RewiBWPlugin.getEffectManager());
        effect.asynchronous = true;
        effect.radius = 1;
        effect.type = EffectType.INSTANT;
        effect.particles = 20;
        effect.setLocation(location);
        effect.particle = ParticleEffect.SPELL_WITCH;
        effect.offset = RewiBWPlugin.ZERO_VECTOR;
        effect.particleCount = 1;
        effect.start();
    }
}
