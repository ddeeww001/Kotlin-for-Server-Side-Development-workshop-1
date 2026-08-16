import kotlin.test.Test
import kotlin.test.assertEquals
import org.example.*
import kotlin.test.assertTrue
import kotlin.test.assertFalse
class WorkshopTest {

    // --- Tests for Workshop #1: Unit Converter ---

    // celsius input: 20.0
    // expected output: 68.0
    @Test
    fun `test celsiusToFahrenheit with positive value`() {
        // Arrange: ตั้งค่า input และผลลัพธ์ที่คาดหวัง
        val celsiusInput = 20.0
        val expectedFahrenheit = 68.0

        // Act: เรียกใช้ฟังก์ชันที่ต้องการทดสอบ
        val actualFahrenheit = celsiusToFahrenheit(celsiusInput)

        // Assert: ตรวจสอบว่าผลลัพธ์ที่ได้ตรงกับที่คาดหวัง
        assertEquals(expectedFahrenheit, actualFahrenheit, 0.001, "20°C should be 68°F")
    }

    // celsius input: 0.0
    // expected output: 32.0
    @Test
    fun `test celsiusToFahrenheit with zero`() {
        val celsiusInput = 0.0
        val expectedFahrenheit = 32.0
        val actualFahrenheit = celsiusToFahrenheit(celsiusInput)
        assertEquals(expectedFahrenheit, actualFahrenheit, 0.001, "20°C should be 68°F")
        }

    // celsius input: -10.0
    // expected output: 14.0
    @Test
    fun `test celsiusToFahrenheit with negative value`() {
        val celsiusInput = -10.0
        val expectedFahrenheit = 14.0
        val actualFahrenheit = celsiusToFahrenheit(celsiusInput)
        assertEquals(expectedFahrenheit, actualFahrenheit, 0.001, "20°C should be 68°F")

    }

    // test for kilometersToMiles function
    // kilometers input: 1.0
    // expected output: 0.621371
    @Test
    fun `test kilometersToMiles with one kilometer`() {
        val celsiusInput = 1.0
        val expectedFahrenheit = 0.621371
        val actualFahrenheit = celsiusToFahrenheit(celsiusInput)
        assertEquals(expectedFahrenheit, actualFahrenheit, 0.000001, "20°C should be 68°F")
    }

    // --- Tests for Workshop #1: Unit Converter End ---

    // --- Tests for Workshop #2: Data Analysis Pipeline ---
    // ทำการแก้ไขไฟล์ Workshop2.kt ให้มีฟังก์ชันที่ต้องการทดสอบ
    // เช่น ฟังก์ชันที่คำนวณผลรวมราคาสินค้า Electronics ที่ราคา > 500 บาท
    // ในที่นี้จะสมมุติว่ามีฟังก์ชันชื่อ calculateTotalElectronicsPriceOver500 ที่รับ List<Product> และคืนค่า Double
    // จงเขียน test cases สำหรับฟังก์ชันนี้ โดยตรวจสอบผลรวมราคาสินค้า Electronics ที่ราคา > 500 บาท
    // 🚨

    // จงเขียน test cases เช็คจำนวนสินค้าที่อยู่ในหมวด 'Electronics' และมีราคามากกว่า 500 บาท
    // 🚨

    // --- Tests for Workshop #1: Unit Converter End ---

    // --- Tests for Workshop #2: Data Analysis Pipeline ---
    @Test
    fun `test products condition with assertTrue and assertFalse`() {
        // Arrange: เตรียมข้อมูลสินค้า
        val productsList = listOf(
            Product("Laptop", 35000.0, "Electronics"),
            Product("Keyboard", 499.0, "Electronics")
        )
        // Act: ตรวจสอบเงื่อนไขผ่าน Collection Operations (เช่น .any หรือ .all)
        val hasExpensiveItem = productsList.any { it.price > 500.0 }
        val hasNegativePrice = productsList.any { it.price < 0.0 }
        // Assert: ใช้ assertTrue และ assertFalse ตรวจสอบ
        assertTrue(hasExpensiveItem, "ควรมีสินค้าที่ราคามากกว่า 500 บาทอยู่ในรายการ")
        assertFalse(hasNegativePrice, "ไม่ควรมีสินค้าที่ราคาติดลบ")
    }




    // --- Tests for Workshop #2: Data Analysis Pipeline End ---
}