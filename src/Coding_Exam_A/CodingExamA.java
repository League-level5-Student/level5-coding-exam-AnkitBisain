package Coding_Exam_A;

import java.awt.Color;

import javax.swing.JOptionPane;

import org.jointheleague.graphical.robot.Robot;

public class CodingExamA {
	public static void main(String[] args) {
		/*
		 * Write a program that asks the user for three pieces of information. 1. How
		 * many robots 2. The color of the shapes 3. How many sides each shape will have
		 * 
		 * Once the information has been collected, the program will then make the
		 * requested number of robots each draw the requested shape in the requested
		 * color. The robots should execute at the same time so Threads will need to be
		 * used. Arrange the robots so that the shapes do not overlap. For full credit,
		 * define the Thread functions using lambdas.
		 * 
		 * See the Coding_Exam_A_Demo.jar for an example of what the finished product
		 * should look like.
		 */
		int rbots = Integer.parseInt(JOptionPane.showInputDialog(null, "How many robots?"));
		String color = JOptionPane.showInputDialog(null, "Red, Blue, or Green?");
		int sides = Integer.parseInt(JOptionPane.showInputDialog(null, "How many sides?"));
		Robot[] robots = new Robot[rbots];
		Thread[] t = new Thread[rbots];
		for (int i = 0; i < robots.length; i++) {
			robots[i] = new Robot(200 * (i + 1), 200);
			if (color.equalsIgnoreCase("green")) {
				robots[i].setPenColor(Color.GREEN);
			} else if (color.equalsIgnoreCase("red")) {
				robots[i].setPenColor(Color.RED);
			} else {
				robots[i].setPenColor(Color.BLUE);
			}
			robots[i].penDown();
		}
		for (int i = 0; i < robots.length; i++) {
			final int k = i;
			t[i] = new Thread(() -> {
				for (int j = 0; j < sides; j++) {
					robots[k].move(300 / sides);
					robots[k].turn(360 / sides);
				}
				robots[k].hide();
			});
		}
		for (int i = 0; i < t.length; i++) {
			t[i].start();
		}
	}

}
