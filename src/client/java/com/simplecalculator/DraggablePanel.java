package com.simplecalculator;

import com.simplecalculator.mixin.client.WidgetMixin;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.ScreenRect;
import net.minecraft.client.gui.navigation.GuiNavigation;
import net.minecraft.client.gui.navigation.GuiNavigationPath;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class DraggablePanel implements Widget, Element, Drawable {
    private int x,y,width,height,deltaX,deltaY,edge;
    private List<ClickableWidget> widgets = new ArrayList<>();
    private Map<ClickableWidget, float[]> originalCoordinates = new HashMap<>();
    public boolean visible;
    private boolean ctrl=false;
    private static final Texture texture = new Texture(new Identifier("simplecalculator","textures/size/size.png"),5,5);
    private static final Texture texture1 = new Texture(new Identifier("simplecalculator","textures/size/size.png"),5,5);
    private static final Texture texture2 = new Texture(new Identifier("simplecalculator","textures/size/size.png"),5,5);
    private static final Texture texture3 = new Texture(new Identifier("simplecalculator","textures/size/size.png"),5,5);
    public DraggablePanel(int x,int y,int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public DraggablePanel(int x,int y,int width,int height,boolean visible){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.visible = visible;
    }
    public void add(ClickableWidget widget){
        this.widgets.add(widget);
        int x = widget.getX() - this.x;
        int y = widget.getY() - this.y;
        float coefficientX = (float) x /this.width;
        float coefficientY = (float) y /this.height;
        int width = this.width/widget.getWidth();
        int height = this.height/widget.getHeight();
        this.originalCoordinates.put(widget, new float[]{width,height,coefficientX,coefficientY});
    }
    public void add(List<ClickableWidget> widgets){
        for(ClickableWidget widget:widgets) {
            int x = widget.getX() - this.x;
            int y = widget.getY() - this.y;
            int width = this.width/widget.getWidth();
            int height = this.height/widget.getHeight();
            this.originalCoordinates.put(widget, new float[]{x,y,width,height});
        }
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
    public void setPosition(int x,int y){
        this.setX(x);
        this.setY(y);
    }
    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public ScreenRect getNavigationFocus() {
        return Widget.super.getNavigationFocus();
    }

    @Override
    public void forEachChild(Consumer<ClickableWidget> consumer) {

    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        Element.super.mouseMoved(mouseX, mouseY);
    }
    private double edge3x,edge3y;
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        deltaX = (int) mouseX - this.x;
        deltaY = (int) mouseY - this.y;
        if(texture2.isMouseOver(mouseX,mouseY)){
            edge = 2;
        } else if(texture3.isMouseOver(mouseX,mouseY)) {
            edge3x = mouseX;
            edge3y = mouseY;
            edge = 3;
        }
        return Element.super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        edge = -1;
        return Element.super.mouseReleased(mouseX, mouseY, button);
    }
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if(isMouseOverPanel(mouseX,mouseY,this.x,this.y,this.width,this.height)&&!ctrl) {
            this.x = (int) (mouseX - this.deltaX);
            this.y = (int) (mouseY - this.deltaY);
            for (Map.Entry<ClickableWidget, float[]> entry : originalCoordinates.entrySet()) {
                ClickableWidget widget = entry.getKey();
                float[] coords = entry.getValue();
                widget.setX((int) (this.x+coords[2]*this.width));
                widget.setY((int) (this.y+coords[3]*this.height));
            }
        }
        if(ctrl) {
            switch (edge) {
                case 2 -> {
                    if (mouseX - this.x > 36 && mouseY - this.y > 46) {
                        this.width = (int) (mouseX - this.x);
                        this.height = (int) (mouseY - this.y);
                        updateWidgets();
                    }
                }
                case 3 -> {
                    //if (Math.abs(mouseX - this.x + this.width) > 36 && Math.abs(mouseY - this.y + this.height) > 46) {
                        this.width = (int) ((int) edge3x - mouseX);
                        this.height = (int) ((int) edge3y - mouseY);
                        this.x = (int) mouseX;
                        this.y = (int) (this.y + mouseY - edge3y);
                        updateWidgets();
                        // }
                }
            }
        }


        return Element.super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
    private void updateWidgets(){
        for (Map.Entry<ClickableWidget, float[]> entry : originalCoordinates.entrySet()) {
            ClickableWidget widget = entry.getKey();
            float[] coords = entry.getValue();
            widget.setX((int) (this.x + coords[2] * this.width));
            widget.setY((int) (this.y + coords[3] * this.height));
            widget.setWidth((int) (this.width / coords[0]));
            ((WidgetMixin) widget).setHeight((int) (this.height / coords[1]));
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        return Element.super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(keyCode == GLFW.GLFW_KEY_LEFT_CONTROL){
            ctrl = true;
        }
        return Element.super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        if(keyCode == GLFW.GLFW_KEY_LEFT_CONTROL){
            ctrl = false;
        }
        return Element.super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        return Element.super.charTyped(chr, modifiers);
    }

    @Nullable
    @Override
    public GuiNavigationPath getNavigationPath(GuiNavigation navigation) {
        return Element.super.getNavigationPath(navigation);
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return Element.super.isMouseOver(mouseX, mouseY);
    }

    @Override
    public void setFocused(boolean focused) {

    }

    @Override
    public boolean isFocused() {
        return false;
    }

    @Nullable
    @Override
    public GuiNavigationPath getFocusedPath() {
        return Element.super.getFocusedPath();
    }

    @Override
    public int getNavigationOrder() {
        return Element.super.getNavigationOrder();
    }
    private boolean isMouseOverPanel(double mouseX, double mouseY, int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
        return mouseX >= buttonX && mouseX <= buttonX + buttonWidth && mouseY >= buttonY && mouseY <= buttonY + buttonHeight;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        try {
            texture.render(context,mouseX,mouseY, delta);
            texture1.render(context,mouseX,mouseY, delta);
            texture2.render(context,mouseX,mouseY, delta);
            texture3.render(context,mouseX,mouseY, delta);
            texture.visible=ctrl;
            texture1.visible=ctrl;
            texture2.visible=ctrl;
            texture3.visible=ctrl;
            texture.setPosition(this.x,this.y);
            texture1.setPosition(this.x+this.width,this.y);
            texture2.setPosition(this.x+this.width,this.y+this.height);
            texture3.setPosition(this.x,this.y+this.height);
            if(!ctrl){
                edge = -1;
            }
            if (!this.visible) {
                for(ClickableWidget widget:widgets){
                    widget.visible = false;
                }
            }else{
                for(ClickableWidget widget:widgets){
                    widget.visible = true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
