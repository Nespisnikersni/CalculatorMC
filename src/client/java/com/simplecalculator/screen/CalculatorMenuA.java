package com.simplecalculator.screen;

import com.simplecalculator.DraggablePanel;
import com.simplecalculator.SimpleCalculatorClient;
import com.simplecalculator.mixin.client.WidgetMixin;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.text.Text;

import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

import static com.simplecalculator.SimpleCalculatorClient.evaluate;

@Environment(EnvType.CLIENT)
public class CalculatorMenuA extends Screen {
    TextFieldWidget textFieldWidget;
    private Identifier texture = new Identifier("minecraft", "textures/gui/demo_background.png");
    private DraggablePanel panel;
    private ButtonWidget buttonWidget, buttonWidget1, buttonWidget2, buttonWidget3, buttonWidget4, buttonWidget5, buttonWidget6,
            buttonWidget7, buttonWidget8, buttonWidget9, buttonWidget10, buttonWidget11, buttonWidget12, buttonWidget13, buttonWidget14, buttonWidget15, buttonWidget16, buttonWidget20, buttonWidget21, buttonWidget22, buttonWidget23;
    private Boolean regular = false, nether = false;
    private TextWidget textWidget, textWidget2;
    private TextFieldWidget textFieldWidget1, textFieldWidget2, textFieldWidget3, textFieldWidget4, textFieldWidget5, textFieldWidget6;
    int x, y, x1, y1;

    public CalculatorMenuA(Text title) {
        super(title);
    }

    @Override
    protected void init() {
        int x, y, z;
        x = (int) MinecraftClient.getInstance().player.capeX;
        y = (int) MinecraftClient.getInstance().player.capeY;
        z = (int) MinecraftClient.getInstance().player.capeZ;
        panel = new DraggablePanel(width / 2 - 40, 40,85,105,false);
        buttonWidget20 = createButton(width / 2 + 50, 60, 50, 20, "evaluate", () -> {
            textFieldWidget4.setText(String.valueOf(x / 8));
            textFieldWidget5.setText(String.valueOf(y / 8));
            textFieldWidget6.setText(String.valueOf(z / 8));
        });
        buttonWidget21 = createButton(width / 2 + 50, 100, 50, 20, "evaluate", () -> {
            textFieldWidget1.setText(String.valueOf(x * 8));
            textFieldWidget2.setText(String.valueOf(y * 8));
            textFieldWidget3.setText(String.valueOf(z * 8));
        });
        buttonWidget22 = createButton(width / 2 + 110, 60, 50, 20, "my cords", () -> {
            textFieldWidget1.setText(String.valueOf(x));
            textFieldWidget2.setText(String.valueOf(y));
            textFieldWidget3.setText(String.valueOf(z));
        });
        buttonWidget23 = createButton(width / 2 + 110, 100, 50, 20, "my cords", () -> {
            textFieldWidget4.setText(String.valueOf(x));
            textFieldWidget5.setText(String.valueOf(y));
            textFieldWidget6.setText(String.valueOf(z));
        });
        textFieldWidget1 = createInput(width / 2 - 120, 60, 40, 20, null, () -> {
        });
        textFieldWidget2 = createInput(width / 2 - 60, 60, 40, 20, null, () -> {
        });
        textFieldWidget3 = createInput(width / 2, 60, 40, 20, null, () -> {
        });
        textFieldWidget4 = createInput(width / 2 - 120, 100, 40, 20, null, () -> {
        });
        textFieldWidget5 = createInput(width / 2 - 60, 100, 40, 20, null, () -> {
        });
        textFieldWidget6 = createInput(width / 2, 100, 40, 20, null, () -> {
        });
        buttonWidget = createButton(width / 2 - 40, 120, 20, 20, "0", () -> {
            textFieldWidget.setText(textFieldWidget.getText() + "0");
        });
        buttonWidget2 = createButton(width / 2 - 20, 100, 20, 20, "2", () -> {
            textFieldWidget.setText(textFieldWidget.getText() + "2");
        });
        buttonWidget3 = createButton(width / 2, 100, 20, 20, "3", () -> {
            textFieldWidget.setText(textFieldWidget.getText() + "3");
        });
        buttonWidget1 = createButton(width / 2 - 40, 100, 20, 20, "1", () -> {
            textFieldWidget.setText(textFieldWidget.getText() + "1");
        });
        buttonWidget6 = createButton(width / 2 - 20, 80, 20, 20, "6", () -> {
            textFieldWidget.setText(textFieldWidget.getText() + "6");
        });
        buttonWidget4 = createButton(width / 2, 80, 20, 20, "4", () -> {
            textFieldWidget.setText(textFieldWidget.getText() + "4");
        });
        buttonWidget5 = createButton(width / 2 - 40, 80, 20, 20, "5", () -> {
            textFieldWidget.setText(textFieldWidget.getText() + "5");
        });
        buttonWidget8 = createButton(width / 2 - 20, 60, 20, 20, "8", () -> {
            textFieldWidget.setText(textFieldWidget.getText() + "8");
        });
        buttonWidget9 = createButton(width / 2, 60, 20, 20, "9", () -> {
            textFieldWidget.setText(textFieldWidget.getText() + "9");
        });
        buttonWidget7 = createButton(width / 2 - 40, 60, 20, 20, "7", () -> {
            textFieldWidget.setText(textFieldWidget.getText() + "7");
        });
        buttonWidget10 = createButton(width / 2 + 20, 60, 20, 20, "-", () -> {
            textFieldWidget.setText(textFieldWidget.getText() + "-");
        });
        buttonWidget11 = createButton(width / 2 + 20, 80, 20, 20, "+", () -> {
            textFieldWidget.setText(textFieldWidget.getText() + "+");
        });
        buttonWidget12 = createButton(width / 2 + 20, 100, 20, 20, "*", () -> {
            textFieldWidget.setText(textFieldWidget.getText() + "*");
        });
        buttonWidget13 = createButton(width / 2 + 20, 120, 20, 20, "/", () -> {
            textFieldWidget.setText(textFieldWidget.getText() + "/");
        });
        buttonWidget14 = createButton(width / 2, 120, 20, 20, "=", () -> {
            textFieldWidget.setText(String.valueOf(evaluate(textFieldWidget.getText())));
        });
        buttonWidget15 = createButton(width / 2 - 40, 20, 80, 20, "end portal(coming never)", () -> {
        });
        textWidget = createText(width / 2 - 180, 60, 60, 20, "Overworld");
        textWidget2 = createText(width / 2 - 180, 100, 60, 20, "Nether");
        buttonWidget16 = createButton(width / 2 + 40, 20, 80, 20, "nether portal", () -> {nether = !nether;});
        textFieldWidget = createInput(width / 2 - 40, 40, 80, 20, null, () -> {});
        createButton(width / 2 - 120, 20, 80, 20, "regular", () -> {
            regular = !regular;
            panel.visible = regular;
        });
        panel.add(buttonWidget);
        panel.add(buttonWidget1);
        panel.add(buttonWidget2);
        panel.add(buttonWidget3);
        panel.add(buttonWidget4);
        panel.add(buttonWidget5);
        panel.add(buttonWidget6);
        panel.add(buttonWidget7);
        panel.add(buttonWidget8);
        panel.add(buttonWidget9);
        panel.add(buttonWidget10);
        panel.add(buttonWidget11);
        panel.add(buttonWidget12);
        panel.add(buttonWidget13);
        panel.add(buttonWidget14);
        panel.add(textFieldWidget);
    }


