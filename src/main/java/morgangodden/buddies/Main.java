package morgangodden.buddies;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import morgangodden.buddies.config.ConfigBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod("buddies")
public class Main {
	public static Main instance;
	static 	Highlighter highlighter;
	
	public static final String MODID = "buddies";
	public static final Logger logger = LogManager.getLogger(MODID);
	
	public Minecraft mc;
	public ClientPlayerEntity player;
	
	public Main() { 
		// Defining instances
		instance = this;
		highlighter = new Highlighter();
		
		// Config/Data
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigBuilder.client_config);
		ConfigBuilder.loadConfig(ConfigBuilder.client_config, FMLPaths.CONFIGDIR.get().resolve("buddies-client.toml").toString());
		
		// Registries
		MinecraftForge.EVENT_BUS.register(this);
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		modBus.addListener(this::setupClient);
	}
	
	private void setupClient(FMLClientSetupEvent e) {
		// Register key bindings
		ClientRegistry.registerKeyBinding(InputHandler.toggleGlow);
		ClientRegistry.registerKeyBinding(InputHandler.togglePlayer);
		
		// Register events
		MinecraftForge.EVENT_BUS.register(highlighter);
		MinecraftForge.EVENT_BUS.register(new InputHandler());
	}
}
