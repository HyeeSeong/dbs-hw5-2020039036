class ProcessOperation()
{
    private val connection = ConnectMySQL.getConnection()

    fun insertData(table: String, data: Any) {

        val query = when (table) {
            "Book" -> "INSERT INTO Book (bookname, publisher, price) VALUES (?, ?, ?)"
            "Customer" -> "INSERT INTO Customer (name, address, phone) VALUES (?, ?, ?)"
            else -> throw IllegalArgumentException("Unknown table: $table")
        }

        connection.prepareStatement(query).use { statement ->
            when (table) {
                "Book" -> {
                    val book = data as Book
                    statement.setString(1, book.bookname)
                    statement.setString(2, book.publisher)
                    statement.setInt(3, book.price)
                }
                "Customer" -> {
                    val customer = data as Customer
                    statement.setString(1, customer.name)
                    statement.setString(2, customer.address)
                    statement.setString(3, customer.phone)
                }
            }
            statement.executeUpdate()
        }
        println("$table 에 성공적으로 데이터를 추가했습니다.")
    }

    fun deleteData(table: String, columnName: String, condition: String) {

        val query = "DELETE FROM $table WHERE $columnName LIKE ?"

        connection.prepareStatement(query).use { statement ->
            statement.setString(1, "%$condition%")
            statement.executeUpdate()
        }
        println("$table 테이블에서 $condition 이(가) 포함된 모든 Columns 을 삭제하였습니다.")
    }

    fun searchData(table: String, columnName: String, keyword: String): List<Map<String, Any>> {

        val query = "SELECT * FROM $table WHERE $columnName LIKE ?"
        val results = mutableListOf<Map<String, Any>>()

        connection.prepareStatement(query).use { statement ->
            statement.setString(1, "%$keyword%")
            val resultSet = statement.executeQuery()

            while (resultSet.next()) {
                val row = mutableMapOf<String, Any>()
                val metaData = resultSet.metaData
                for (i in 1..metaData.columnCount) {
                    row[metaData.getColumnName(i)] = resultSet.getObject(i)
                }
                results.add(row)
            }
        }
        return results
    }


}