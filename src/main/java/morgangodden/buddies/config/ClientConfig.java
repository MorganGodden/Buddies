package morgangodden.buddies.config;

import java.util.LinkedList;
import java.util.List;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class ClientConfig {
	public static ConfigValue<List<String>> UUIDs;
	
	 public static void init(ForgeConfigSpec.Builder clientBuilder) {
		clientBuilder.push("Buddy Client Config");
		UUIDs = clientBuilder.comment("UUIDs of all Buddies").define("uuids", new LinkedList<String>());
		clientBuilder.pop();
	}
}
