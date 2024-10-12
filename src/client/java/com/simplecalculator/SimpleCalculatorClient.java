package com.simplecalculator;

import com.simplecalculator.screen.CalculatorMenuA;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.Stack;

public class SimpleCalculatorClient implements ClientModInitializer {
	private static KeyBinding keyBinding;
	public static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger("SimpleCalculator");
	@Override
	public void onInitializeClient() {
		keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"calculator",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_I,
				"SimpleCalculator"
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (keyBinding.wasPressed()) {
				CalculatorMenuA configMenuScreen = new CalculatorMenuA(Text.literal("SimpleCalculator"));
				client.setScreen(configMenuScreen);
			}
		});
	}
	public static Double evaluate(String expression) {
		try {
			if (expression.isEmpty()) {
				throw new IllegalArgumentException("error");
			}

			expression = expression.replaceAll("\\s+", "");
			expression = expression.replace(',', '.');
			Stack<Double> values = new Stack<>();
			Stack<Character> operators = new Stack<>();

			for (int i = 0; i < expression.length(); i++) {
				char c = expression.charAt(i);
				if (Character.isDigit(c)) {
					StringBuilder sb = new StringBuilder();
					while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
						sb.append(expression.charAt(i++));
					}
					values.push(Double.parseDouble(sb.toString()));
					i--;
				} else if (c == '(') {
					operators.push(c);
				} else if (c == ')') {
					while (!operators.isEmpty() && operators.peek() != '(') {
						if (values.size() < 2) {
							throw new IllegalArgumentException("error");
						}
						values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
					}
					if (operators.isEmpty() || operators.peek() != '(') {
						throw new IllegalArgumentException("error");
					}
					operators.pop();
				} else if (isOperator(c)) {
					while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
						if (values.size() < 2) {
							throw new IllegalArgumentException("error");
						}
						values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
					}
					operators.push(c);
				} else {
					throw new IllegalArgumentException("error" + c);
				}
			}

			while (!operators.isEmpty()) {
				if (values.size() < 2) {
					throw new IllegalArgumentException("error");
				}
				values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
			}

			if (values.size() != 1) {
				throw new IllegalArgumentException("Некорректное выражение: остаток значений");
			}

			return values.pop();
		} catch (ArithmeticException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}



	private static boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}

	private static int precedence(char op) {
		if (op == '+' || op == '-') return 1;
		if (op == '*' || op == '/') return 2;
		return 0;
	}

	private static double applyOperator(char op, double b, double a) {
		switch (op) {
			case '+':
				return a + b;
			case '-':
				return a - b;
			case '*':
				return a * b;
			case '/':
				if (b == 0) {
					throw new ArithmeticException("Division by zero");
				}
				return a / b;
			default:
				return 0;
		}
	}
}