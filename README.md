## Drawer with Tabs Sample

<img src="https://raw.githubusercontent.com/j796160836/DrawerWithTabsSample/master/screenshot/image01.png" width="250" /> <img src="https://raw.githubusercontent.com/j796160836/DrawerWithTabsSample/master/screenshot/image02.png" width="250" /> <img src="https://raw.githubusercontent.com/j796160836/DrawerWithTabsSample/master/screenshot/image03.png" width="250" />

Here is a sample template for these requirement:

* Use `DrawerLayout` (aka. sliding menu) and hamburger menu  
for page navigations.
* Use `TabLayout` and `ViewPager` for secound layer page switching.
* Fixed screen orientation if not tablet.
* Written in Kotlin language.

### Sample page structure

* Navigation page 1
	* Tab 1
	* Tab 2
	* Tab 3
* Navigation page 2 (with floating action button)
* Navigation page 3

### Dependencies library

This sample using <a href="http://jakewharton.github.io/butterknife/">`Butterknife`</a> for view injections.  
You can replace for `findViewForId(...)` instead.
