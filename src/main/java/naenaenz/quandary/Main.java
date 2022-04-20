package naenaenz.quandary;

import naenaenz.quandary.entity.DubEntity;
import naenaenz.quandary.register.BlockRegister;
import naenaenz.quandary.register.ItemRegister;
import naenaenz.quandary.register.ParticleRegister;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static naenaenz.quandary.register.EntityRegister.DUB;

public class Main implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("quandary");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("registering");
		ItemRegister.register();
		BlockRegister.register();
		ParticleRegister.register();
		FabricDefaultAttributeRegistry.register(DUB, DubEntity.createMobAttributes());
		LOGGER.info("registered");
	}
}
