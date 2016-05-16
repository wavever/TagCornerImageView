#TagCornerImageView
TagCornerImageView is a custom imageview that can add tag with some different style.
##ScreenShots
![](http://ww1.sinaimg.cn/large/ace35ee1gw1f3xc9mun0vj206m0dcjsq.jpg)
![](http://ww4.sinaimg.cn/mw690/ace35ee1gw1f3xbxxsrdxj206m0dcwfv.jpg)
##Demo
[Fir.im](http://fir.im/tagcornerimg)
##Features
TagCornerImageView is extended ImageView,so You can use it as a ImageView,and can add one of style for tag.
##Usage
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
Add this to your module's `build.gradle` file:
```groovy
dependencies {
    ...
    compile 'me.wavever.tagcornerimageview:library:0.2.0'
    }
}
```
###Custom Attribute
`tag_type` the type of tag , now there are two types , `triangle` and `rect`.default value is `rect`<br>
`tag_text` the text of rect tag.<br>
`tag_text_color` the text color of rect tag , defaule value is white<br>
`tag_text_size` the text size of rect tag , default value is 18sp.<br>
`tag_background_color` the color of tag's background , default value is blue.<br>
`tag_background_alpha` the alpha of tag , 0~255 , defaule value is 255.<br>
`tag_icon` the icon of triangle tag.<br>
`tag_gravity` the direction of your tag on , four types:`left_top_corner`,
`right_top_corner`,
`right_bottom_corner` and 
`left_bottom_corner`.<br>
##TODO
* custom the tag's width by dp.
* add more types of tag.
##License

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
