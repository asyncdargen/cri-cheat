package ru.zalupa.util;

import dev.xdark.clientapi.entity.Entity;
import dev.xdark.clientapi.entity.EntityPlayerSP;
import dev.xdark.clientapi.math.Vec3d;
import lombok.experimental.UtilityClass;
import ru.zalupa.Zalupa;
import ru.zalupa.gui.GuiScreen;

import static dev.xdark.clientapi.opengl.GlStateManager.*;

@UtilityClass
public class RenderTools {

    final int color = GuiScreen.rgb(58, 104, 176, 230);
    final int colorc = GuiScreen.rgb(205, 64, 108, 230);

    public void drawBlockEsp(int x, int y, int z, float partialTick, boolean current) {
        EntityPlayerSP self = Zalupa.getSelfPlayer();
        pushMatrix();
        translate(
                -self.getLastX() - (self.getX() - self.getLastX()) * partialTick + x,
                -self.getLastY() - (self.getY() - self.getLastY()) * partialTick + y + 1,
                -self.getLastZ()- (self.getZ() - self.getLastZ()) * partialTick + z
        );
        glNormal3f(0, 1f, 0);
        scale(-0.025F, -0.025F, 0.025F);
        disableLighting();
        depthMask(false);
        disableDepth();
        enableBlend();
        tryBlendFuncSeparate(770, 771, 1, 0);
        disableTexture2D();
        rotate(90, 1f, 0, 0);
        Zalupa.getApi().overlayRenderer().drawRect(-15, -15, -25, -25, current ? colorc : color);
        enableTexture2D();
        enableDepth();
        depthMask(true);
        enableLighting();
        disableBlend();
        popMatrix();
    }

    public void drawTracerLine(Entity entity, float x, float y, float z) {
        EntityPlayerSP self = Zalupa.getSelfPlayer();
        Vec3d eyeVector = Vec3d.of(0.0D, 0.0D, 1.0D).rotatePitch((float)(-Math.toRadians(self.getRotationPitch()))).rotateYaw((float)(-Math.toRadians(self.getRotationYaw())));
        float color = (float)Math.round(Vec3d.of(self.getX(), self.getY(), self.getZ()).distanceTo(entity.getX(), entity.getY(), entity.getZ())) / 20.0F;
        color(2.0F - color, color, 0.0F, 1.0F);
        glBegin(1);
        glVertex3f((float) eyeVector.getX(), (float) ((double)self.getEyeHeight() + eyeVector.getY()), (float) eyeVector.getZ());
        glVertex3f(x, y, z);
        glEnd();
    }
}
