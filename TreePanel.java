package NewTree;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.text.BadLocationException;


public class TreePanel extends JPanel {
	public static final int WIDTH = 960*2;
	public static final int HEIGHT = 540*2;
	private static final int width = 760*2;
	private static final int height = 400*2;
	private static final int SCROLL_SPEED = 400;

	private JPanel treePanel;//main panel
	private final String IMAGE_FILE = "tree.jpg";
	private final String TEXT_FILE = "group.txt";
	private static ArrayList<String> roll;//list to scroll
	private JTextPane text;
	private JScrollPane scroll;
	private static int rollIndex;

	public TreePanel(ArrayList<String> roll){
		//set this panel
		setLayout(null);
		setOpaque(false);

		//set background image
		treePanel = new ImagePanel(IMAGE_FILE);
		treePanel.setBounds(0, 0, WIDTH, HEIGHT);
		treePanel.setOpaque(false);

		//set text pane
		text = new JTextPane();
		text.setFont(new Font("メイリオ", Font.BOLD, 60));
		text.setForeground(Color.WHITE);
		text.setOpaque(false);

		//set scroll pane
		scroll = new JScrollPane(text);
		scroll.setBounds((WIDTH-width)/2, (HEIGHT-height)/2, width, height);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll);
		add(treePanel);

		//set list
		if(roll == null){
			this.roll = new ArrayList<String>();
			this.roll = (ArrayList<String>) getFileToList();
		}else this.roll = roll;


		//scroll list
		text.setText("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				addLineToScrollFromList(TreePanel.roll);
			}
		});

		hitachiTree();

	}

	public TreePanel(){
		this(null);
	}

	private List<String> getFileToList(){

		List<String> list = new ArrayList<>();
		InputStream is = getClass().getResourceAsStream(TEXT_FILE);
		try(BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))){
			//try(BufferedReader br = new BufferedReader(new InputStreamReader(is))){
			String line;
			while((line=br.readLine())!=null){
				for(String item:line.split(",")){
					list.add(item);
				}
			}
			return list;
		} catch (FileNotFoundException e) {
			System.err.println("Text File Not Found");
		} catch (IOException e) {
			System.err.println("IOError at Text");
		}
		return list;
	}

	private void addLineToScrollFromList(ArrayList<String> roll){
		Timer timer = new Timer(SCROLL_SPEED, new ActionListener(){
			int hi = 0;
			public void actionPerformed(ActionEvent e){
				try{
					String str = roll.get(hi);
					text.getDocument().insertString(text.getDocument().getLength(), "\n" + str, null);
					hi++;
					if(hi == roll.size()){
						hi = 0;
					}
				}catch (BadLocationException e1) {
				}
			}
		});
		timer.start();
	}

	private void hitachiTree() {
		Desktop desktop = Desktop.getDesktop();
		String uriString = "https://www.youtube.com/watch?v=XCWd5IgYGj8";
		try {
			URI uri = new URI(uriString);
			desktop.browse(uri);
		} catch (URISyntaxException e) {
		} catch (IOException e) {
		}

	}
}