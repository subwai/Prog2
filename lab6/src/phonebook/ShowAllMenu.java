package phonebook;
import javax.swing.*;
import java.awt.event.*;
import java.util.Set;

@SuppressWarnings("serial")
public class ShowAllMenu extends JMenuItem implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;
	
	public ShowAllMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Show all");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e) {
		 StringBuilder sb = new StringBuilder("All:\n");
		 for(String name : phoneBook.names()){
			 sb.append(name + ":\n");
			 for(String number: phoneBook.findNumber(name)){
				 sb.append("\t" + number);
			 }
			 sb.append("\n");
		 }
		 gui.setText(sb.toString());
	 }
}
