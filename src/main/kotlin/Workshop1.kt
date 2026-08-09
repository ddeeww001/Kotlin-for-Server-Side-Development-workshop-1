package org.example


fun main() {
    while (true) {
        // 1. แสดงเมนูให้ผู้ใช้เลือก
        println("===== Unit Converter =====")
        println("โปรดเลือกหน่วยที่ต้องการแปลง:")
        println("1. Celsius to Fahrenheit")
        println("2. Kilometers to Miles")
        println("พิมพ์ 'exit' เพื่อออกจากโปรแกรม")
        print("เลือกเมนู (1, 2, or exit): ")

        val choice = readln()
        when (choice) {
            "1" -> convertCelsiusToFahrenheit()
            "2" -> convertKilometersToMiles()
            "exit"-> break
            else -> println("กรุณาใส่ข้อมูลให้ถูกต้อง")
        }
        println() // พิมพ์บรรทัดว่างเพื่อความสวยงาม
    }
}

fun celsiusToFahrenheit(celsius: Double): Double = celsius * 9.0 / 5.0 + 32


fun kilometersToMiles(kilometers: Double): Double =  kilometers * 0.621371


fun convertCelsiusToFahrenheit() {
    print("ป้อนค่าองศาเซลเซียส (Celsius): ")
    val input = readln()
    // 5. จัดการ Null Safety ด้วย toDoubleOrNull() และ Elvis operator (?:)
    // ออกจากฟังก์ชัน convertCelsiusToFahrenheit() หากข้อมูลผิดพลาด: return
    // celsius
    // 🚨
    val celsius = input.toDoubleOrNull() ?: 0.0
    val fahrenheitResult = celsiusToFahrenheit(celsius)

    // 6. แสดงผลลัพธ์
    // ใช้ String format เพื่อแสดงทศนิยม 2 ตำแหน่ง
    println("ผลลัพธ์: $celsius °C เท่ากับ ${"%.2f".format(fahrenheitResult)} °F")
}


// ฟังก์ชันสำหรับจัดการกระบวนการแปลง Kilometers to Miles ทั้งหมด
fun convertKilometersToMiles() {
    print("ป้อนค่ากิโลเมตร (Kilometers): ")
    val input = readln()

    // 5. จัดการ Null Safety ด้วย toDoubleOrNull() และ Elvis operator (?:)
    // ออกจากฟังก์ชัน convertKilometersToMiles() หากข้อมูลผิดพลาด: return
    // kilometers
    // 🚨
    val kilometers = input.toDoubleOrNull() ?: return
    val milesResult = kilometersToMiles(kilometers)

    // 6. แสดงผลลัพธ์
   println("ผลลัพธ์: $kilometers km เท่ากับ ${"%.2f".format(milesResult)} miles")
}