package me.vovari2.denydropflags;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.item.ItemType;
import com.sk89q.worldguard.config.WorldConfiguration;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Set;

public class EventListeners implements Listener {

    @EventHandler
    public void addItemInDroppedItemsToEntityDeath(EntityDeathEvent event){
        Entity entity = event.getEntity();
        WorldConfiguration worldConfiguration = WorldGuardUtils.getWorldConfiguration(entity.getWorld());
        if (!worldConfiguration.useRegions)
            return;

        Location location = entity.getLocation();
        Set<ItemType> itemTypes = WorldGuardUtils.getItemsInFlagOfDenyDropEntities(location);
        if (itemTypes == null)
            return;

        event.getDrops().removeIf(itemStack -> itemTypes.contains(BukkitAdapter.asItemType(itemStack.getType())));
    }

    @EventHandler
    public void addItemInDroppedItemsToBreakBlock(BlockDropItemEvent event){
        Block block = event.getBlock();
        WorldConfiguration worldConfiguration = WorldGuardUtils.getWorldConfiguration(block.getWorld());
        if (!worldConfiguration.useRegions)
            return;

        Set<ItemType> itemTypes = WorldGuardUtils.getItemsInFlagOfDenyDropBlocks(block.getLocation());
        if (itemTypes == null)
            return;

        event.getItems().removeIf(itemFromBlock -> itemTypes.contains(BukkitAdapter.asItemType(itemFromBlock.getItemStack().getType())));
    }
}

