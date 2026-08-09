# สรุปเนื้อหาและการเรียนรู้ Workshop #1 (Unit Converter)

เอกสารสรุปเนื้อหา แนวคิดสำคัญ โครงสร้างโจทย์ และคำแนะนำการปรับปรุงโค้ดสำหรับ **Workshop #1: Simple Console Application - Unit Converter**

---

## 1. ภาพรวมโจทย์ใน Workshop #1

เป้าหมายของ Workshop #1 คือการสร้างแอปพลิเคชันคอนโซลสำหรับแปลงหน่วยอุณหภูมิ (Celsius -> Fahrenheit) และแปลงระยะทาง (Kilometers -> Miles) โดยครอบคลุมแนวคิดหลักของภาษา Kotlin ดังนี้:

- **Control Flow:** การใช้ `while (true)` เพื่อวนลูปเมนู และการใช้ `when` expression ในการเลือกการทำงาน
- **Null Safety:** การใช้ `toDoubleOrNull()` ป้องกัน Error จาก Input ขยะ ร่วมกับ **Elvis Operator (`?:`)**
- **Functions:** การสร้างฟังก์ชันคำนวณแยก (Pure Functions) และฟังก์ชันจัดการ Workflow (Handler Functions)
- **Formatting:** การจัดฟอร์แมต String แสดงผลทศนิยม 2 ตำแหน่ง (`"%.2f".format(...)`)

---

## 2. สรุปแนวคิดสำคัญ (Key Concepts)

### 2.1 การใช้งาน `when` Control Flow
- เข้ามาแทนที่ `switch-case` ในภาษา Java โดยไม่ต้องใช้คำสั่ง `break` ในแต่ละเคส
- สามารถใช้งานเป็นได้ทั้ง **Statement** (สั่งงานทั่วไป) และ **Expression** (คืนค่าใส่ตัวแปร)
- รองรับการตรวจสอบหลายค่าในบรรทัดเดียว, ช่วงข้อมูล (`in`), เช็คประเภท (`is`), หรือแบบไม่มี Argument (ใช้แทน `if-else if`)

### 2.2 Null Safety & Elvis Operator (`?:`)
- **`toDoubleOrNull()`**: แปลง String เป็น Double อย่างปลอดภัย หากแปลงไม่ได้จะคืนค่าเป็น `null` แทนการพังด้วย `NumberFormatException`
- **Elvis Operator (`?:`)**: กำหนดทางเลือกสำรองเมื่อค่าฝั่งซ้ายเป็น `null`
  - *รูปแบบ 1 (Default Value):* `val price = input.toDoubleOrNull() ?: 0.0`
  - *รูปแบบ 2 (Guard Clause / Early Return):* `val celsius = input.toDoubleOrNull() ?: return` (แนะนำสำหรับ Workshop)

### 2.3 การสร้างฟังก์ชัน (`fun`)
- ประกาศขึ้นต้นด้วย `fun`
- รองรับการเขียนแบบ **Single-Expression Function** เพื่อความกระชับ เช่น:
  ```kotlin
  fun celsiusToFahrenheit(celsius: Double): Double = celsius * 9.0 / 5.0 + 32
  ```

---

## 3. สรุปผลการรีวิวโค้ด & Idiomatic Kotlin (Workshop #1)

### 🌟 จุดที่ทำได้ดีแล้ว
1. **การเลือกใช้ `val` vs `var`**: เลือกใช้ `val` (Immutability) 100% ป้องกันการแก้ไขตัวแปรโดยไม่ตั้งใจ
2. **การใช้งาน `when` ใน `main()`**: แบ่งโครงสร้างเมนู และใช้ `break` ออกจากลูป `while` ได้สะอาด อ่านง่าย
3. **ฟังก์ชันคำนวณ**: เขียนสูตรคำนวณและ Return Type ได้ถูกต้องตามข้อกำหนด

### 💡 จุดที่แนะนำให้ปรับปรุง
1. **การจัดการ Null Safety ใน `convertCelsiusToFahrenheit()` / `convertKilometersToMiles()`**:
   - *เดิม:* `val celsius = input.toDoubleOrNull() ?: 0.0` (เมื่อป้อนข้อความผิด ตัวแปรจะกลายเป็น `0.0` แล้วถูกนำไปคำนวณต่อ)
   - *ปรับปรุง:* ควรใช้ `val celsius = input.toDoubleOrNull() ?: return` หรือใช้ `run { println(...); return }` เพื่อหยุดการทำงานเมื่อผู้ใช้ป้อนข้อมูลผิดพลาด
2. **เพิ่ม `else` ใน `when (choice)`**:
   - เพิ่ม `else -> println("กรุณาใส่ข้อมูลให้ถูกต้อง")` เพื่อแจ้งเตือนกรณีป้อนเมนูที่ไม่ถูกต้อง

---

## 4. โครงสร้างตัวอย่างโปรแกรมแบบ Idiomatic Kotlin

```kotlin
fun celsiusToFahrenheit(celsius: Double): Double = celsius * 9.0 / 5.0 + 32

fun convertCelsiusToFahrenheit() {
    print("ป้อนค่าองศาเซลเซียส (Celsius): ")
    val input = readln()

    val celsius = input.toDoubleOrNull() ?: run {
        println("ข้อผิดพลาด: กรุณาป้อนตัวเลขที่ถูกต้อง!")
        return
    }

    val fahrenheitResult = celsiusToFahrenheit(celsius)
    println("ผลลัพธ์: $celsius °C เท่ากับ ${"%.2f".format(fahrenheitResult)} °F")
}
```

---

## 5. ผลการรันโปรแกรม (Execution Result)

![ผลการรันโปรแกรม Workshop #1](file:///d:/Learn_frontend/Kotlin-for-Server-Side-Development-workshop-1/images/workshop1_execution.png)

> [!NOTE]
> **เกร็ดความรู้เรื่องภาษาไทยต่างดาว (Character Encoding):** 
> หากรันโปรแกรมบน Windows / IntelliJ Console แล้วตัวอักษรภาษาไทยแสดงผลเป็นเครื่องหมาย `` (Font/Encoding Mismatch) เกิดจาก Terminal หรือ JVM บน Windows ไม่ได้กำหนด Encoding เป็น `UTF-8` เป็นค่าเริ่มต้น สามารถแก้ไขได้โดยการเพิ่มตัวเลือก `-Dfile.encoding=UTF-8` ใน JVM Options ของ IDE หรือใน `build.gradle.kts`

---
*บันทึกสรุปสำหรับการเรียนรู้รายวิชา Kotlin for Server-Side Development*

