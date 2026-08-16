import org.example.validateCitizenId
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class test {

@Test
    fun `id with wrong checksum returns false`() {
        // หลักที่ 13 ต้องเป็น check digit ที่คำนวณจาก 12 หลักแรก
        // 110170018520 → check digit ที่ถูกต้องคือ 6
        assertFalse(validateCitizenId("1101700185207")) // หลักสุดท้ายผิด
        assertFalse(validateCitizenId("asdfghjkluit"))
        assertFalse(validateCitizenId("1234567890129")) // ที่ถูกคือ ...1

        // ใบที่ checksum ถูกต้อง ต้องยังผ่านอยู่
        assertTrue(validateCitizenId("3509900547250"))
        assertTrue(validateCitizenId("1234567890121"))
    }

    @Test
    fun `supports Thai numerals flawlessly`() {
        // เลขไทยที่ถูกต้อง (เทียบเท่า 3509900547250)
        assertTrue(validateCitizenId("๓๕๐๙๙๐๐๕๔๗๒๕๐"))
        // เลขไทยผสมเลขอารบิก
        assertTrue(validateCitizenId("1234567890๑๒1"))
        
        // เลขไทยที่ผิด
        assertFalse(validateCitizenId("๑๑๐๑๗๐๐๑๘๕๒๐๗")) 
    }

}