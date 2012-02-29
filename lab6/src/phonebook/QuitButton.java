package phonebook;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class QuitButton extends JButton implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;

	public QuitButton(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Quit");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e) {
		 try {
			 ObjectOutputStream out =
				 new ObjectOutputStream(new FileOutputStream("save.bin"));
			 out.writeObject(phoneBook);
		 } catch (Exception ex) {
			 ex.printStackTrace();
			 System.exit(1);
		 }
		System.exit(0);
	 }
}
