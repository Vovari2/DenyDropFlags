package me.vovari2.denydropflags;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.item.ItemType;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.config.WorldConfiguration;
import com.sk89q.worldguard.internal.platform.WorldGuardPlatform;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.RegistryFlag;
import com.sk89q.worldguard.protection.flags.SetFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Set;

public class WorldGuardUtils {

    private static WorldGuardPlatform platform;
    private static SetFlag<ItemType> DENY_DROP_ENTITIES;
    private static SetFlag<ItemType> DENY_DROP_BLOCKS;

    public static void load(){
        FlagRegistry flagRegistry = WorldGuard.getInstance().getFlagRegistry();
        SetFlag<ItemType> flag = new SetFlag<>(
                "deny-drop-entities",
                new RegistryFlag<>(null, ItemType.REGISTRY));
        flagRegistry.register(flag);
        DENY_DROP_ENTITIES = flag;

        SetFlag<ItemType> flag2 = new SetFlag<>(
                "deny-drop-blocks",
                new RegistryFlag<>(null, ItemType.REGISTRY));
        flagRegistry.register(flag2);
        DENY_DROP_BLOCKS = flag2;

    }
    public static void enable(){
        platform = WorldGuard.getInstance().getPlatform();
    }


    public static WorldConfiguration getWorldConfiguration(World world){
        return platform.
                getGlobalStateManager().
                get(BukkitAdapter.adapt(world));
    }
    public static Set<ItemType> getItemsInFlagOfDenyDropEntities(Location location){
        return getApplicableRegionSet(location)
                .queryValue(null, DENY_DROP_ENTITIES);
    }
    public static Set<ItemType> getItemsInFlagOfDenyDropBlocks(Location location){
        return getApplicableRegionSet(location)
                .queryValue(null, DENY_DROP_BLOCKS);
    }

    private static ApplicableRegionSet getApplicableRegionSet(Location location){
        return platform.
                getRegionContainer().
                createQuery().
                getApplicableRegions(BukkitAdapter.adapt(location));
    }
}
