# วิธี Set Up โฟลเดอร์ Project
-[x] สร้าง Folder ตั้งชื่ออะไรก้ได้อันนึงแล้วเปิด cmd ที่ Folder
-[x] พิมพ์  ``` git clone https://github.com/DiFve/YIMS ``` เพื่อลงไฟล์ Project ไว้ใน Folder
-[x] เปิด netbeans แล้ว กด ``` File > Open Project > Folderที่สร้างมาแล้ว ``` เปิดไฟล้ Project ที่ชื่อ ```YIMS```
-[x] ใน netbeans กดคลิ๊กขวาที่ Project ```YIMS``` แล้ว ```set up JavaFX ใหม่ + ลบของเก่าออก```

# วิธี Push งาน
-[x] เปิด Folder ```YIMS``` แล้วเปิด ```cmd```
-[x] -[x] พิมพ์ ```git checkout -b ชื่อBranchที่จะสร้าง``` ทำแค่ครั้งแรกครั้งเดียว -[x] -[x]
-[x] พิมพ์ ```git add .```
-[x] ```git commit -m "ข้อความ"```
-[x] ```git push origin ชื่อBranchที่สร้างไว้``` **ห้ามใช้ git push origin main เด็ดขาด เดี๋ยวโค้ดบึ้ม**
-[x] ไปที่หน้า ```https://github.com/DiFve/YIMS```จะเห็นปุ่ม ```Compare && pull request``` กดเข้าไปแล้วกด ```pull request```
-[x] บอกจ๊ากให้มา Merge ให้
-[x] หลังจากจ๊าก Merge ให้แล้วให้กลับไปที่ cmd แล้ว พิมพ์```git checkout master```  ```git pull origin master```เพื่อสลับกลับไปยัง branch main และอัพเดท

# วิธี Pull **ต้องทำก่อนเขียนโค้ดเพิ่มทุกครั้ง**
-[x] เปิด Folder ```YIMS``` แล้วเปิด ```cmd```
-[x] พิมพ์ git ```checkout master``` 
-[x] ```git pull```
-[x] ```แล้วก็ทำงานซะ```