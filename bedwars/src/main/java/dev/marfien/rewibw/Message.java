package dev.marfien.rewibw;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Message {

    public static final Message GAME_ALREADY_RUNNING = bad("Le jeu est déjà en cours.");
    public static final Message FORCEMAP_COMMAND_TOO_LATE = bad("Tu ne peux plus changer la carte dans les 10 dernières secondes.");
    public static final Message FORCEMAP_COMMAND_USAGE = bad("Utilisation : /forcemap <Carte>");

    public static final Message UNKNOWN_MAP = bad("La carte §a%s§r n'existe pas.");
    public static final Message AVAILABLE_MAPS = system("Cartes disponibles : §a%s");
    public static final Message MAP_CHANGED = system("La carte a été changée en §a%s§r.");

    public static final Message COUNTDOWN_STARTED = info("Le compte à rebours a commencé.");
    public static final Message START_COMMAND_TOO_LATE = bad("§cLe compte à rebours ne peut plus être réduit.");
    public static final Message START_COMMAND_COUNTDOWN_REDUCED = info("Le compte à rebours a été réduit.");

    public static final Message LOBBY_IDLE = info("Il manque encore §a%d§r joueurs pour que la partie commence.");
    public static final Message LOBBY_JOIN = system("%s§r a rejoint le serveur §8(§a%d§8/§a" + RewiBWPlugin.getPluginConfig().getTeams().getMaxPlayers() + "§8)");
    public static final Message LOBBY_LEAVE = system("%s§r a quitté le serveur.");
    public static final Message COUNTDOWN_BEGAN = info("Le compte à rebours a commencé.");
    public static final Message GAME_STARTS_IN = info("La phase de jeu commence dans §a%d§r secondes.");

    public static final Message GAME_ENDS_IN_MINUTES = system("Le jeu se termine dans §a%d§r minutes.");
    public static final Message GAME_ENDS_IN_SECONDS = system("Le jeu se termine dans §a%d§r secondes.");

    public static final Message MOBILE_SHOP_NOT_GROUND = bad("Tu ne peux placer la boutique mobile que sur le sol.");

    public static final Message PARACHUTE_ON_GROUND = bad("Tu es déjà au sol.");
    public static final Message PARACHUTE_ALREADY_ACTIVE = bad("Tu as déjà un parachute actif.");
    public static final Message PARACHUTE_BROKE = bad("Ton parachute s'est cassé.");

    public static final Message ALREADY_TELEPORTING = bad("Tu es déjà en cours de téléportation !");
    public static final Message TELEPORTER_BELOW_BLOCK = bad("Tu dois être sous un ciel dégagé !");
    public static final Message TELEPORT_CANCELLED = bad("La téléportation a été annulée !");
    public static final Message TELEPORTER_LOST = bad("Tu n'as plus de téléporteur !");

    public static final Message PLAYER_DIED = system("%s§r est mort.");
    public static final Message PLAYER_KILLED = system("%s§r a été tué par %s §8[§c❤%s§8] §r.");

    public static final Message CANNOT_PLACE_BLOCKS_TEAMSPAWN = bad("Tu ne peux pas placer de blocs sur un point de réapparition.");

    public static final Message WEB_COOLDOWN = bad("Tu ne peux placer une toile d'araignée que toutes les 20 secondes.");
    public static final Message SHOP_NOT_ENOUGH_RESOURCES = bad("Tu n'as pas assez de %s§r.");
    public static final Message OWN_BED_DESTROY = bad("Tu ne peux pas détruire ton propre lit.");
    public static final Message TEAM_CHEST_DESTROY = bad("Un coffre d'équipe a été détruit.");

    public static final Message SELECT_PERK = system("Tu as sélectionné le perk §a%s§r.");
    public static final Message ALREADY_IN_TEAM = bad("Tu es déjà dans cette équipe !");
    public static final Message TEAM_JOINED = system("Tu es maintenant dans l'équipe %s§r.");
    public static final Message TEAM_FULL = bad("L'équipe est déjà complète !");
    public static final Message TEAM_ASSIGNED = system("Tu as été assigné à l'équipe %s§r.");
    public static final Message NO_TEAM_FOUND = bad("Aucune équipe n'a pu être trouvée pour toi.");
    public static final Message TEAM_BROADCAST_HEADER = of("▆▆▆▆▆▆▆▆▆▆ §6§lÉquipes§r ▆▆▆▆▆▆▆▆▆▆", ChatColor.WHITE);
    public static final Message TEAM_BROADCAST_FORMAT = of("▆ %sÉquipe %s§r : §6%s", ChatColor.WHITE);
    public static final Message VOTING_BROADCAST = of(
            " \n" +
                    "          §o§cVote terminé ! §r\n" +
                    "          §6Carte : §a%s\n" +
                    " ",
            ChatColor.WHITE);
    public static final Message VOTE_CHANGED = system("Tu as changé ton vote pour §a%s§r.");
    public static final Message VOTE_CAST = system("Tu as voté pour la carte §a%s§r.");

    public static final Message BED_DESTROYED = system("Le lit de l'équipe %s§r a été détruit par %s§r.");
    public static final Message TEAM_ELIMINATED = system("L'équipe %s§r a été éliminée.");
    public static final Message REMAINING_TEAMS = system("Il reste §a%d§r équipes et §a%d§r joueurs.");

    public static final Message INGAME_LEAVE = system("%s§r a quitté le jeu.");
    public static final Message SERVER_STOPPING = bad("Le serveur s'arrête dans §a%d§r secondes.");
    public static final Message BROADCAST_WINNER = of("L'équipe %s§r a remporté la partie !", ChatColor.AQUA);
    public static final Message NO_WINNER = info("Aucune équipe n'a pu remporter la partie.");
    public static final Message BLOCK_OUT_OF_MAP = bad("Tu ne peux pas placer de blocs ici !");
    public static final Message TEAM_CHEST_NO_ACCESS = bad("Ce coffre d'équipe n'appartient pas à ton équipe !");
    public static final Message PLAYER_NOT_FOUND = bad("Le joueur n'a pas pu être trouvé.");
    public static final Message DEATH_TITLE = system("Tu es mort");
    public static final Message DEATH_TITLE_KILLED = system("Tu as été tué");
    public static final Message RESET_PERK = system("Ton perk a été réinitialisé.");
    public static final Message RESCUE_PLATFORM_ON_GROUND = bad("Tu es déjà au sol.");
    public static final Message SHOP_NOT_ENOUGH_SPACE = bad("Tu n'as pas assez de place dans ton inventaire.");
    public static final Message SPECTATOR_TARGET = of("Tu regardes maintenant §a%s§r.", ChatColor.AQUA);
    public static final Message GAME_NOT_STARTED_YET = bad("Le jeu n'a pas encore commencé.");
    public static final Message MAP_NAME = system("La carte s'appelle §a%s§r §8(§a%s§8)§r.");
    public static final Message JUMP_AND_RUN_FINISH = info("Tu as terminé le Jump'n'Run en §a%s§r secondes !");
    public static final Message JUMP_AND_RUN_NEW_RECORD = info("Tu as établi un nouveau record : §a%s§r.");
    public static final Message CATCH_CATCHED = info("%s§r a maintenant la tête. Vite !");
    public static final Message CATCH_SELECTED = info("Tu as maintenant la tête. Cours !");

    private final String string;

    @Override
    public String toString() {
        return this.string;
    }

    public String format(Object... args) {
        return String.format(this.string, args);
    }

    public static Message good(String message) {
        return of(message, ChatColor.GREEN);
    }

    public static Message system(String message) {
        return of(message, ChatColor.GOLD);
    }

    public static Message info(String message) {
        return of(message, ChatColor.GRAY);
    }

    public static Message bad(String message) {
        return of(message, ChatColor.RED);
    }

    public static Message of(String message, ChatColor baseColor) {
        return new Message(baseColor + message.replace("§r", "§r" + baseColor));
    }

    public static void broadcast(String message) {
        if (Bukkit.getOnlinePlayers().isEmpty()) return;

        Bukkit.getConsoleSender().sendMessage(message);
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(message);
        }
    }

}
