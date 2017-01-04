import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.BorderLayout;

public class Interface extends JFrame implements ActionListener{
		private static ArrayList<String> userName = new ArrayList<String>();//will store user names from text file
		private static ArrayList<String> passwords = new ArrayList<String>();//stores all the passwords
        public static ArrayList<User> users = new ArrayList<User>();//create arraylists of books and users
        public static ArrayList<Book> books = new ArrayList<Book>();
        JLabel lblTopFive, lblTopFour,lblTopThree,lblTopTwo,lblTopOne;//labels for top novels by average rating
        JLabel lblRandom1,lblRandom2,lblRandom3,lblRandom4,lblRandom5,lblRandom6,lblRandom7,lblRandom8,lblRandom9,lblRandom10;//label 
        private JLabel Lbluser;
        private JPanel searchRating;
        private JPanel panelReccomendation;
        private JLabel lblLblsearchrating;
        private JLabel lblBookName;
        private JTextField txtSearchBook;
        private JButton btnSearch;
        private JLabel lblAverageRating;
        private JLabel lblReccomendationsForYou;
        private JLabel lblRec1;
        private JLabel lblRec2;
        private JLabel lblRec3;
        private JLabel lblRec4;
        private JLabel lblRec5;
        private JLabel lblRec6;
        private JLabel lblRec7;
        private JLabel lblRec8;
        private JLabel lblRec9;
        private JLabel lblRec10;
        private JPanel panelChanges;
        private JPanel panelChangeRate;
        private JLabel lblChangeRating;
        private JLabel lblBookN;
        private JTextField textFieldName;
        private JLabel lblRating;
        private JTextField textFieldRating;
        private JPanel panelAddNovel;
        private JLabel lblAddBook;
        private JLabel lblBook2;
        private JTextField textFieldName2;
        private JLabel lblRating2;
        private JTextField textFieldRating2;
        private JButton btnOk;
        private JButton btnOK2;
        private static int id;
        public static void main(String[]args) throws IOException, InterruptedException{
                getBookInfo();
                getUserRatings();               
                Password pass = new Password(users);
                int userIndex = -1;
                int x =0;
                pass.setFocusable(false);
                while(userIndex == -1){
                	userIndex = pass.getUser();
                	pass.repaint();//needed for the variable update
                }
                Thread.sleep(200);//sleep so the user can view the message
                pass.dispose();//dispose of and close pass
                pass.setVisible(false);
                id = userIndex;
                if(userIndex == -2){
                	String newUser = JOptionPane.showInputDialog("Enter the new user name. They will be prompted for a password upon their first login");
                	users.add(new User(newUser));//add the new user to the system
                	for(int z =0; z< books.size(); z++){
                		users.get(users.size()-1).addRating(0, books.get(z));//add a zero for everybook initially
                		
                	}
                	writeToFile();//write the changes to file
                }else{//if not admin
                	Interface ints = new Interface(userIndex);
                }
               
        }
		public Interface(int user){
			super("Book Review");
			getContentPane().setBackground(Color.green);
			getContentPane().setLayout(new GridLayout(0, 3, 0, 0));
			
			JPanel panel = new JPanel();
			getContentPane().add(panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			
			Lbluser = new JLabel("User: " + users.get(user).getName());
			Lbluser.setBackground(Color.GREEN);
			Lbluser.setFont(new Font("Comic Sans MS", Font.BOLD, 19));
			Lbluser.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblLbluser = new GridBagConstraints();
			gbc_lblLbluser.insets = new Insets(0, 0, 5, 0);
			gbc_lblLbluser.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblLbluser.gridx = 0;
			gbc_lblLbluser.gridy = 0;
			panel.add(Lbluser, gbc_lblLbluser);
			
			searchRating = new JPanel();
			GridBagConstraints gbc_searchRating = new GridBagConstraints();
			gbc_searchRating.weighty = 1.0;
			gbc_searchRating.insets = new Insets(0, 0, 5, 0);
			gbc_searchRating.fill = GridBagConstraints.BOTH;
			gbc_searchRating.gridx = 0;
			gbc_searchRating.gridy = 1;
			panel.add(searchRating, gbc_searchRating);
			searchRating.setLayout(new GridLayout(5, 0, 0, 0));
			
			lblLblsearchrating = new JLabel("Search Average Rating");
			lblLblsearchrating.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
			lblLblsearchrating.setHorizontalAlignment(SwingConstants.CENTER);
			searchRating.add(lblLblsearchrating);
			
			lblBookName = new JLabel("Book Name:");
			lblBookName.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
			lblBookName.setHorizontalAlignment(SwingConstants.CENTER);
			searchRating.add(lblBookName);
			
			txtSearchBook = new JTextField();
			searchRating.add(txtSearchBook);
			txtSearchBook.setColumns(10);
			
			btnSearch = new JButton("Search");
			btnSearch.addActionListener(this);
			searchRating.add(btnSearch);
			
			lblAverageRating = new JLabel("");
			lblAverageRating.setHorizontalAlignment(SwingConstants.CENTER);
			searchRating.add(lblAverageRating);
			
			panelReccomendation = new JPanel();
			GridBagConstraints gbc_panelReccomendation = new GridBagConstraints();
			gbc_panelReccomendation.gridheight = 2;
			gbc_panelReccomendation.weighty = 3.0;
			gbc_panelReccomendation.insets = new Insets(0, 0, 5, 0);
			gbc_panelReccomendation.fill = GridBagConstraints.BOTH;
			gbc_panelReccomendation.gridx = 0;
			gbc_panelReccomendation.gridy = 2;
			panel.add(panelReccomendation, gbc_panelReccomendation);
			panelReccomendation.setLayout(new GridLayout(11, 0, 0, 0));
			
			
			lblReccomendationsForYou = new JLabel("Reccomendations For You!");
			lblReccomendationsForYou.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
			lblReccomendationsForYou.setHorizontalAlignment(SwingConstants.CENTER);
			panelReccomendation.add(lblReccomendationsForYou);
			
			lblRec1 = new JLabel("");
			lblRec1.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblRec1.setHorizontalAlignment(SwingConstants.CENTER);
			panelReccomendation.add(lblRec1);
			
			lblRec2 = new JLabel("");
			lblRec2.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblRec2.setHorizontalAlignment(SwingConstants.CENTER);
			panelReccomendation.add(lblRec2);
			
			lblRec3 = new JLabel("");
			lblRec3.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblRec3.setHorizontalAlignment(SwingConstants.CENTER);
			panelReccomendation.add(lblRec3);
			
			lblRec4 = new JLabel("");
			lblRec4.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblRec4.setHorizontalAlignment(SwingConstants.CENTER);
			panelReccomendation.add(lblRec4);
			
			lblRec5 = new JLabel("");
			lblRec5.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblRec5.setHorizontalAlignment(SwingConstants.CENTER);
			panelReccomendation.add(lblRec5);
			
			lblRec6 = new JLabel("");
			lblRec6.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblRec6.setHorizontalAlignment(SwingConstants.CENTER);
			panelReccomendation.add(lblRec6);
			
			lblRec7 = new JLabel("");
			lblRec7.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblRec7.setHorizontalAlignment(SwingConstants.CENTER);
			panelReccomendation.add(lblRec7);
			
			lblRec8 = new JLabel("");
			lblRec8.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblRec8.setHorizontalAlignment(SwingConstants.CENTER);
			panelReccomendation.add(lblRec8);
			
			lblRec9 = new JLabel("");
			lblRec9.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblRec9.setHorizontalAlignment(SwingConstants.CENTER);
			panelReccomendation.add(lblRec9);
			
			lblRec10 = new JLabel("");
			lblRec10.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblRec10.setHorizontalAlignment(SwingConstants.CENTER);
			panelReccomendation.add(lblRec10);
			
			JPanel panelTop = new JPanel();
			getContentPane().add(panelTop);
			panelTop.setLayout(new GridLayout(2, 0, 0, 0));
			
			JPanel panelT = new JPanel();
			panelTop.add(panelT);
			panelT.setLayout(new GridLayout(6, 0, 0, 0));
			
			JLabel lblTopNovels = new JLabel("Top 5 Novels");
			lblTopNovels.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
			lblTopNovels.setHorizontalAlignment(SwingConstants.CENTER);
			panelT.add(lblTopNovels);
			
			lblTopOne = new JLabel("");
			lblTopOne.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblTopOne.setHorizontalAlignment(SwingConstants.CENTER);
			panelT.add(lblTopOne);
			
			lblTopTwo = new JLabel("");
			lblTopTwo.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblTopTwo.setHorizontalAlignment(SwingConstants.CENTER);
			panelT.add(lblTopTwo);
			
			lblTopThree = new JLabel("");
			lblTopThree.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblTopThree.setHorizontalAlignment(SwingConstants.CENTER);
			panelT.add(lblTopThree);
			
			lblTopFour = new JLabel("");
			lblTopFour.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblTopFour.setHorizontalAlignment(SwingConstants.CENTER);
			panelT.add(lblTopFour);
			
			lblTopFive = new JLabel("");
			lblTopFive.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblTopFive.setHorizontalAlignment(SwingConstants.CENTER);
			panelT.add(lblTopFive);
			
			updateTopNovels();//method that will calculate and find the top novels
			
			JPanel panelR = new JPanel();
			panelTop.add(panelR);
			panelR.setLayout(new GridLayout(11, 0, 0, 0));
			
			JLabel lblRandomNovel = new JLabel("Try Out These Random Picks!");
			lblRandomNovel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
			lblRandomNovel.setHorizontalAlignment(SwingConstants.CENTER);
			panelR.add(lblRandomNovel);
			
			lblRandom1 = new JLabel("");
			lblRandom1.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblRandom1.setHorizontalAlignment(SwingConstants.CENTER);
			panelR.add(lblRandom1);
			
			lblRandom2 = new JLabel("");
			lblRandom2.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblRandom2.setHorizontalAlignment(SwingConstants.CENTER);
			panelR.add(lblRandom2);
			
			lblRandom3 = new JLabel("");
			lblRandom3.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblRandom3.setHorizontalAlignment(SwingConstants.CENTER);
			panelR.add(lblRandom3);
			
			lblRandom4 = new JLabel("");
			lblRandom4.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblRandom4.setHorizontalAlignment(SwingConstants.CENTER);
			panelR.add(lblRandom4);
			
			lblRandom5 = new JLabel("");
			lblRandom5.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblRandom5.setHorizontalAlignment(SwingConstants.CENTER);
			panelR.add(lblRandom5);
			
			lblRandom6 = new JLabel("");
			lblRandom6.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblRandom6.setHorizontalAlignment(SwingConstants.CENTER);
			panelR.add(lblRandom6);
			
			lblRandom7 = new JLabel("");
			lblRandom7.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblRandom7.setHorizontalAlignment(SwingConstants.CENTER);
			panelR.add(lblRandom7);
			
			lblRandom8 = new JLabel("");
			lblRandom8.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblRandom8.setHorizontalAlignment(SwingConstants.CENTER);
			panelR.add(lblRandom8);
			
			lblRandom9 = new JLabel("");
			lblRandom9.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblRandom9.setHorizontalAlignment(SwingConstants.CENTER);
			panelR.add(lblRandom9);
			
			lblRandom10 = new JLabel("");
			lblRandom10.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
			lblRandom10.setHorizontalAlignment(SwingConstants.CENTER);
			updateRandomNovel();
			
			
			
			getSuggestions(user);
			panelR.add(lblRandom10);
			
			panelChanges = new JPanel();
			getContentPane().add(panelChanges);
			panelChanges.setLayout(new GridLayout(2, 0, 0, 0));
			
			panelChangeRate = new JPanel();
			panelChanges.add(panelChangeRate);
			panelChangeRate.setLayout(new GridLayout(6, 1, 0, 0));
			
			lblChangeRating = new JLabel("Add/Change Novel Rating");
			lblChangeRating.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
			lblChangeRating.setHorizontalAlignment(SwingConstants.CENTER);
			panelChangeRate.add(lblChangeRating);
			
			lblBookN = new JLabel("Book Name:");
			lblBookN.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
			lblBookN.setHorizontalAlignment(SwingConstants.CENTER);
			panelChangeRate.add(lblBookN);
			
			textFieldName = new JTextField();
			panelChangeRate.add(textFieldName);
			textFieldName.setColumns(10);
			
			lblRating = new JLabel("Rating:");
			lblRating.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
			lblRating.setHorizontalAlignment(SwingConstants.CENTER);
			panelChangeRate.add(lblRating);
			
			textFieldRating = new JTextField();
			panelChangeRate.add(textFieldRating);
			textFieldRating.setColumns(10);
			
			btnOk = new JButton("OK");
			btnOk.addActionListener(this);
			panelChangeRate.add(btnOk);
			
			panelAddNovel = new JPanel();
			panelChanges.add(panelAddNovel);
			panelAddNovel.setLayout(new GridLayout(6, 0, 0, 0));
			
			lblAddBook = new JLabel("Add New Book");
			lblAddBook.setHorizontalAlignment(SwingConstants.CENTER);
			lblAddBook.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
			panelAddNovel.add(lblAddBook);
			
			lblBook2 = new JLabel("Book Name,Author");
			lblBook2.setHorizontalAlignment(SwingConstants.CENTER);
			lblBook2.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
			panelAddNovel.add(lblBook2);
			
			textFieldName2 = new JTextField();
			textFieldName2.setColumns(10);
			panelAddNovel.add(textFieldName2);
			
			lblRating2 = new JLabel("Rating:");
			lblRating2.setHorizontalAlignment(SwingConstants.CENTER);
			lblRating2.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
			panelAddNovel.add(lblRating2);
			
			textFieldRating2 = new JTextField();
			textFieldRating2.setColumns(10);
			panelAddNovel.add(textFieldRating2);
			
			btnOK2 = new JButton("OK");
			btnOK2.addActionListener(this);
			panelAddNovel.add(btnOK2);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//default close operation and frame size
			setSize(700,700);
			setVisible(true);
        	
        }
        private void getSuggestions(int user) {//THE SUGGESTION ALGORITHM WILL CONSIDER ALL USER'S RATING USING A WEIGHTED AVERAGE 
			int sum[] = sumofMultiply(user);
			rateBooks(user, sum);
			
			
		}
		private int[] sumofMultiply(int user) {//will multiply the ratings of each book of the current user with every other user and add those numbers--finds similarity between each person
			int[] sum = new int[users.size()];//size -1 bc the current user is not included
			for(int x = 0; x< users.size(); x++){
				sum[x] =0;//assign values to all index
			}
			for(int x =0; x< users.size(); x++){//go through all the users
				
				for(int y=0; y< books.size(); y++){//every book of every user
					sum[x] = sum[x] + users.get(x).getRating(y)*users.get(user).getRating(y);
				}
				
			}
			return sum;//return the sum
		}
		private void rateBooks(int user, int[] sum) {//multiply books a user read by their own sum number calculated by rateBooks then add the number for each book
			int[] bookSum = new int[books.size()];
			for(int x = 0; x< books.size(); x++){
				bookSum[x] =0;//assign values to all index
			}
			for(int x =0; x< books.size(); x++){//go through all the books
				for(int y=0; y< users.size(); y++){//go through all the books
					if(y != user){//dont do the process if the current user is the user searching for recommendations
						bookSum[x] = bookSum[x] + users.get(y).getRating(x)*sum[y];//multiply the users sum(calculated in sumofMultply in relation to searching user) with the current books rarting
					}
				}
			}
			sortBooks(bookSum, user);
			
		}
		private void sortBooks(int[] bookSum, int user) {
			String[] bookNames = new String[books.size()];
			for(int x =0; x< bookNames.length; x++){
				bookNames[x] = books.get(x).getName();//get the name of the book at x and give it to bookNames
			}
			for(int x =0; x< bookNames.length; x++){//regular bubble sort will sort in descending ratings
				for(int i =0; i< bookNames.length-1; i++){
					if(bookSum[i+1] > bookSum[i]){
                        String t = bookNames[i];
                        int ti = bookSum[i];
                        bookNames[i] = bookNames[i+1];
                        bookSum[i] = bookSum[i+1];
                        bookNames[i+1] = t;
                        bookSum[i+1]= ti;
					}
				}
			}
			writeRecs(bookNames, user);
			
		}
		private void writeRecs(String[] bookNames, int user) {
			ArrayList<String> top10 = new ArrayList<String>();
			for(int x =0; x< bookNames.length; x++){
				if(top10.size()==10){//if 1- books have been gathered break the loop
					break;
				}
				if(users.get(user).getRating(bookNames[x]) == 0){//only if the user has not read the book add it to the ratings
					top10.add(bookNames[x]);
				}
			}
			try{
			lblRec1.setText(top10.get(0));//set the text to the reccomended books
			lblRec2.setText(top10.get(1));
			lblRec3.setText(top10.get(2));
			lblRec4.setText(top10.get(3));
			lblRec5.setText(top10.get(4));
			lblRec6.setText(top10.get(5));
			lblRec7.setText(top10.get(6));
			lblRec8.setText(top10.get(7));
			lblRec9.setText(top10.get(8));
			lblRec10.setText(top10.get(9));
			}catch(Exception e){
				
			}
		}
		private void updateRandomNovel() {
        	int random[] = new int[10];//will store the indexes for all the random novels
        	for(int x=0; x< 10; x++){
        			random[x] = (int) (Math.random() * (books.size()-1));//-1 for array indexing
        			for(int y =x-1; y >=0; y--){//check all books behind to ensure the same books are not listed twice
        				if(random[x] == random[y]){//if its equal to another one reset
        					random[x] = (int) (Math.random() * (books.size()-1));//-1 for array indexing
        					y=x-1;
        				}
        			}
        	}
        	
        	 lblRandom1.setText(books.get(random[0]).getName());
        	 lblRandom2.setText(books.get(random[1]).getName());
        	 lblRandom3.setText(books.get(random[2]).getName());
        	 lblRandom4.setText(books.get(random[3]).getName());
        	 lblRandom5.setText(books.get(random[4]).getName());
        	 lblRandom6.setText(books.get(random[5]).getName());
        	 lblRandom7.setText(books.get(random[6]).getName());
        	 lblRandom8.setText(books.get(random[7]).getName());
        	 lblRandom9.setText(books.get(random[8]).getName());
        	 lblRandom10.setText(books.get(random[9]).getName());
		}
		private void updateTopNovels() {
        	double[] ratings = {0,0,0,0,0};//all 5 ratings are 0
        	int[] index = new int[5];
			for(int x =0; x<books.size(); x++ ){
				double rating = books.get(x).averageRating();//will get the average rating of the current book
				if(rating > ratings[0]){//check if the ratings are greater than the top five and asssign those ratinsgs and store the index of those books
					ratings[0] = rating;
					index[0] = x;
				}else if(rating > ratings[1]){
					ratings[1] = rating;
					index[1] = x;
				}else if(rating > ratings[2]){
					ratings[2] = rating;
					index[2] = x;
				}else if(rating > ratings[3]){
					ratings[3] = rating;
					index[3] = x;
				}else if(rating > ratings[4]){
					ratings[4] = rating;
					index[4] = x;
				}
			}
			//set the text of the ratings
			lblTopOne.setText("1:   " + books.get(index[0]).getName());//set the text of the textfields with th appropriate ratings for each book
			lblTopTwo.setText("2:   " + books.get(index[1]).getName());
			lblTopThree.setText("3:   " + books.get(index[2]).getName());
			lblTopFour.setText("4:   " + books.get(index[3]).getName());
			lblTopFive.setText("5:   " + books.get(index[4]).getName());

		}
		private static void getUserRatings() throws IOException {//gets and assigns all the user ratings
                BufferedReader ratingsReader = new BufferedReader(new FileReader("ratings.txt"));
                String line;
                while((line = ratingsReader.readLine())!= null){
                	User use = new User();//create temp user
                    String[] info = line.split(" ");//split the line into its information
                    use.setName(info[0]);//set the name
                    for(int x =1; x<= books.size(); x++){//add the book ratings to the user
                    	use.addRating(Integer.parseInt(info[x]), books.get(x-1));
                    	books.get(x-1).addRating(Integer.parseInt(info[x]));//add the rating to the book
                    }
                    users.add(use);//add the new user to the user array                        
                }
        }
        private static void getBookInfo() throws IOException {//gets and assigns all the book information
                String line;
                BufferedReader bookReader = new BufferedReader(new FileReader("books55.txt"));
                while((line = bookReader.readLine())!= null){//read all the lines
                        String info[] = line.split(",");//split the current line at comma
                        Book book = new Book(info[1],info[0]);//create new book with info
                        books.add(book);//add the current book to the book array list
                }               
                bookReader.close();
        }
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnSearch){//if the search button was clicked
				int index = findBook(txtSearchBook.getText().toLowerCase());
				lblAverageRating.setText("" + books.get(index).averageRating());//get the average rating of the book being searched for and set the ratign on the appropriate text field
			}else if(e.getSource() == btnOk){				
				int bookIndex = findBook(textFieldName.getText().toLowerCase());//get index of book
				System.out.println(users.get(id).getRating(textFieldName.getText().toLowerCase()) + " " +textFieldName.getText().toLowerCase() );
				books.get(bookIndex).changeRating(id, Integer.parseInt(textFieldRating.getText()));
				users.get(id).changeRating(Integer.parseInt(textFieldRating.getText()), textFieldName.getText().toLowerCase());
				System.out.println(users.get(id).getRating(textFieldName.getText().toLowerCase()) + " " +textFieldName.getText().toLowerCase() );
				try {//update the file with the name changes
					writeToFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Your Rating Has Been Changed");
			}else if(e.getSource() == btnOK2){
				String info[] = textFieldName2.getText().split(",");
				books.add(new Book(info[0], info[1]));//add the new book to the database
				for(int x = 0; x<users.size(); x++){
					users.get(x).addRating(0, books.get(books.size()-1));//add the last book to the user ratings
				}
				users.get(id).changeRating(Integer.parseInt(textFieldRating2.getText()), books.get(books.size()-1));//change th rarting of the current user
				try {
					writeToFile();
					writetoBookFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Your new book and rating has been added");
			}
			
		}
		private void writetoBookFile() throws IOException {
			BufferedWriter write = new BufferedWriter(new FileWriter("books55.txt"));
			for(int y =0; y< books.size(); y++){//write all the books to the file once a new book is added
				write.write(books.get(y).getAuthor()+","+books.get(y).getName());
				if(y!=books.size()-1){//if on the last user dont create a new line
					write.newLine();//got to a new line
				}
			}
			write.close();
		}
		private static void writeToFile() throws IOException {
			BufferedWriter write = new BufferedWriter(new FileWriter("ratings.txt"));
			for(int x =0; x<users.size(); x++ ){
				write.write(users.get(x).getName());
				for(int y =0; y< books.size(); y++){//if on the last book dont create a new line
					write.write(" " + users.get(x).getRating(y));
				}
				if(x!=users.size()-1){
					write.newLine();//got to a new line
				}
			}
			write.close();
			
		}
		private int findBook(String lowerCase) {
			for(int x =0; x< books.size(); x++){
				if(books.get(x).getName().toLowerCase().equals(lowerCase)){//if the current book name in lowercase is the one being searched for
					return x;//return the index of the book
				}
			}
			return 0;
		}
		private void changePassword(String password, String name) throws IOException{
			getPasswords();
			int x;
			for(x =0; x< userName.size(); x++){
				if(users.get(id).getName().equals(name)){
					passwords.set(x, password);//changes the password for the current user to the one requested					
					break;
				}
			}
			writePassword(password);
			
		}
		private void writePassword(String password)throws IOException {
				BufferedWriter write = new BufferedWriter(new FileWriter("password.txt"));
				for(int x =0; x< userName.size(); x++){
					write.write(userName.get(x) + " " + passwords.get(x));//will write all the passwords again to the file
				}
			
		}
		private void getPasswords() throws IOException{
			BufferedReader read = new BufferedReader(new FileReader("Password.txt"));
			String line;
			while((line= read.readLine()) != null){
				String[] info= line.split(" ");
				userName.add(info[0]);
				passwords.add(info[1]);
				
				
			}
			
		}
}
