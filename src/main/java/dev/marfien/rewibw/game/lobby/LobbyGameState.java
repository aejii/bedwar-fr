package dev.marfien.rewibw.game.lobby;

import dev.marfien.rewibw.RewiBWPlugin;
import dev.marfien.rewibw.fakemobs.FakeEntityManager;
import dev.marfien.rewibw.game.GameState;
import dev.marfien.rewibw.game.lobby.listeners.LobbyWorldListener;
import dev.marfien.rewibw.game.lobby.listeners.PlayerConnectionListener;
import dev.marfien.rewibw.game.lobby.listeners.PlayerListener;
import dev.marfien.rewibw.team.TeamManager;
import dev.marfien.rewibw.usable.ConsumeType;
import dev.marfien.rewibw.usable.UsableItemInfo;
import dev.marfien.rewibw.usable.UsableItemManager;
import dev.marfien.rewibw.util.CpsTester;
import dev.marfien.rewibw.util.Items;
import dev.marfien.rewibw.voting.MapVoting;
import dev.marfien.rewibw.world.GameWorld;
import lombok.Getter;
import org.bukkit.event.Listener;

import java.util.ArrayList;

@Getter
public class LobbyGameState extends GameState {

    @Getter
    private static final LobbyGameState instance = new LobbyGameState();

    private final GameWorld world = new GameWorld("lobby");
    private final UsableItemManager itemManager = new UsableItemManager();
    private final LobbyCountdown countdown = new LobbyCountdown();

    private final ArrayList<Listener> listeners = new ArrayList<>();

    {
        this.listeners.add(new PlayerListener());
        this.listeners.add(new LobbyWorldListener(this.world));
        this.listeners.add(new PlayerConnectionListener());

        this.itemManager.putHandler(Items.VOTE_ITEM, new UsableItemInfo(ConsumeType.NONE, event -> MapVoting.openGui(event.getPlayer())));
        this.itemManager.putHandler(Items.PERKS_ITEM, new UsableItemInfo(ConsumeType.NONE, event -> event.getPlayer().sendMessage("§cDu kannst keine Perks kaufen!")));
    }

    public Listener[] getListeners() {
        return this.listeners.toArray(new Listener[0]);
    }

    @Override
    public void onStart() {
        this.itemManager.register();
        this.countdown.startIdle();
        this.world.load();
        FakeEntityManager.spawn(new CpsTester(this.world.getLocation("cps")));
        this.listeners.addAll(TeamManager.init(this.world));
    }

    @Override
    public void onStop() {
        this.itemManager.shutdown();
    }

}
