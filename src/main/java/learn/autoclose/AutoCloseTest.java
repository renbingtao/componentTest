package learn.autoclose;

public class AutoCloseTest implements AutoCloseable {

    public AutoCloseTest() {
        System.out.println("Connection opened");
    }


    @Override
    public void close() {
        System.out.println("Connection closed");
        // Cleanup logic (e.g., release database connection)
    }

    public static void main(String[] args) {
        //在try里定义的变量，会在try里定义的变量执行完try里的代码后，自动调用close()方法，即conn.close()，所以要求变量的类必须实现close()方法
        try (AutoCloseTest conn = new AutoCloseTest()) {
            conn.query("SELECT * FROM users");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // `conn.close()` called automatically
    }

    public void query(String sql) {
        System.out.println("Executing: " + sql);
    }

}
