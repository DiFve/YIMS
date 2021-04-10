# วิฑีชั้ย Git อันนี้ (อ่านซะไอเปรต)
- [ ] สร้างซักโฟลเดอร์นึงขึ้นมา
- [ ] ``` git clone https://github.com/DiFve/YIMS ``` เป็นคำสั่ง Download ทั้งโปรเจคนี้ไปไว้ในโฟลเดอร์ที่เราสร้าง 
- [ ] ก่อนจะแก้ไขไฟล์งานให้พิมพ์ ``` git pull ``` เพื่ออัพเดทงานให้เป็นอันล่าสุดตลอดเวลา เผื่อมีเพื่อนมาCommitเพิ่ม
- [ ] ``` git checkout -b "branch name" ``` เพื่อสร้าง branch ใหม่และสลับมายัง branch นั้น
- [ ] ทำงานไรเสร็จก็ ``` git add . git commit ```  ปกติ
- [ ] ใช้ ``` git push origin "branch name" ``` ห้ามใช้ main เด็ดขาด เดี๋ยวโค้ดบึ้มถ้าcommitขึ้น branch แล้วเดี๋ยวมา mergeรวมให้
- [ ] เมื่อ push เสร็จแล้ว กลับมาที่หน้า github จะมีปุ่ม ```Compare && pull request``` ขึ้นมา กด แล้วกด pull request 
- [ ] เมื่อกดรับ pull request แล้ว ให้ใช้คำสั่ง ```git checkout main```  ```git pull origin main``` เพื่อสลับกลับไปยัง branch main และอัพเดท
