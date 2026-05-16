package io.github.jason13official.more_bows_and_arrows.impl.client.renderer;

import io.github.jason13official.more_bows_and_arrows.impl.common.entity.ModArrow;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.arrow.ArrowType;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.Identifier;

public class ModArrowRenderer extends ArrowRenderer<ModArrow, ArrowRenderState> {

  private final ArrowType type;

  public ModArrowRenderer(Context context, ArrowType type) {
    super(context);
    this.type = type;
  }

  @Override
  protected Identifier getTextureLocation(ArrowRenderState arrowRenderState) {
    return this.type.getTextureLocation();
  }

  @Override
  public ArrowRenderState createRenderState() {
    return new ArrowRenderState();
  }
}
