package net.bielszabiel.whisperers.block;

import net.bielszabiel.whisperers.block.custom.DeepberryBushBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.bielszabiel.whisperers.Whisperers;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block DEEPBERRY_BUSH = registerBlock("deepberry_bush",
            new DeepberryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Whisperers.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Whisperers.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        Whisperers.LOGGER.info("Registering Mod Blocks for " + Whisperers.MOD_ID);
    }
}