# PRJ_battle-slime-

#### Spinner
```java
Spinner spn = (Spinner) findViewById(/*R.id.spn~~*/);


//getSelected
//取得目前Spinner選單 內容物(陣列) 的位置
int p = spn.getSelectedItemPosition();

String str1 = getResources().getStringArray(/*R.array.STRNAME*/);

```

```xml
<Spinner
android:entries="@array/STRING_ARRAY"
/>
```
