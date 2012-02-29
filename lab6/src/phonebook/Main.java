package phonebook;

import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
		String file = JOptionPane.showInputDialog("Enter savefile");
		if(file == null || file.isEmpty()){
			file = "save";
		}

		PhoneBook pb = new PhoneBook(file);
		
		new PhoneBookGUI(pb);
	}

}
