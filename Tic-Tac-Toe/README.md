# Project ASAP (Tic-Tac-Toe)

This repository is a final project (Java GUI) from Object-Oriented Programming Class, Teknik Informatika Universitas Padjadjaran.

[Challenge Guidelines](challenge-guideline.md)

Project Games Tic-Tac-Toe

## Credits

| NPM           | Name                       |
| ------------- | -------------------------- |
| 140810210009  | Ardes Zubka Putra          |
| 140810210051  | Satria Alief Putra Hidayat |
| 140810210059  | Prames Ray Lapian          |

## Change log

- **[Sprint Planning](changelog/sprint-planning.md) - (planning date)**
  - Membuat project dan inisiasi gradle dan javaFX
  - Mencari referensi penggunaan software terkait
  - Mencari referensi pembuatan Tic Tac Toe
  - Membuat logika ketika kondisi Menang
  - Membuat logika ketika kondisi Kalah
  - Membuat logika ketika kondisi Seri
  - Membuat aturan kontrol mouse/keyboard pemain
  - Membuat list Fitur yang bisa disispkan
  - Membuat Logika Scoreboard
  - Membuat Tampilan Dashboard
  - Perbaiki Tampilan Aplikasi
  - Membuat Single Player
  - Membuat Halaman How-To-Play
  - Final Check

- **[Sprint 1](changelog/sprint-1.md) - (date from 15 until 21)**
  - Membuat project dan inisiasi gradle dan javaFX
  - Mencari referensi penggunaan software terkait
  - Mencari referensi pembuatan Tic Tac Toe

- **[Sprint 2](changelog/sprint-2.md) - (date from 22 until 28)**
  - Membuat logika ketika kondisi Menang
  - Membuat logika ketika kondisi Kalah
  - Membuat logika ketika kondisi Seri
  - Membuat aturan kontrol mouse/keyboard pemain
  - Membuat list Fitur yang bisa disispkan
  
- **[Sprint 3](changelog/sprint-3.md) - (date from 29 until 05)**
  - Membuat Logika Score
  - Membuat Akumulasi Score Pada Scoreboard
  - Membuat Tampilan Menu Pilihan Awal
  - Perbaiki Bug Pada Saat Kondisi Menang/Seri/Kalah
  - Perbaiki Tampilan Saat Player Menang 
  - Membuat Single Player
  - Membuat Halaman How-To-Play 
  - Final Check 

## Running The App

Folder utama 'PROJECT-AKHIR-UAS-PBO-2022-KELOMPOK-A-09'

1. Ketik kode di bawah pada folder utama.
```
./gradlew run
```

2. Kursor mouse digunakan dalam aplikasi :
   - Gunakan kursor untuk memilih/menekan pilihan/tombol dalam aplikasi.
   - Bermainlah menggunakan kursor dari mouse secara bergantian sesuai dengan peraturan bermain Tic-Tac-Toe.

## Classes Used

1. **App** -
`App.java`
   - Program yang digunakan untuk menyambungkan fxml awal ke kodingan.

2. **Game Controller**
`GameController.java`
   - Program yang berfungsi untuk menjalankan game Tic-Tac-Toe.
   - Program ini tersambung dengan Game.fxml sebagai halaman untuk memainkan game.

3. **Home Controller**
`HomeController.java`
   - Program yang berisi fungsi fungsi tombol pada halaman utama
   - Tersambung dengan Home.fxml sebagai halaman utama yang
   - Halaman utama berisi tombol pilihan antara bermain game atau cara bermain

4. **How To Play Controller**
`HowToPlayController.java`
   - Program berisi langkah langkah cara bermain game Tic-Tac-Toe
   - Tersambung dengan file HowToPlay.fxml sebagai halaman cara bermain

## Notable Assumption and Design App Details

- Desain Aplikasi
  - Aplikasi berbasi GUI
  - Pada halaman utama terdapat pilihan halamn How To Play dan Play
  - Halaman Play untuk bermain
  - Halaman How To Play berisi cara bermain Tic-Tac-Toe
  - Pada halaman Play, pemain menunggu giliran memilih tombol X/O
  - Terdapat kondisi menang dan seri jika tidak ada yang memenangkan ronde
  - Terdapat tombol Home di setiap halaman untuk kembali ke halaman utama (Home)
