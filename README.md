## FriendlyGreenHouse

組員工作分配:
----
Ivan+Deniel : 硬體部分+硬體部分的測試文件規劃

鳥哥 : 資料庫填充+測試文件

鵬中 : server連結資料庫+server部分的測試文件規劃

靖霆 : Android App的我的養花日誌和養花大百科+其測試文件規劃

軒如 : Android App的其他部分+其測試文件規劃

Android App工作之詳細分配
-----
* 專案架構請參考dropbox設計文件的最新版(UML diagram)
* 專案已裝專屬Android的Unit test framework:Robolectric，可以玩玩看

####靖霆:
* 我的養花日誌: 使用SQLite做儲存

SQLite操作class參考專案的LocalDatabase/SQLiteHandler.java；Activity介面在DiaryActivity.java
* 養花大百科

呼叫API的method: api/CallAPIhelper.java的getDictionaryFlowers()需要實作；Activity介面在DictionaryActivity.java

DeadLine
------
* 整合測試(硬體+軟體皆要完成) : 星期一晚上6:00前
* 驗收測試(+環境(地下室)測試) + 測試文件報告 : 星期二晚上6:00前
* Demo Time : 星期三