    public ButtonWidget createButton(int x, int y, int width, int height, String buttonText, WidgetAction action) {
        ButtonWidget createdButton;
        createdButton = ButtonWidget.builder(Text.literal(buttonText), button -> {
                    action.perform();
                })
                .dimensions(x, y, width, height)
                .build();
        addDrawableChild(createdButton);
        return createdButton;
    }

    public TextWidget createText(int x, int y, int width, int height, String text) {
        TextWidget createdText = new TextWidget(x, y, width, height, Text.of(text), textRenderer);
        addDrawableChild(createdText);
        return createdText;
    }

    public TextFieldWidget createInput(int x, int y, int width, int height, String text, WidgetAction widgetAction) {
        TextFieldWidget createdInput = new TextFieldWidget(textRenderer, x, y, width, height, Text.literal(text));
        widgetAction.perform();
        addDrawableChild(createdInput);
        return createdInput;
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        panel.keyReleased(keyCode,scanCode,modifiers);
        return super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ENTER || keyCode == GLFW.GLFW_KEY_KP_ENTER) {
            textFieldWidget.setText(String.valueOf(evaluate(textFieldWidget.getText())));
        }
        panel.keyPressed(keyCode,scanCode,modifiers);
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        panel.mouseClicked(mouseX,mouseY,button);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        panel.mouseDragged(mouseX,mouseY,button,deltaX,deltaY);
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (regular) {
            context.drawTexture(texture, panel.getX() - 5, panel.getY() - 5, 0, 0, panel.getWidth()+7, panel.getHeight()+7);
        }
        if (nether) {
            context.drawTexture(texture, x1 - 5, y1 - 5, 0, 0, 310, 80);
        }
        panel.render(context,mouseX,mouseY,delta);
        super.render(context, mouseX, mouseY, delta);
    }
}

