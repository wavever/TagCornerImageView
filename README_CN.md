#TagCornerImageView
TagCornerImageView 是一个自定义的imageview，你在imageview的四个角添加你不同风格的tag标签。
##截图
![](http://ww1.sinaimg.cn/large/ace35ee1gw1f3xc9mun0vj206m0dcjsq.jpg)
![](http://ww4.sinaimg.cn/mw690/ace35ee1gw1f3xbxxsrdxj206m0dcwfv.jpg)
##示例apk
[Fir.im](http://fir.im/tagcornerimg)
##特性
TagCornerImageView 继承自ImageView,所以你可以当做ImageView来使用,也可以添加条形标签，三角标签，或是圆角矩形。.
##使用
###Maven
```xml
<dependency>
  <groupId>me.wavever.tagcornerimageview</groupId>
  <artifactId>library</artifactId>
  <version>0.2.0</version>
  <type>pom</type>
</dependency>
```
###Gradle<br>
在你的module下的`build.gradle`添加:
```groovy
dependencies {
    ...
    compile 'me.wavever.tagcornerimageview:library:0.2.0'
    }
}
```
###自定义属性
`tag_type` 标签的样式，现在支持的样式有： `triangle` and `rect`。默认样式为 `rect`<br>
`tag_text` 条形标签所要显示的文本。<br>
`tag_text_color` 条形标签所要显示的文本颜色，默认为白色。<br>
`tag_text_size` 条形标签所要显示的文本大小，默认为18sp。<br>
`tag_background_color`标签的背景颜色，默认为蓝色。br>
`tag_background_alpha` 标签背景颜色的透明度, 范围为：0~255 , 默认值为255。<br>
`tag_icon` 标签的icon，目前只支持三角样式。<br>
`tag_gravity` 角标所要显示的位置，四个角，分别为:`left_top_corner`,
`right_top_corner`,
`right_bottom_corner` 和
`left_bottom_corner`.<br>
**注意** 条形标签目前只支持文字，所以在条形标签中使用`app:tag_icon`是无效的。
###在xml里使用
```xml
<me.wavever.library.TagCornerImageView
            android:id="@+id/rect_img1"
            android:layout_width="200dp"
            android:layout_height="200dp"
            android:scaleType="centerCrop"
            android:src="@mipmap/wukong"
            android:layout_margin="10dp"
            app:tag_background_color="#f56112"
            app:tag_gravity="left_top_corner"
            app:tag_icon="@mipmap/ic_launcher"
            app:tag_text="小悟空"
            app:tag_text_size="14sp"
            app:tag_type="rect" />
```
##TODO
* 自定义标签的宽度。
* 添加更多的标签样式。

License
-------
    Copyright (c) 2016 Wavever

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
