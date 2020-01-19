package mb.mizinkobusters.kitpvp.kit;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import mb.mizinkobusters.kitpvp.KitPvP;
import mb.mizinkobusters.kitpvp.gui.SelectGUI;

public class HealthBoost implements Listener {

	/*
	 *
	 * HealthBoost Kit
	 *
	 */

	JavaPlugin plugin;
	SelectGUI kits;

	public HealthBoost(KitPvP plugin, SelectGUI kits) {
		this.plugin = plugin;
		this.kits = kits;
	}

	@EventHandler
	public void onKill(PlayerDeathEvent event) {
		Player player = event.getEntity();

		if (player.getKiller() != null && player.getKiller().getType().equals(EntityType.PLAYER)) {
			Player killer = player.getKiller();

			if (killer.getMaxHealth() < 26) {
				killer.setMaxHealth(killer.getMaxHealth() + 1);
				killer.setHealth(killer.getMaxHealth());
			}
		}
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		Player damagee = (Player) event.getEntity();
		Player damager = (Player) event.getDamager();

		if (kits.getKits().get(damagee.getUniqueId()).equals("HealthBoost")) {
			return;
		}

		if (kits.getKits().get(damager.getUniqueId()).equals("HealthBoost")) {
			return;
		}
	}
}
