package net.bielszabiel.whisperers;

import net.bielszabiel.whisperers.Item.ModItems;
import net.bielszabiel.whisperers.block.ModBlocks;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//coś ważnego i potrzebnego
public class Whisperers implements ModInitializer {
	public static final String MOD_ID = "whisperers";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registermoditems();
		ModBlocks.registerModBlocks();


	}
}