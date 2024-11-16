import java.sql.Date


fun main() {

    val po = ProcessOperation()

    while(true)
    {
        println("메뉴를 선택하세요: 1. 삽입  2. 삭제  3. 검색 4. 종료")
        when (readLine()?.toIntOrNull()) {
            1 -> {
                println("삽입할 테이블을 선택하세요: Book, Customer")
                val table = readLine() ?: ""
                println("삽입할 데이터를 입력하세요.")
                when (table) {
                    "Book" -> {
                        print("책 이름: ")
                        val bookname = readLine() ?: ""
                        print("출판사: ")
                        val publisher = readLine() ?: ""
                        print("가격: ")
                        val price = readLine()?.toIntOrNull() ?: 0

                        val book = Book(
                            bookname = bookname,
                            publisher = publisher,
                            price = price)
                        po.insertData("Book", book)
                    }
                    "Customer" -> {
                        print("고객명: ")
                        val name = readLine() ?: ""
                        print("주소: ")
                        val address = readLine() ?: ""
                        print("휴대폰 번호 (예시:010-1234-5678): ")
                        val phone = readLine() ?: ""

                        val customer = Customer(
                            name = name,
                            address = address,
                            phone = phone
                        )
                        po.insertData("Customer", customer)
                    }
                    else -> println("잘못 입력하셨습니다.")
                }
            }
            2 -> {
                println("삭제할 테이블을 선택하세요: Book, Customer")
                val table = readLine() ?: ""
                when (table) {
                    "Book" -> {
                        print("삭제할 책 이름을 입력하세요: ")
                        val condition = readLine() ?: ""
                        po.deleteData(table, "bookname", condition)
                    }
                    "Customer" -> {
                        print("삭제할 고객 이름을 입력하세요: ")
                        val condition = readLine() ?: ""
                        po.deleteData(table, "name", condition)
                    }
                    else  -> {
                        println("잘못 입력하셨습니다.")
                    }
                }

            }
            3 -> {
                println("검색할 테이블을 선택하세요: Book, Customer")
                val table = readLine() ?: ""
                when (table) {
                    "Book" -> {
                        print("검색할 책 이름을 입력하세요:")
                        val keyword = readLine() ?: ""
                        val results = po.searchData(table, "bookname", keyword)
                        println("검색 결과:")
                        results.forEach { println(it) }
                    }
                    "Customer" -> {
                        print("검색할 고객 이름을 입력하세요:")
                        val keyword = readLine() ?: ""
                        val results = po.searchData(table, "name", keyword)
                        println("검색 결과:")
                        results.forEach { println(it) }
                    }
                    else  -> {
                        println("잘못 입력하셨습니다.")
                    }
                }
            }
            4 -> {
                println("프로그램 종료중 ...")
                break
            }
            else -> println("잘못된 입력입니다.")
        }
    }

}
