# craft_the_code

Observer观察者模式
================
主题对象，观察对象，观察者可以订阅/取消对主题对象的关注，主题对象一旦有变化会将变化通知给所有关注自己的观察者。
一对多关系，  

一个主题应该有注册监听、取消监听、通知更新这些方法，最基本的  
观察者应该有一个更新方法，好让主题数据变化时，能够调用该方法通知到所有观察者。  

#####JAVA内置观察者
通过抽象类Observable和接口Observer实现  
但是JAVA中只能继承一个类，所以当你实现Observable一个类无法满足需求时，就需要自己实现观察者，定义主题和观察者接口的方式来实现

#####使用场景
发布-订阅




Decorator装饰器模式
================
对一些类功能想扩展，但是又不能修改原有类，这时候就通过装饰器模式实现
一个接口Component
一个具体实现类AComponent
一个装饰器抽象类实现Component，ADecorator，拥有Component同样的方法签名,内部维护了一个Component的实例
一个具体装饰器继承ADecorator，AbDecorator  
参照代码实例，快递装饰器  
其实java里就有这种模式应用  
InputStream就是一个抽象组件  
FileInputStream就是一个具体组件，可以被装饰者包装起来 
FilterInputStream就是一个抽象装饰者 
BufferedInputStream就是具体装饰者

























