package org.example

fun validateCitizenId(id: String?): Boolean {
    if (id.isNullOrBlank()) return false

    // แปลงตัวเลขไทยให้เป็นเลขอารบิก
    val normalizedId = id.map { char ->
        when (char) {
            in '๐'..'๙' -> (char - '๐' + '0'.code).toChar()
            else -> char
        }
    }.joinToString("")

    // ตรวจสอบว่ามีความยาว 13 หลักพอดี และเป็นตัวเลขทั้งหมด
    if (normalizedId.length != 13 || !normalizedId.all { it.isDigit() }) {
        return false
    }

    // คำนวณ Checksum จาก 12 หลักแรก
    var sum = 0
    for (i in 0..11) {
        val digit = normalizedId[i].digitToInt()
        val weight = 13 - i
        sum += digit * weight
    }

    // คำนวณหา Check digit ที่ควรจะเป็น
    val expectedCheckDigit = (11 - (sum % 11)) % 10
    
    // ดึงตัวเลขหลักที่ 13 มาเปรียบเทียบ
    val actualCheckDigit = normalizedId[12].digitToInt()

    return expectedCheckDigit == actualCheckDigit
}