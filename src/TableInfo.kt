import java.sql.Date

/*
madang 데이터베이스에 정의되어 있는 테이블들을 정의
 */

data class Book(
    val bookid : Int? = null,   // PK
    val bookname : String,
    val publisher : String,
    val price : Int
)

data class Customer(
    val custid : Int? = null,  // PK
    val name : String,
    val address : String,
    val phone: String
)

data class ImportedBook(
    val bookid : Int,   // PK
    val bookname : String,
    val publisher : String,
    val price : Int
)

data class Orders(
    val orderid : Int,  // PK
    val custid : Int,   // FK
    val bookid : Int,   // FK
    val saleprice : Int,
    val orderdate : Date,  // java.sql.Date 형을 써서 별도의 변환 필요없음
)