public class Book {
    private String bookName;

    private String author;

    public Book() {
        System.out.println("Book 构造方法");
    }

    public String getBookName() {
        System.out.println("getBookName");
        return bookName;
    }

    public void setBookName(String bookName) {
        System.out.println("setBookName");
        this.bookName = bookName;
    }

    public String getAuthor() {
        System.out.println("getAuthor");
        return author;
    }

    public void setAuthor(String author) {
        System.out.println("setAuthor");
        this.author = author;
    }

    public void printBookInfo() {
        System.out.println("Book Name：" + this.bookName + ",Author：" + this.author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
