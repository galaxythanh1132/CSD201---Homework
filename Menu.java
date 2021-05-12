package csd.book.list;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class Menu
{

    private boolean running = true;
    private Scanner scanner;
    private int selection;
    private LinkedList<Book> blist = new LinkedList<>();

    public int printMenu()
    {
        scanner = new Scanner(System.in);
        System.out.println("---- Book Management ----\n"
                + "1. Add a book\n"
                + "2. Find a book\n"
                + "3. Update price of book\n"
                + "4. Exit\n"
                + "Please choose an option: ");

        selection = scanner.nextInt();
        return selection;
    }

    // Main loop
    public void menuLoop()
    {

        do
        {
            //Choose operations
            switch (printMenu())
            {
                case 1:
                    Book new_book = new Book();
                    new_book.inputBook();
                    blist.add(new_book);
                    break;
                case 2:
                    find();
                    break;

                case 3:
                    updatePrice();
                    break;
                case 4:
                    System.out.println("Thanks for using my application!");
                    savelist();
                    running = false;

                default:
                    System.out.println("Please enter a different number");
            }

        } while (running);

    }

    //Operation to find a book by name
    public void find()
    {
        scanner = new Scanner(System.in);
        int count = 0;
        String temp;

        System.out.println("Enter your book title: ");
        temp = scanner.next();

        for (Book b : blist)
        {
            if (b.getTitle().toLowerCase().contains(temp))
            {
                System.out.println(b.getTitle());
                count++;
            }

        }
        if (count == 0)
        {
            System.out.println("There are no results.");
        }
    }

    //Operation to update a book price, search by ID
    public void updatePrice()
    {
        scanner = new Scanner(System.in);
        int temp_id;
        float temp_price;

        System.out.println("Enter ID of the book that you want to change price of: ");
        temp_id = scanner.nextInt();

        for (Book b : blist)
        {
            if (b.getId() == temp_id)
            {
                System.out.println("Enter the new price of this book: ");
                temp_price = scanner.nextFloat();
                b.setPrice(temp_price);
            }

        }
    }
    
    //An attempt to save list of books
    public void savelist()
    {
        try
        {
           FileOutputStream os = new FileOutputStream("Booklist.txt");
           ObjectOutputStream obo = new ObjectOutputStream(os);
           Iterator<Book> it = blist.iterator();
           while(it.hasNext())
           {
               Book temp = it.next();
               obo.writeObject(temp);
           }
           
           os.close();
           obo.close();
        }catch(IOException e)
        {
            
        }
        
    }

}
