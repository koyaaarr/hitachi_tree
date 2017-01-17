package NewTree;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){

				JFrame ct = new JFrame();
				ct.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ct.getContentPane().add(new TreePanel());
				ct.setSize(TreePanel.WIDTH, TreePanel.HEIGHT);
				ct.setLocationRelativeTo(null);
				ct.setAlwaysOnTop(true);
				ct.setVisible(true);
			}
		});
	}
}
