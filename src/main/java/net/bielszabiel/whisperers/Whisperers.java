package net.bielszabiel.whisperers;

import net.bielszabiel.whisperers.Item.ModItems;
import net.bielszabiel.whisperers.block.ModBlocks;
import net.bielszabiel.whisperers.entity.ModEntitys;
import net.bielszabiel.whisperers.entity.custom.WhispererEntity;
import net.bielszabiel.whisperers.entity.custom.WhispererKingEntity;
import net.bielszabiel.whisperers.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Whisperers implements ModInitializer {
	public static final String MOD_ID = "whisperers";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		//dodawanie ważnych rzeczy
		ModItems.registermoditems();
		ModBlocks.registerModBlocks();
		ModEntitys.registerModEntitys();
		ModLootTableModifiers.modifyLootTables();
		FabricDefaultAttributeRegistry.register(ModEntitys.WHISPERER, WhispererEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntitys.WHISPERER_KING, WhispererKingEntity.createAttributes());

	}
}