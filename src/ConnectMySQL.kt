import java.sql.Connection
import java.sql.DriverManager

object ConnectMySQL {
    private const val URL = "jdbc:mysql://192.168.153.101:4567/madang"
    private const val USER = "hyeseong"
    private const val PASSWORD = "1234"

    fun getConnection(): Connection {
        return DriverManager.getConnection(URL, USER, PASSWORD)
    }
}