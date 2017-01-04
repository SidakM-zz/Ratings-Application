import java.util.ArrayList;

public class User {
        private String name;
        private ArrayList<Integer> ratings = new ArrayList<Integer>();
        private ArrayList<Book> books = new ArrayList<Book>();
        public User(){
                
        }
        public User(String name){
                this.name = name;
        }
        public void setName(String name){//getters and setters for the name
                this.name = name;
        }
        public String getName(){//returns user name
                return this.name;
        }
        public void addRating(int rating, Book book){// add a rating and a book1
                this.ratings.add(rating);
                this.books.add(book);
        }
        public int getRating(Book book){//get the rating by book
                for(int x =0; x< books.size(); x++){//go through all the books
                        if(books.get(x).getName().equals(book.getName())){//if the current books name is the same as the one being changed
                                return ratings.get(x);//return the rating for that book
                        }
                }
                return (Integer) null;//if not found return null
        }
        public int getRating(String book){//get the rating by book
            for(int x =0; x< books.size(); x++){//go through all the books
                    if(books.get(x).getName().toLowerCase().equals(book.toLowerCase())){//if the current books name is the same as the one being changed
                            return ratings.get(x);//return the rating for that book
                    }
            }
            return (Integer) null;//if not found return null
    }
        public int getRating(int index){//get the rating by book
                            return ratings.get(index);//return the rating for that book
        }
        public void changeRating(int rating, Book book){
                for(int x =0; x< books.size(); x++){//go through all the books
                        if(books.get(x).getName().equals(book.getName())){//if the current books name is the same as the one being changed
                                ratings.set(x, rating);//change the rating of the book 
                        }
                }
                
        }
        public void changeRating(int rating, String book){
            for(int x =0; x< books.size(); x++){//go through all the books
                    if(books.get(x).getName().toLowerCase().equals(book)){//if the current books name is the same as the one being changed
                            ratings.set(x, rating);//change the rating of the book 
                    }
            }
            
        }
}
