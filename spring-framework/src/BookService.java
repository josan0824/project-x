public class BookService {
    //以前的写法
    //private Book book = new Book();
    private Book book;

    public void setBook(Book book) {
        this.book = book;
    }

    public void printBook() {
        System.out.println("printBook");
    }

    public Book getBook() {
        return book;
    }
}
