package morgangodden.buddies;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.Button.IPressable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Menu extends Screen {
	//private final Screen menuScreen;
	
   private static final ResourceLocation HOPPER_GUI_TEXTURE = new ResourceLocation("textures/gui/container/hopper.png");
	
	public Menu(ITextComponent text) {
		super(text);
		this.addButton(new Button(this.width / 2 - 4 - 150, 210, 150, 20, ITextComponent.getTextComponentOrEmpty("Okay"), abc()));
	}
	
	IPressable abc() {
		return null;}
	
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
    
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(0.0F, 0.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(HOPPER_GUI_TEXTURE);

     }

}
