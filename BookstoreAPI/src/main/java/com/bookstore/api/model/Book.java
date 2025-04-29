
package com.bookstore.api.model;

public class Book {
    private String title;
    private int authorId;
    private String isbn;
    private int publicationYear;
    private double price;
    private int stock;
    
    // Default constructor
    public Book() {}
    
    // Parameterized constructor
    public Book(String title, int authorId, String isbn, int publicationYear, double price, int stock) {
        this.title = title;
        this.authorId = authorId;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.price = price;
        this.stock = stock;
    }

    // Getters and setters
    
    public String getTitle() { 
        return title; 
    }
    
    public void setTitle(String title) {
        this.title = title; 
    }
    
    public int getAuthorId() { 
        return authorId; 
    }
    
    public void setAuthorId(int authorId) { 
        this.authorId = authorId; 
    }
    
    public String getIsbn() { 
        return isbn; 
    }
    
    public void setIsbn(String isbn) { 
        this.isbn = isbn; 
    }
    
    public int getPublicationYear() { 
        return publicationYear; 
    }
    
    public void setPublicationYear(int publicationYear) { 
        this.publicationYear = publicationYear; 
    }
    
    public double getPrice() { 
        return price; 
    }
    
    public void setPrice(double price) { 
        this.price = price; 
    }
    
    public int getStock() { 
        return stock; 
    }
    
    public void setStock(int stock) { 
        this.stock = stock; 
    }
}
