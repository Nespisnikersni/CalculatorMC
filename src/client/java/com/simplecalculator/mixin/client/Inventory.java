package com.simplecalculator.mixin.client;

import com.simplecalculator.SimpleCalculatorClient;
import com.simplecalculator.screen.CalculatorMenuA;
import com.simplecalculator.screen.WidgetAction;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

import static com.simplecalculator.SimpleCalculatorClient.evaluate;

@Mixin(InventoryScreen.class)
public abstract class Inventory extends Screen {
    protected Inventory(Text title) {
        super(title);
    }

    private TextFieldWidget textFieldWidget;
    private Identifier texture = new Identifier("minecraft", "textures/gui/demo_background.png");

    private ButtonWidget buttonWidget, buttonWidget1, buttonWidget2, buttonWidget3, buttonWidget4, buttonWidget5, buttonWidget6, buttonWidget7, buttonWidget8, buttonWidget9, buttonWidget10, buttonWidget11, buttonWidget12, buttonWidget13, buttonWidget14, buttonWidget15, buttonWidget16, buttonWidget20, buttonWidget21, buttonWidget22, buttonWidget23;
    private List<ButtonWidget> widgets = new ArrayList<>();
    private List<TextWidget> textNether = new ArrayList<>();
    private List<ButtonWidget> buttonNether = new ArrayList<>();
    private List<TextFieldWidget> inputNether = new ArrayList<>();
    private Boolean regular = false, nether = false;
    private TextWidget textWidget, textWidget2;
    private TextFieldWidget textFieldWidget1, textFieldWidget2, textFieldWidget3, textFieldWidget4, textFieldWidget5, textFieldWidget6;
    int xx, yy, x1, y1;

