package net.bielszabiel.whisperers.datagen;

import net.bielszabiel.whisperers.block.ModBlocks;
import net.bielszabiel.whisperers.block.custom.DeepberryBushBlock;
import net.bielszabiel.whisperers.Item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.DEEPBERRY_BUSH,
                BlockStateModelGenerator.TintType.NOT_TINTED,
                DeepberryBushBlock.AGE, 0, 1, 2, 3);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // Rejestruje model przedmiotu dla bloku krzaka
    }
}

