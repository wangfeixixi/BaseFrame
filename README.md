<<<<<<< HEAD
# FrameXixi

  TheMvp+Fragmentation的结合使用


******

### 配置： 

1.项目根build.gradle添加

            allprojects {
                repositories {
                    google()
                    jcenter()
                    maven { url 'https://jitpack.io' } //添加仓库依赖
                }
            }
2.module的build.gradle添加

             implementation 'com.github.wangfeixixi:FrameXixi:Tag'
		  
Tag最新版本如下
[![](https://jitpack.io/v/wangfeixixi/FrameXixi.svg)](https://jitpack.io/#wangfeixixi/FrameXixi)

	  
### 开始使用啦！

1.封装自己的基类

    	  1.public abstract class BaseActivity<T extends IDelegate> extends ActivityPresenter<T>
    	  2.public abstract class BaseFragment<T extends IDelegate> extends FragmentPresenter<T> 
    	  3.public abstract class BaseDelegate extends AppDelegate 


### 如果觉得好请给我点赞哈！
### 如果需要进一步交流，邮件哦：xuanyuanxixi@foxmail.com
