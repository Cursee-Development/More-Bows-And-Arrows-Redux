package com.cursee.more_bows_and_arrows.client.renderer.entity;

import com.cursee.more_bows_and_arrows.core.world.entity.projectile.ModArrow;
import com.cursee.more_bows_and_arrows.core.world.entity.projectile.util.ArrowType;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ModArrowRenderer extends ArrowRenderer<ModArrow> {

    private final ArrowType type;

    public ModArrowRenderer(EntityRendererProvider.Context context, ArrowType type) {
        super(context);
        this.type = type;
    }

    @Override
    public ResourceLocation getTextureLocation(ModArrow arrow) {
        return this.type.getTextureLocation();
    }

    @Override
    public void render(ModArrow entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (this.type == ArrowType.REALLY_BIG) poseStack.scale(5.0f, 5.0f, 5.0f);
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public boolean shouldRender(ModArrow livingEntity, Frustum camera, double camX, double camY, double camZ) {
        return true;
    }

    //    @Override
//    public void render(ModArrow entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
//        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
//        // particles??????????????????? todo impl
//    }
}
