# ANE - Android - VolumeController - Wiki

## Yêu cầu:
* Android SDK lớn hơn hoặc bằng 15
* Android Studio 
 
## Developments
JavaAPI cho ANE định nghĩa 2 interfaces sau:
* FREExtension
* FREFunction

Các hàm được gọi đến bởi thư viện AS3 sẽ được map với các lớp xử lý trong thư viện Android
```
    public Map<String, FREFunction> getFunctions() {
        Map<String, FREFunction> functions = new HashMap<>();
        functions.put("init", new InitFunction());
        functions.put("setVolume", new SetVolumeFunction());
        functions.put("getCurrentVolume", new GetCurrentVolume());
        return functions;
    }
```

## Build
* Sử dụng gradle để build: Sử dụng task _createJar_ và _deleteJar_ là các hàm để build file jar:
```
    task deleteJar(type: Delete) {
        delete 'libs/jars/volumenativelib.jar'
    }

    task createJar(type: Copy) {
        from('build/intermediates/bundles/default/')
        into('libs/jars/')
        include('classes.jar')
        rename('classes.jar', 'volumenativelib.jar')
    }
```
* Trong đó, _volumenativelib.jar_ là tên bản build thư viện android. Tên này cần trùng khớp với tên thư viện android trong file _extension.xml_ khi build ANE.
```
    <platform name="Android-ARM">
            <applicationDeployment>
                <nativeLibrary>volumenativelib.jar</nativeLibrary>
                <initializer>my.extension.volume.VolumeExtension</initializer>
            </applicationDeployment> 
    </platform>
```
* Ngoài ra, trong một số trường hợp không tồn tại thư mục _"build/intermediates/bundles/default/"_ trong phần android library mà thay vào đó là thư mục _"build/intermediates/bundles/debug/"_
* Tên package chứa các class của thư viện cần được đặt trùng với giá trị _initializer_ trong file extension.xml
```
    <initializer>my.extension.volume.VolumeExtension</initializer>
```
