# AI Log & บันทึกการเรียนรู้ Workshop #1 (Unit Converter)

เอกสารสรุปการเรียนรู้และ AI Log ตามรูปแบบรายวิชา Kotlin for Server-Side Development สำหรับ **Workshop #1: Simple Console Application - Unit Converter**

---

## 1. Prompt ที่ใช้ (สรุป)

- **Prompt 1 (สรุปโจทย์):** ให้ AI รวบรวมและสรุปเนื้อหาโจทย์จากทุกไฟล์ในโปรเจกต์ (`Workshop1.kt`, `Workshop2.kt`, `WorkshopTest.kt`) เพื่อทำความเข้าใจขอบเขตงาน
- **Prompt 2 (สอบถามไวยากรณ์):** สอบถามวิธีใช้งาน `when`, Null Safety (`toDoubleOrNull()`), Elvis Operator (`?:`), และการสร้างฟังก์ชัน (`fun`) พร้อมขอตัวอย่างการนำไปปรับใช้
- **Prompt 3 (รีวิวโค้ด):** ให้ AI รีวิวโค้ดที่เขียนใน `Workshop1.kt` โดยเน้นประเด็นความเป็น **Idiomatic Kotlin** (การใช้ `val/var`, `?:`, `?.`, `let`) โดยให้ชี้แจงทีละจุดพร้อมเหตุผล

---

## 2. AI ตอบผิด / น่าสงสัยตรงไหน

- **การใช้ Default Value (`?: 0.0`) แทน Early Return:**
  ในตอนแรก AI ได้อธิบายว่า Elvis Operator (`?:`) สามารถใช้คืนค่า Default Value ได้ เช่น `val celsius = input.toDoubleOrNull() ?: 0.0` ซึ่งแม้จะทำให้โค้ดไม่รันพังด้วย Exception แต่นี่เป็น **Logic Error ที่น่าสงสัย** เพราะถ้าผู้ใช้พิมพ์ข้อความที่ไม่ใช่ตัวเลข (เช่น `"abc"`) ตัวแปร `celsius` จะกลายเป็น `0.0` แล้วคำนวณต่อ ได้ผลลัพธ์เป็น `0.0 °C เท่ากับ 32.00 °F` ซึ่งขัดกับโจทย์ที่ระบุว่า _"ออกจากฟังก์ชันหากข้อมูลผิดพลาด: return"_
- **การขาดกรณีครอบคลุมใน `when` (Exhaustive check):**
  ในตอนแรก `when (choice)` ใน `main()` มีเพียงเคส `"1"`, `"2"`, และ `"exit"` แต่ขาด `else` ทำให้เมื่อผู้ใช้กรอกข้อความอื่น โปรแกรมจะนิ่งเงียบไปโดยไม่แจ้งเตือนผู้ใช้

---

## 3. เราตัดสินใจ / แก้อย่างไร

- **ปรับใช้ Guard Clause Pattern (`?: return`):**
  เปลี่ยนจากการใส่ค่า Default `0.0` มาเป็นการใช้ `return` หรือ `run { println(...); return }` ทางฝั่งขวาของ Elvis Operator (`?:`) เพื่อหยุดการทำงานของฟังก์ชันทันทีเมื่อ `toDoubleOrNull()` ได้ค่า `null`:
  ```kotlin
  val celsius = input.toDoubleOrNull() ?: run {
      println("ข้อผิดพลาด: กรุณาป้อนตัวเลขที่ถูกต้อง!")
      return
  }
  ```
- **เพิ่ม branch `else` ใน `when (choice)`:**
  เติม `else -> println("กรุณาใส่ข้อมูลให้ถูกต้อง")` เพื่อแจ้งเตือนผู้ใช้เมื่อกรอกเมนูที่ไม่ถูกต้อง
- **ปรับแต่งเป็น Idiomatic Kotlin:**
  - เลือกใช้ `val` ทั้งหมดเพื่อเน้น Immutability (ห้ามใช้ `var` หากไม่จำเป็น)
  - ปรับฟังก์ชันคำนวณแบบบรรทัดเดียวให้เป็น Single-Expression Function (`fun celsiusToFahrenheit(celsius: Double): Double = celsius * 9.0 / 5.0 + 32`)

---

## 4. สิ่งที่ได้เรียนรู้

1. **Guard Clause Pattern ด้วย Elvis Operator (`?:`):**
   Elvis Operator ไม่ได้ใช้เพียงแค่กำหนดค่า Default เท่านั้น แต่ใน Kotlin นิยมใช้ร่วมกับ `return` หรือ `throw` เพื่อสกัดกั้นข้อมูลที่ไม่ถูกต้องตั้งแต่ต้นฟังก์ชัน
2. **หลักการเขียน Idiomatic Kotlin:**
   - การยึดหลัก Immutability ด้วย `val`
   - การลดทอนรูปด้วย Single-Expression Function (`=`)
   - การใช้ `when` ควบคุม Flow แทน `if-else if` และมี `else` คุมเคสที่ไม่คาดคิดเสมอ
3. **ความแตกต่างของ Null Safety Operators:**
   ทำความเข้าใจการทำงานระหว่าง `toDoubleOrNull()`, Safe Call (`?.`), Elvis Operator (`?:`), และ Scope Function (`let`)
4. **การแก้ปัญหา Character Encoding บน Windows Terminal:**
   เรียนรู้ว่าอาการตัวอักษรภาษาไทยกลายเป็นเครื่องหมายต่างดาว () เกิดจาก Windows Terminal / JVM ไม่ได้กำหนดค่า `UTF-8` และวิธีแก้ไขด้วยการระบุ `-Dfile.encoding=UTF-8`

---

