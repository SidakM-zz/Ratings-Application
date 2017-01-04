import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import javax.swing.*;


public class Password extends JFrame implements ActionListener {
	private static ArrayList<String> userName = new ArrayList<String>();//will store user names from text file
	private static ArrayList<User> allUser = new ArrayList<User>();//holds the user list passed when this class created
	private static ArrayList<String> passwords = new ArrayList<String>();//stores all the passwords
	JButton enter, clear, addUser;//button
	JTextField userNames;
	JPasswordField password;//field for password
	JLabel user, pass;
	private int userIndex = -1;//set as negative one so interface can identify when changed
	boolean setClose = false;
	public int getUser(){
		return userIndex;
	}
	public Password(ArrayList<User> use) throws IOException{
		super("Enter your password");
		allUser = use;
		userNames = new JTextField("");
		password = new JPasswordField("");
		password.setEchoChar('*');//set the char to mask as
		user = new JLabel("Username: ");
		pass = new JLabel("Password: ");
		enter = new JButton("Enter");
		clear = new JButton("Clear");
		clear.addActionListener(this);
		enter.addActionListener(this);
		setLayout(new GridLayout(3,2));
		add(user);
		add(userNames);
		add(pass);
		add(password);
		add(clear);
		add(enter);
		setSize(400,200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	private void getPassword() throws IOException {
		BufferedReader read = new BufferedReader(new FileReader("password.txt"));
		String line;
		while((line = read.readLine()) != null){
			String[] info = line.split(",");
			userName.add(info[0]);//add user name and password
			passwords.add(info[1]);
		}		
		read.close();
	}
	
	public boolean checkPassword(ArrayList<User> use, String password, String user) throws IOException{
		boolean found = false;
		boolean correct = false;	
		
		for(int x = 0; x< userName.size(); x++){
				if(userName.get(x).equals(user)){//if the name is the same
					found = true;//set found is equal to true
					//System.out.println("found");
					if(passwords.get(x).equals(password)){
						correct = true;//and if the passwords match set correct to true
									//find the user index in the arraylist
						for(int u =0; u< use.size(); u++){
							if(use.get(u).getName().equals(user)){
								//System.out.println(u);
								userIndex = u;//pass the user index
							}
						}
					}
				}
		}//WORKS
		if(found == false){//if the wrong password add them to the database
			for(int x =0; x< use.size(); x++){
				if(use.get(x).getName().equals(user)){//if the user is in the database but does not alrady have a password
					writeNewPassword(password, user);//write the new password to file
					userIndex = x;//the user logging in is the user at x
					JOptionPane.showMessageDialog(null, "A password has been set for your account. Use this password the next time you log in.");
					return true;//return true
				}
			}
			JOptionPane.showMessageDialog(null, "The user is not in the system");
			return false;//otherwise they are not a user and return false
		}else{
			if(correct == true){//if correct pass then do
				//System.out.println("Correct Password");
				return true;
			}else{//if wrong password notify them of their wrong password
				JOptionPane.showMessageDialog(null, "Wrong password");
				return false;
			}
		}
		
		
	}
	
	private void writeNewPassword(String password, String user) throws IOException {
		BufferedWriter write = new BufferedWriter(new FileWriter("password.txt", true));//the true means that it will always append a password to the end of the file
		write.newLine();
		write.write(user + "," + password);//write the new user name to file
		write.close();
		//System.out.println(user + " " +password);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==(enter)){
			if(userNames.getText().equals("admin") && String.valueOf(password.getPassword()).equals("admin") ){//if its the admin logging in then
				userIndex = -2;
			}else{
			try {
				getPassword();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//send in users to get password file
			String us= userNames.getText();
			String pass = String.valueOf(password.getPassword());//get the password(char array) and convert to string
		
			try {
				boolean comp = checkPassword(allUser, pass, us);//check the validity of the password
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		}else{//otherwise if the clear button is pressed empty the text fields
			userNames.setText("");
			password.setText("");
		}
		
		
	}
	
}
