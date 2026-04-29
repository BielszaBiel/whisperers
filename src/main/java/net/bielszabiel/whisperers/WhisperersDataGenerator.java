package net.bielszabiel.whisperers;

import net.bielszabiel.whisperers.datagen.ModBlockTagProvider;
import net.bielszabiel.whisperers.datagen.ModModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class WhisperersDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

pack.addProvider(ModModelProvider::new);
	}
}
