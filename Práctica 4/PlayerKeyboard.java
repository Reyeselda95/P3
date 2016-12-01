package model.io;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import model.exceptions.io.TetrisIOException;

public class PlayerKeyboard implements IPlayer {

	private JFrame frame;
	private ConcurrentLinkedQueue<Character> moves; // thread-safe queue!

	public PlayerKeyboard() {
		moves = new ConcurrentLinkedQueue<Character>();
		moves.add('I');

		frame = new JFrame("Tetris");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel(
				"<html>Make this window active in order to control "
						+ "the pieces with the keyboard. "
						+ "The current piece must be fixed before adding a new one. "
						+ " Valid keys are: "
						+ "[right arrow]: move right; "
						+ "[left arrow]: move left; "
						+ "[down arrow]: move down; "
						+ "[ctrl + right arrow]: rotate counterclockwise; "
						+ "[ctrl + left arrow]: rotate clockwise; "
						+ "[ctrl + down arrow]: 5-row fast drop; "
						+ "[space]: hard drop; "
						+ "[i], [j], [l], [o], [s], [t], [z]: add a new piece of "
						+ "the corresponding shape.</html>");
		label.setBorder(new EmptyBorder(7, 7, 7, 7));
		frame.getContentPane().add(label, BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(400, 250));
		frame.setFocusable(true);
		frame.requestFocusInWindow();
		frame.pack();
		frame.setVisible(true);

		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				switch (key) {
				case KeyEvent.VK_KP_LEFT:
				case KeyEvent.VK_LEFT:
					if ((e.getModifiers() & InputEvent.CTRL_MASK) == InputEvent.CTRL_MASK) {
						moves.add('↻');
					} else {
						moves.add('←');
					}
					break;
				case KeyEvent.VK_KP_RIGHT:
				case KeyEvent.VK_RIGHT:
					if ((e.getModifiers() & InputEvent.CTRL_MASK) == InputEvent.CTRL_MASK) {
						moves.add('↺');
					} else {
						moves.add('→');
					}
					break;
				case KeyEvent.VK_KP_DOWN:
				case KeyEvent.VK_DOWN:
					if ((e.getModifiers() & InputEvent.CTRL_MASK) == InputEvent.CTRL_MASK) {
						for (int i = 0; i < 5; i++) {
							moves.add('↓');
						}
					} else {
						moves.add('↓');
					}
					break;
				case KeyEvent.VK_SPACE:
					for (int i = 0; i < 20; i++) {
						moves.add('↓');
					}
					break;
				case KeyEvent.VK_I:
					moves.add('I');
					break;
				case KeyEvent.VK_J:
					moves.add('J');
					break;
				case KeyEvent.VK_L:
					moves.add('L');
					break;
				case KeyEvent.VK_O:
					moves.add('O');
					break;
				case KeyEvent.VK_S:
					moves.add('S');
					break;
				case KeyEvent.VK_T:
					moves.add('T');
					break;
				case KeyEvent.VK_Z:
					moves.add('Z');
					break;
				}
			}
		});
	}

	@Override
	public char nextMove() throws TetrisIOException {
		while (moves.isEmpty()) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		char currentMove = moves.poll();
		return currentMove;
	}

}