    @Inject(method = "init", at = @At("HEAD"))
    private void inventory(CallbackInfo ci) {
        int x, y, z;
        x = (int) MinecraftClient.getInstance().player.capeX;
        y = (int) MinecraftClient.getInstance().player.capeY;
        z = (int) MinecraftClient.getInstance().player.capeZ;
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
            SimpleCalculatorClient.LOGGER.info("end portal");
        });
        buttonNether.add(buttonWidget20);
        buttonNether.add(buttonWidget21);
        buttonNether.add(buttonWidget22);
        buttonNether.add(buttonWidget23);
        inputNether.add(textFieldWidget1);
        inputNether.add(textFieldWidget2);
        inputNether.add(textFieldWidget3);
        inputNether.add(textFieldWidget4);
        inputNether.add(textFieldWidget5);
        inputNether.add(textFieldWidget6);
        textWidget = createText(width / 2 - 180, 60, 60, 20, "Overworld");
        textWidget2 = createText(width / 2 - 180, 100, 60, 20, "Nether");
        textNether.add(textWidget);
        textNether.add(textWidget2);
        buttonWidget16 = createButton(width / 2 + 40, 20, 80, 20, "nether portal", () -> {
            nether = !nether;
            if (nether) {
                for (TextFieldWidget widget : inputNether) {
                    widget.active = true;
                    widget.visible = true;
                }
                for (ButtonWidget widget : buttonNether) {
                    widget.active = true;
                    widget.visible = true;
                }
                for (TextWidget widget : textNether) {
                    widget.active = true;
                    widget.visible = true;
                }
            } else {
                for (TextFieldWidget widget : inputNether) {
                    widget.active = false;
                    widget.visible = false;
                }
                for (ButtonWidget widget : buttonNether) {
                    widget.active = false;
                    widget.visible = false;
                }
                for (TextWidget widget : textNether) {
                    widget.active = false;
                    widget.visible = false;
                }
            }
        });
        if (nether) {
            for (TextFieldWidget widget : inputNether) {
                widget.active = true;
                widget.visible = true;
            }
            for (ButtonWidget widget : buttonNether) {
                widget.active = true;
                widget.visible = true;
            }
            for (TextWidget widget : textNether) {
                widget.active = true;
                widget.visible = true;
            }
        } else {
            for (TextFieldWidget widget : inputNether) {
                widget.active = false;
                widget.visible = false;
            }
            for (ButtonWidget widget : buttonNether) {
                widget.active = false;
                widget.visible = false;
            }
            for (TextWidget widget : textNether) {
                widget.active = false;
                widget.visible = false;
            }
        }
        textFieldWidget = createInput(width / 2 - 40, 40, 80, 20, null, () -> {
        });
        widgets.add(buttonWidget);
        widgets.add(buttonWidget1);
        widgets.add(buttonWidget2);
        widgets.add(buttonWidget3);
        widgets.add(buttonWidget4);
        widgets.add(buttonWidget5);
        widgets.add(buttonWidget6);
        widgets.add(buttonWidget7);
        widgets.add(buttonWidget8);
        widgets.add(buttonWidget9);
        widgets.add(buttonWidget10);
        widgets.add(buttonWidget11);
        widgets.add(buttonWidget12);
        widgets.add(buttonWidget13);
        widgets.add(buttonWidget14);
        createButton(width / 2 - 120, 20, 80, 20, "regular", () -> {
            regular = !regular;
            if (regular) {
                for (ButtonWidget widget : widgets) {
                    widget.active = true;
                    widget.visible = true;
                    textFieldWidget.active = true;
                    textFieldWidget.visible = true;
                }
            } else {
                for (ButtonWidget widget : widgets) {
                    widget.active = false;
                    widget.visible = false;
                    textFieldWidget.active = false;
                    textFieldWidget.visible = false;
                }
            }
        });
        if (regular) {
            for (ButtonWidget widget : widgets) {
                widget.active = true;
                widget.visible = true;
                textFieldWidget.active = true;
                textFieldWidget.visible = true;
            }
        } else {
            for (ButtonWidget widget : widgets) {
                widget.active = false;
                widget.visible = false;
                textFieldWidget.active = false;
                textFieldWidget.visible = false;
            }
        }
    }

    @Inject(method = "render", at = @At("HEAD"))
    public void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        MatrixStack matrix = context.getMatrices();
        matrix.push();
        matrix.translate(0,0,1);
        xx = buttonWidget.getX();
        yy = buttonWidget.getY();
        x1 = textWidget.getX();
        y1 = textWidget.getY();
        buttonWidget.render(context,mouseX,mouseY,delta);
        buttonWidget1.setPosition(xx, yy - 20);
        buttonWidget2.setPosition(xx + 20, yy - 20);
        buttonWidget3.setPosition(xx + 40, yy - 20);
        buttonWidget4.setPosition(xx + 40, yy - 40);
        buttonWidget5.setPosition(xx, yy - 40);
        buttonWidget6.setPosition(xx + 20, yy - 40);
        buttonWidget7.setPosition(xx, yy - 60);
        buttonWidget8.setPosition(xx + 20, yy - 60);
        buttonWidget9.setPosition(xx + 40, yy - 60);
        buttonWidget10.setPosition(xx + 60, yy - 60);
        buttonWidget11.setPosition(xx + 60, yy - 40);
        buttonWidget12.setPosition(xx + 60, yy - 20);
        buttonWidget13.setPosition(xx + 60, yy);
        buttonWidget14.setPosition(xx + 40, yy);
        textFieldWidget.setPosition(xx, yy - 80);
        textWidget2.setPosition(x1, y1 + 40);
        textFieldWidget1.setPosition(x1 + 60, y1);
        textFieldWidget2.setPosition(x1 + 120, y1);
        textFieldWidget3.setPosition(x1 + 180, y1);
        textFieldWidget4.setPosition(x1 + 60, y1 + 40);
        textFieldWidget5.setPosition(x1 + 120, y1 + 40);
        textFieldWidget6.setPosition(x1 + 180, y1 + 40);
        buttonWidget20.setPosition(x1 + 230, y1);
        buttonWidget21.setPosition(x1 + 230, y1 + 40);
        buttonWidget22.setPosition(x1 + 290, y1);
        buttonWidget23.setPosition(x1 + 290, y1 + 40);
        matrix.pop();
    }
    @Inject(method = "drawBackground",at = @At("TAIL"))
    private void drawBackground(DrawContext context, float delta, int mouseX, int mouseY,CallbackInfo info){
        drawBackgorund(context);
    }
    private void drawBackgorund(DrawContext context){
        if (regular) {
            context.drawTexture(texture, xx - 5, yy - 85, 0, 0, 90, 110);
        }
        if (nether) {
            context.drawTexture(texture, x1 - 5, y1 - 5, 0, 0, 310, 80);
        }
    }

    public ButtonWidget createButton(int x, int y, int width, int height, String buttonText, WidgetAction action) {
        ButtonWidget createdButton;
        createdButton = ButtonWidget.builder(Text.literal(buttonText), button -> {
            action.perform();
        }).dimensions(x, y, width, height).build();
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
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ENTER || keyCode == GLFW.GLFW_KEY_KP_ENTER) {
            textFieldWidget.setText(String.valueOf(evaluate(textFieldWidget.getText())));
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    double newX, newY, newCX, newCY;

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        newCX = mouseX - textFieldWidget.getX();
        newCY = mouseY - textFieldWidget.getY();
        newX = mouseX - textWidget.getX();
        newY = mouseY - textWidget.getY();
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (isMouseOverButton(mouseX, mouseY, textFieldWidget.getX(), textFieldWidget.getY(), 80, 100) && buttonWidget.active) {
            buttonWidget.setPosition((int) (mouseX - newCX), (int) (mouseY - newCY + 80));
        }
        if (isMouseOverButton(mouseX, mouseY, textWidget.getX(), textWidget.getY(), 300, 70) && textWidget.active) {
            textWidget.setPosition((int) (mouseX - newX), (int) (mouseY - newY));
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    private boolean isMouseOverButton(double mouseX, double mouseY, int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
        return mouseX >= buttonX && mouseX <= buttonX + buttonWidth && mouseY >= buttonY && mouseY <= buttonY + buttonHeight;
    }
}