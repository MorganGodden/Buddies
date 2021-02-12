package morgangodden.buddies.config;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import morgangodden.buddies.Main;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ConfigBuilder {
	private static final ForgeConfigSpec.Builder client_builder = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec client_config;
	
	static { 
		ClientConfig.init(client_builder);
		client_config = client_builder.build(); }
	
	public static void loadConfig(ForgeConfigSpec config, String path) {
		Main.logger.info("Loading Config: " + path);
		
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
		Main.logger.info("Built Config: " + path);
		file.load();
		Main.logger.info("Loaded Config: " + path);
		
		config.setConfig(file);
	}
}
