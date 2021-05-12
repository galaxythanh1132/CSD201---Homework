package csd.book.list;

import java.io.Serializable;
import java.util.Scanner;

public class Book implements Serializable
{

    //Book attributes
    private String title;
    private int id;
    private float price;
    private String author;
    private Scanner scanner;

    //Input for Book
    public void inputBook()
    {
        scanner = new Scanner(System.in);
        System.out.print("Please enter the book ID: ");
        id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Please enter the title of the book: ");
        title = scanner.nextLine();
        System.out.print("Please enter the author of the book: ");
        author = scanner.nextLine();
        System.out.print("Please enter the price of the book: ");
        price = scanner.nextFloat();

    }
    
    public Book()
    {
        title = "";
        id = 0;
        price = 0;
        author = "";
                
    }
    
    public Book(String title, int id, float price, String author)
    {
        this.title = title;
        this.id = id;
        this.price = price;
        this.author = author;
    }

    //Getters & setters
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    
    @Override
    public String toString()
    {
        return id + " | " + title + " | " + author + " | " + price;
    }
    
}
