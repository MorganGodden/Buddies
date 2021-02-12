package morgangodden.buddies;

import java.util.List;
import java.util.UUID;

import morgangodden.buddies.config.ClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class InputHandler {
	Main main;
	public static KeyBinding toggleGlow = new KeyBinding("key.buddies.toggle_glow",  71, "key.categories.buddies");
	public static KeyBinding togglePlayer = new KeyBinding("key.buddies.toggle_player",  82, "key.categories.buddies");
	
	InputHandler() { main = Main.instance; }
	
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
		if(main.mc == null) { return; }
		
		// Toggle Glow
		if(toggleGlow.isPressed()) {
			main.highlighter.enabled = !main.highlighter.enabled;
			
			main.mc.world.getPlayers().forEach(entity -> { main.highlighter.checkToHighlightEntity((Entity) entity); });
			main.player.sendMessage(
					ITextComponent.getTextComponentOrEmpty(I18n.format("chat.buddies.toggle_glow") + main.highlighter.enabled), null);
			
			// Output buddy names
			//List<ServerPlayerEntity> serverPlayers = main.mc.player.players.getPlayerList().getPlayers();
			//serverPlayers.forEach(player -> { 
			//	if(ClientConfig.UUIDs.get().contains(player.getUniqueID())){
			//		main.player.sendMessage(ITextComponent.getTextComponentOrEmpty("  - " + player.getScoreboardName()), null); }
			//});
		}
		
		// Toggle Player
		if(togglePlayer.isPressed()) {
			if(this.main.mc.pointedEntity == null || !main.mc.pointedEntity.getType().toString().equalsIgnoreCase("entity.minecraft.player")) {
				main.player.sendMessage( // Send ERROR message
						ITextComponent.getTextComponentOrEmpty(I18n.format("chat.buddies.toggle_player.error")), null); }
			else {
				Entity buddy = main.mc.pointedEntity;
				String buddyUUID = buddy.getUniqueID().toString();
				List<String> UUIDs = ClientConfig.UUIDs.get();
				
				if(UUIDs.contains(buddyUUID)) { 
					// Remove player from buddy list
					UUIDs.remove(buddyUUID); ClientConfig.UUIDs.set(UUIDs);
					if(buddy.isGlowing()) { buddy.setGlowing(false); }
					main.player.sendMessage( // Send DISABLED message
							ITextComponent.getTextComponentOrEmpty(main.mc.pointedEntity.getScoreboardName() + I18n.format("chat.buddies.toggle_player.disable")), null); }
				else { 
					// Add player to buddy list
					UUIDs.add(buddyUUID); ClientConfig.UUIDs.set(UUIDs);
					if(main.highlighter.enabled) { buddy.setGlowing(true); }
					main.player.sendMessage( // Send ACTIVE message
							ITextComponent.getTextComponentOrEmpty(main.mc.pointedEntity.getScoreboardName() + I18n.format("chat.buddies.toggle_player.enable")), null); }
				
				ClientConfig.UUIDs.save();
			}
		}
	}
}
