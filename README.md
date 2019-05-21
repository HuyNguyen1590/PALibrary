# PACore Library
![](https://camo.githubusercontent.com/d4e0f63e9613ee474a7dfdc23c240b9795712c96/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f5052732d77656c636f6d652d627269676874677265656e2e737667)

## Cài đặt

``` java
implementation 'com.github.pamobilevn.PALibrary:PACore:v2.15'
```

## Nội dung
* [Array](#array)
* [Bitmap](#bitmap)
* [Json](#json)
* [Layout](#layout)
* [Process File](#process_file)
* [Utils](#utils)
* [DefaultFunction Class](#default_function)
* [Convert](#convert)
* [SharedPreference](#shared_preference)

 <a name="array"></a>
 
### Array 

 
| **TÊN PHƯƠNG THỨC** | **MÔ TẢ** | **SỬ DỤNG** |
| --- | --- | --- |
| toArray(ArrayList&lt;T&gt; list) | Chuyển từ ArrayList sang Array |  ArrayList&lt;AppItem&gt; arrayList = new ArrayList&lt;&gt;(); <br/> AppItem[] arrResult = **`ArrayConvert.toArray(arrayList);`** |
| toArrayList(ArrayList&lt;Object&gt; list) | Chuyển từ ArrayList&lt;Object&gt; sang một ArrayList với object do người dùng định nghĩa | ArrayList&lt;Object&gt; arrayList = new ArrayList&lt;&gt;();<br/>ArrayList&lt;AppItem&gt; arrResult = **`ArrayConvert.toArrayList(arrayList);`** |
| toObjectArray(ArrayList&lt;T&gt; list) | Chuyển từ ArrayList với object do người dùng định nghĩa sang ArrayList&lt;Object&gt; | ArrayList&lt;AppItem&gt; arrAppItem = new ArrayList&lt;&gt;();<br/> ArrayList&lt;Object&gt; arrResult = **`ArrayConvert.toObjectArray(arrAppItem);`**  |
| toObjectArray(List&lt;T&gt; list) | Chuyển từ List với object do người dùng định nghĩa sang ArrayList&lt;Object&gt; | List&lt;AppItem&gt; arrAppItem = new ArrayList&lt;&gt;();<br/>ArrayList&lt;Object&gt; arrResult = **`ArrayConvert.toObjectArray(arrAppItem);`** |
| toObjectArray(T[] list) | Chuyển từ Array với object do người dùng định nghĩa sang ArrayList&lt;Object&gt; | AppItem[] arrAppItem = new AppItem[]{app0, app1};<br/>ArrayList&lt;Object&gt; arrResult = **`ArrayConvert.toObjectArray(arrAppItem)`**;  |
| toArrayList(List&lt;T&gt; list) | Chuyển từ List&lt;Object&gt; sang ArrayList&lt;Object&gt; | List&lt;AppItem&gt;  arrAppItem = new ArrayList&lt;&gt;();<br/>ArrayList&lt;AppItem&gt; arrResult = **`ArrayConvert.toArrayList(arrAppItem);`**  |
| toArrayList(T[] list) | Chuyển từ Array sang ArrayList | AppItem[]  arrAppItem = new AppItem[]{app1, app2};<br/>ArrayList&lt;AppItem&gt; arrResult = **`ArrayConvert.toArrayList(arrAppItem)`**;  |


<a name="bitmap"></a>
### Bitmap


| **TÊN PHƯƠNG THỨC** | **MÔ TẢ** | **SỬ DỤNG** |
| --- | --- | --- |
| getBitmapFromAssets(Context context, String path) | Trả về bitmap của một hình ảnh trong thư mục assets từ đường dẫn do người dùng truyền vào  | Bitmap mBitmap =   **`BitmapClass. getBitmapFromAssets(mContext,path);`** |
| getBitmapFromURL(String src) | Trả về  bitmap từ url do người dùng truyền vào | Bitmap mBitmap =   **`BitmapClass.getBitmapFromURL(url);`** |
| loadBitmapFromView(View v) | Tạo và trả về bitmap từ view được truyền vào | Bitmap bitmap = **`BitmapClass.loadBitmapFromView(view)`**; |
| loadBitmapFromFile(String path) | Đọc một file hình ảnh và trả về bitmap từ đường dẫn truyền vào | Bitmap mResult = **`BitmapClass.loadBitmapFromFile(path)`**; |
| AsyncGettingBitmapFromUrl() | Class load async lấy bitmap từ url | Bitmap mResult  =  new **`BitmapClass.AsyncGettingBitmapFromUrl().execute(url).get();`** |



<a name="json"></a>
### Json

| **TÊN PHƯƠNG THỨC** | **MÔ TẢ** | **SỬ DỤNG** |
| --- | --- | --- |
| convertToJson(Object obj) | Truyền vào một object bất kỳ phương thức xử lý và trả về json | String json = **`JsonCovert.convertToJson(obj)`** |
| convertFromJson(String json) | Chuyển json sang object | **`JsonConvert.convertFromJson(json);`** |
| getArray(String json, Class&lt;T[]&gt; tClass) | Chuyển từ json sang array | AppItem[] arr = **`JsonConvert.getArray(json,   AppItem[].class);`** |
| jsonToHashMap(String json) | Chuyển từ json sang HashMap | HashMap&lt;String,String&gt; arr = **`JsonConvert.jsonToHashMap(json);`** |




<a name="layout"></a>
### Layout

| **TÊN PHƯƠNG THỨC** | **MÔ TẢ** | **SỬ DỤNG** |
| --- | --- | --- |
| setParamMargin(View view, int top, int bottom, int left, int right) | Set margin cho một view bất kỳ | **`LayoutParams.setParamMargin(view, 10,10,10,10);`** |
| setParamWidthHeight(View view, intwidth, intheight) | Set width, height cho view | **`LayoutParams.setParamWidthHeight(view, 100, 100);`** |
| getStatusHeight(Activity mActivity) | Phương thức trả về height của status bar | intheight = **`LayoutParams.getStatusHeight(mActivity);`** |
| getNavBarHeight(Activity mActivity) | Phương thức trả về height của navigation bar | intheight = **`LayoutParams.getNavBarHeight(mActivity);`** |
| getActionBarHeight(Activity mActivity) | Phương thức trả về height của action bar | intheight = **`LayoutParams.getActionBarHeight(mActivity);`** |


<a name="process_file"></a>
### Process File

| **TÊN PHƯƠNG THỨC** | **MÔ TẢ** | **SỬ DỤNG** |
| --- | --- | --- |
| generateImageFileName() | Generate và trả về file name với ngày tháng năm hiện tại | **`ProcessFile.generateImageFileName();`** |
| saveImageToFolder(Bitmap bitmap, String folderName, String imgName, Context context) | Tạo hình ảnh và lưu vào bộ nhớ từ bitmap truyền vào với tên hình do người dùng đặt | **`ProcessFile. saveImageToFolder(mBitmap, "CardMaker","pokemon",mActivity);`** |
| saveImageToFolder(Bitmap bitmap, String folderName, Context context) | Tạo hình ảnh và lưu vào bộ nhớ từ bitmap truyền vào với tên hình được tạo từ phương thức generateImageFileName() | **`ProcessFile. saveImageToFolder(mBitmap, "CardMaker", mActivity);`** |
| openImage(Context context, String path) | Mở hình ảnh từ đường dẫn truyền vào | **`ProcessFile.openImage(mContext, path);`** |


<a name="utils"></a>
### Utils

| **TÊN PHƯƠNG THỨC** | **MÔ TẢ** | **SỬ DỤNG** |
| --- | --- | --- |
| openFacebookURL(Activity mActivity, String fbURL) | Mở Facebook từ url truyền vào | **`Utils.openFacebookURL(mActivity, fbURL)`** |
| openYouTubeURL(Activity mActivity, String YouTubeURL) | Mở Youtube từ url truyền vào | **`Utils.openYouTubeURL( mActivity, youtubeURL)`** |


<a name="default_function"></a>
### DefaultFunction Class

| **TÊN PHƯƠNG THỨC** | **MÔ TẢ** | **SỬ DỤNG** |
| --- | --- | --- |
| checkPackageName(String packageName) | Kiểm tra xem package name hiện tại có giống với package name truyền vào không nếu không thì mở ứng dụng trên google play và đóng app. Mục đích là để chặn clone ứng dụng | **`DefaultFunction.getInstance(this).checkPackageName("co.pamobile");`** |
| checkRateApp() | Hiện dialog yêu cầu người dùng rate app mỗi 3 lần mở ứng dụng | **`DefaultFunction.getInstance(this).checkRateApp();`** |
| checkCodeVersion(intversionCode) | Kiểm tra nếu version code hiện tại nhỏ hơn version code được truyền vào thì show dialog yêu cầu người dùng update | **`DefaultFunction.getInstance(this).checkCodeVersion(newVrCode)`** |
| confirmExit() | Show dialog thông báo trước khi người dùng thoát app | **`DefaultFunction. getInstance(this).confirmExit()`** |
| overrideFonts(finalView v,Typeface typeface) | Override lại fonts của view được truyền vào | **`DefaultFunction.getInstance(this).overrideFonts(view , typeface)`** |

<a name="convert"></a>
### Convert

| **TÊN PHƯƠNG THỨC** | **MÔ TẢ** | **SỬ DỤNG** |
| --- | --- | --- |
| convertPixelsToDp(float px)  | Nhận vào giá trị pixel trả về Dp | **`Utils.convertPixelsToDp(value)`**  |
| convertDpToPixel(float dp) | Nhận vào giá trị Dp trả về Pixel | **`Utils.convertDpToPixel(value)`**  |



** **


<a name="shared_preference"></a>
### SharedPreference

| **TÊN PHƯƠNG THỨC** | **MÔ TẢ** | **SỬ DỤNG** |
| --- | --- | --- |
|  saveDate(String key,Date date) | Lưu Date vào SharedPreferences | **`SharedPreference._getInstance_(this).saveDate("UPDATE_DATE",new Date());`** |
| getDate(String key) | Truyền vào key đã lưu trước đó để lấy ra date từ SharedPreferences | **`SharedPreference._getInstance_(this).getDate("UPDATE_DATE");`** |
| saveInt( String key, int num) | Lưu một số vào SharedPreferences | **`SharedPreference._getInstance_(this).saveInt("KEY_NAME", value);`** |
| getInt(String key) | Truyền vào key để lấy ra số đã lưu từ SharedPreferences | **`SharedPreference._getInstance_(this).getInt("KEY_NAME");`** |
| saveBoolean( String key, boolean value) | Lưu một giá trị kiểu boolean vào SharedPreferences | **`SharedPreference._getInstance_(this).saveBoolean("KEY_NAME", true);`** |
| getBoolean(String key) | Truyền vào key để lấy giá trị boolean đã lưu từ SharedPreferences | **`SharedPreference._getInstance_(this).getBoolean("KEY_NAME");`** |
| saveString( String key, String value) | Lưu string vào SharedPreferences | **`SharedPreference._getInstance_(this).saveString("KEY_NAME", "ABC");`** |
| getString(String key) | Truyền vào key để lấy giá trị string đã lưu từ SharedPreferences | **`SharedPreference._getInstance_(this).getString("KEY_NAME");`** |

Liencse
===
Copyright 2019 PAMobile Company
