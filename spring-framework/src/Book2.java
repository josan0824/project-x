public class Book2 {
    private String bookName;

    private String author;

    public Book2() {
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void printBookInfo() {
        System.out.println("Book2 Name：" + this.bookName + ",Author：" + this.author);
    }

    @Override
    public String toString() {
        return "Book2{" +
                "bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
