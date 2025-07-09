package bayhasoft.adventurerscookbook.screen;

import bayhasoft.adventurerscookbook.AdventurersCookBook;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SeedMakerScreen extends HandledScreen<SeedMakerScreemHandler>{
    private static final Identifier GUI_TEXTURE = AdventurersCookBook.id("textures/gui/seed_maker_gui.png");
    private static final Identifier PROGRESS_ARROW = AdventurersCookBook.id("textures/gui/progress_arrow.png");

    public SeedMakerScreen(SeedMakerScreemHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight, 256, 256);

        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            context.drawTexture(RenderPipelines.GUI_TEXTURED, PROGRESS_ARROW, x + 85, y + 30, 0, 0,
                    8, handler.getScaledArrowProgress(), 8, 26);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
