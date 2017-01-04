import java.util.ArrayList;

public class Book {
        
        private String name, author;
        private ArrayList<Integer> ratings = new ArrayList<Integer>();
        public Book(String name, String author){//when creating a book name and author can be provided
                this.name = name;               
                this.author = author;
        }
        public Book(){//or a blank book can be created
                
        }
        public void addRating(int rating){//will add a rating for the book
        	ratings.add(rating);
        }
        public double averageRating(){
        	double total = 0;
        	int x =0;
        	int t =0;
        	while(x<ratings.size()){//go thorugh all the ratings
        		if(ratings.get(x)!= 0){//only if ratings are not 0 add them
        			total+=ratings.get(x);//add them up
        			t++;
        		}
        		x++;
        	}
        	return total/(t);//return the average rating(x+1 bc the indexing begins at 0)
        }
        public void setName(String name){//getters and setters for the name
                this.name = name;
        }
        public String getName(){
                return this.name;
        }
        public void setAuthor(String author){//getters and setters for the author
                this.author = author;
        }
        public String getAuthor(){
                return this.author;
        }
        public void changeRating(int index, int rating){
        	ratings.set(index, rating);//change the rating of that user
        }
        
        
}
