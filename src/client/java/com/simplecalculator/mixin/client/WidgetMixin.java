package com.simplecalculator.mixin.client;

import net.minecraft.client.gui.widget.ClickableWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ClickableWidget.class)
public interface WidgetMixin {
    @Accessor("height")
    void setHeight(int height);

    @Accessor("height")
    int getHeight();
}
