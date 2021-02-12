package morgangodden.buddies;

import morgangodden.buddies.config.ClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Highlighter {
	Main main;
	public boolean enabled;
	boolean inGame = false;
	
	
	Highlighter() { main = Main.instance; enabled = false; }
	
	@SubscribeEvent
	public void loadEntities(EntityJoinWorldEvent event) { 
		if(!inGame) {
			inGame = true; 
			main.mc = Minecraft.getInstance();
			main.player = main.mc.player; }
		
		checkToHighlightEntity(event.getEntity()); }
	
	
	public void checkToHighlightEntity(Entity entity) {
		if(entity.getType().toString().equalsIgnoreCase("entity.minecraft.player")) {
			entity.setGlowing(enabled && ClientConfig.UUIDs.get().contains(entity.getUniqueID().toString()));
		}
	}
}