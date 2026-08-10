package bayhasoft.adventurerscookbook.screen;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class SeedMakerScreen extends AbstractContainerScreen<SeedMakerScreemHandler>{
    private static final Identifier GUI_TEXTURE = AdventurersCookBook.id("textures/gui/seed_maker_gui.png");
    private static final Identifier PROGRESS_ARROW = AdventurersCookBook.id("textures/gui/progress_arrow.png");

    public SeedMakerScreen(SeedMakerScreemHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractBackground(graphics, mouseX, mouseY, delta);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight, 256, 256);

        renderProgressArrow(graphics, x, y);
    }

    private void renderProgressArrow(GuiGraphicsExtractor graphics, int x, int y) {
        if(menu.isCrafting()) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, PROGRESS_ARROW, x + 85, y + 30, 0, 0,
                    8, menu.getScaledArrowProgress(), 8, 26);
        }
    }

//    @Override
//    public void render(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
//        super.render(graphics, mouseX, mouseY, delta);
//        renderTooltip(graphics, mouseX, mouseY);
//    }
}
